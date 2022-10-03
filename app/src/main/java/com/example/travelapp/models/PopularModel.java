package com.example.travelapp.models;

import java.io.Serializable;

public class PopularModel implements Serializable {
    String name;
    String description;
    String type;
    String img_url;

    public PopularModel() {
    }

    public PopularModel(String name, String description, String type, String img_url) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
