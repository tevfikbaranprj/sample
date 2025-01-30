package de.tvbr.entities.repositories.api3;

import jakarta.persistence.*;

@Entity
@Table(name="api3_data")

public class Api3Data {

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
