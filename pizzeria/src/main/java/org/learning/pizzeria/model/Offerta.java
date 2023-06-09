package org.learning.pizzeria.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "offerte")
public class Offerta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    private LocalDate startOfferDate;

    private LocalDate endOfferDate;

    private String name;

    @ManyToOne
    @NotNull
    private Pizza pizza;

    public Pizza getPizza() {
        return pizza;
    }

    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getStartOfferDate() {
        return startOfferDate;
    }

    public void setStartOfferDate(LocalDate startOfferDate) {
        this.startOfferDate = startOfferDate;
    }

    public LocalDate getEndOfferDate() {
        return endOfferDate;
    }

    public void setEndOfferDate(LocalDate endOfferDate) {
        this.endOfferDate = endOfferDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}