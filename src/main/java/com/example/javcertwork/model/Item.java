package com.example.javcertwork.model;

import lombok.Data;

@Data
public class Item {

    private Long id;
    private String name;

    public Item() {}

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
