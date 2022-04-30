package com.example.proektvp2022.model;

import com.example.proektvp2022.model.exceptions.IncomeNotFoundException;
import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany
    private List<Services> services;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Product> products;

    @ManyToOne
    private Patient patient;

    private Integer done;

    public Income( List<Services> services, List<Product> products,Patient patient) {
        this.services = services;
        this.products = products;
        this.patient=patient;
        this.done=0;
    }

    public Income() {
    }

    public float payPrice()
    {
        float price = 0;
        for (Product r : this.products) {
            price += r.getPrice();
        }
        for(Services s : this.services){
            price+=s.getPrice();
        }
        return price;
    }
}
