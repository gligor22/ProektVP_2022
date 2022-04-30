package com.example.proektvp2022.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Services {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private ServicesTypes type;

    private Float price;

    private Integer duration;

    public Services( ServicesTypes type, Float price, Integer duration) {

        this.type = type;
        this.price = price;
        this.duration = duration;
    }

    public Services() {
    }
}
