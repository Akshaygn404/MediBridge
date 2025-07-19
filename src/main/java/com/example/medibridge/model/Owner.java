package com.example.medibridge.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Owner extends User{
    private String license;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Store> stores;


}
