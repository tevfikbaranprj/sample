package de.tvbr.entities.api2;

import jakarta.persistence.*;

@Entity
@Table(name="api2_data")

public class Api2Data {

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
