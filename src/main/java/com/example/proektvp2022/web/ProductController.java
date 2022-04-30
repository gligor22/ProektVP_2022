package com.example.proektvp2022.web;

import com.example.proektvp2022.model.Manufacturer;
import com.example.proektvp2022.model.Product;
import com.example.proektvp2022.model.exceptions.CategoryNotFoundException;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.ManufacturerNotFoundException;
import com.example.proektvp2022.model.exceptions.ProductNotFoundException;
import com.example.proektvp2022.service.impl.CategoryService;
import com.example.proektvp2022.service.impl.ManufacturerService;
import com.example.proektvp2022.service.impl.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class ProductController {

    public final ProductService productService;
    public final ManufacturerService manufacturerService;
    public final CategoryService categoryService;

    public ProductController(ProductService productService, ManufacturerService manufacturerService, CategoryService categoryService) {
        this.productService = productService;
        this.manufacturerService = manufacturerService;
        this.categoryService = categoryService;
    }
    @GetMapping
    public String getProductPage(@RequestParam(required = false) Long category,
                                 @RequestParam(required = false) Long manufacturer,
                                 @RequestParam(required = false) String error, Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("manufactures",manufacturerService.findAll());
        model.addAttribute("categories",categoryService.listCategories());
        model.addAttribute("bodyContent","products");
        model.addAttribute("products",productService.filter(category,manufacturer));
        return "master-template";
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/products";
    }
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable Long id,Model model)
    {
        try{
            Product p = productService.findById(id);
            model.addAttribute("product",p);
            model.addAttribute("manufactures", manufacturerService.findAll());
            model.addAttribute("categories", categoryService.listCategories());
            model.addAttribute("bodyContent","addProduct");
            return "master-template";
        }catch (ProductNotFoundException e )
        {
            return "redirect:/products?error=InvalidProductID";
        }
    }
    @GetMapping("/addProduct")
    public String addProductPageShow(@RequestParam(required = false)String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("manufactures",manufacturerService.findAll());
        model.addAttribute("categories",categoryService.listCategories());
        model.addAttribute("bodyContent","addProduct");
        return "master-template";
    }
    @PostMapping("/enrollProduct")
    public String enrollProduct(@RequestParam String name,@RequestParam(required = false) Double price, @RequestParam(required = false) Integer quantity,
                                @RequestParam long category,@RequestParam long manufacturer,@RequestParam(required = false) Long id)
    {
        try{
            if(id!=null)
            {
                productService.edit(id,name, price, quantity, category, manufacturer);
            }
            else {
                productService.save(name, price, quantity, category, manufacturer);
            }
            return  "redirect:/products";
        }catch (ProductNotFoundException | InvalidArgumentsException | CategoryNotFoundException | ManufacturerNotFoundException e){
            if(id!=null)
                return "redirect:/products/edit/"+id+"?error=BadInput";
            return "redirect:/products/addProduct?error=BadInput";
        }
    }

    @GetMapping("/manufactures")
    public String getManufacturesPage(Model model)
    {
        model.addAttribute("manufactures",manufacturerService.findAll());
        model.addAttribute("bodyContent","manufactures.html");
        return "master-template";
    }
    @GetMapping("/manufactures/addManufacturer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addManufacturer(@RequestParam(required = false)String error,Model model)
    {
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        model.addAttribute("bodyContent","addManufacturer.html");
        return "master-template";
    }


    @PostMapping("/manufactures/enrollManufacturer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String enrollManufacturer(@RequestParam(required = false) Long id,@RequestParam String name,@RequestParam String address)
    {
        try{
            if(id!=null)
            {
                manufacturerService.edit(id,name,address);
            }
            else {
                manufacturerService.save(name,address);
            }
            return  "redirect:/products/manufactures";
        }catch ( InvalidArgumentsException | ManufacturerNotFoundException e){
            if(id!=null)
                return "redirect:/products/manufactures/edit/"+id+"?error=BadInput";
            return "redirect:/products/manufactures/addManufacturer?error=BadInput";
        }
    }

    @DeleteMapping("/manufactures/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteManufacturer(@PathVariable Long id){
        manufacturerService.deleteById(id);
        return "redirect:/products/manufactures";
    }
    @PostMapping("/manufactures/edit/{id}")
    public String editManufacturer(@PathVariable Long id,Model model)
    {
        try{
            Manufacturer p = manufacturerService.findById(id);
            model.addAttribute("manufacturer",p);
            model.addAttribute("bodyContent","addManufacturer");
            return "master-template";
        }catch (ManufacturerNotFoundException | InvalidArgumentsException e )
        {
            return "redirect:/products/manufactures?error=InvalidProductID";
        }
    }

}