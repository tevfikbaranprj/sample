package de.tvbr.entities.repositories.api1;

import jakarta.persistence.*;

@Entity
@Table(name="api1_data")

public class Api1Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
