package com.example.proektvp2022.web;

import com.example.proektvp2022.model.Role;
import com.example.proektvp2022.model.User;
import com.example.proektvp2022.model.exceptions.InvalidArgumentsException;
import com.example.proektvp2022.model.exceptions.InvalidUserCredentialsException;
import com.example.proektvp2022.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ResolvableType;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private static String authorizationRequestBaseUri = "oauth2/authorization";
    Map<String, String> oauth2AuthenticationUrls
            = new HashMap<>();

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    public final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(required = false) String error,Model model){
        model.addAttribute("bodyContent","login.html");

        Iterable<ClientRegistration> clientRegistrations = null;
        ResolvableType type = ResolvableType.forInstance(clientRegistrationRepository)
                .as(Iterable.class);
        if (type != ResolvableType.NONE &&
                ClientRegistration.class.isAssignableFrom(type.resolveGenerics()[0])) {
            clientRegistrations = (Iterable<ClientRegistration>) clientRegistrationRepository;
        }

        clientRegistrations.forEach(registration ->
                oauth2AuthenticationUrls.put(registration.getClientName(),
                        authorizationRequestBaseUri + "/" + registration.getRegistrationId()));
        model.addAttribute("urls", oauth2AuthenticationUrls);

        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "master-template";
    }

    @PostMapping("/login")
    public String LogIn(Model model, @RequestParam String username , @RequestParam String password, HttpServletRequest request)
    {
        try {
            User user = userService.login(username,password);
            model.addAttribute("bodyContent","home.html");
            request.getSession().setAttribute("user", user);
            return "master-template";
        }catch (InvalidUserCredentialsException | InvalidArgumentsException e)
        {
            return "redirect:/login?error="+e.getMessage();
        }
    }

    @GetMapping("/login/register")
    public String getRegisterPage(@RequestParam(required = false) String error,Model model){
        model.addAttribute("bodyContent","register.html");
        if(error!=null && !error.isEmpty())
        {
            model.addAttribute("hasError",true);
            model.addAttribute("error",error);
        }
        return "master-template";
    }

    @PostMapping("/login/register")
    public String enrollNewUser(@RequestParam String name, @RequestParam String surname,
                                @RequestParam String username, @RequestParam String password,@RequestParam String repeat,@RequestParam String code)
    {
        try{
            userService.register(username,password,repeat,name,surname,code);
            return "redirect:/login";
        }
        catch(InvalidArgumentsException e)
        {
            return "redirect:/login/register?error="+e.getMessage();
        }
    }
}
