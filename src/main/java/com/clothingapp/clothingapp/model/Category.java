package com.clothingapp.clothingapp.model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String description;

    public Category(String description, Long id, String name) {
        this.description = description;
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    @Column
    public Long getId() {
        return id;
    }

    @Column
    public String getName() {
        return name;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column
    public void setDescription(String description) {
        this.description = description;
    }
}


