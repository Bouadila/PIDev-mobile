/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codename1.uikit.cleanmodern;

/**
 *
 * @author brahm
 */
class Dish {
    private String imageName;
    private String dishName;
    private String price;
    private String highlights;
    private String fullDescription;
    
    public Dish(String imageName, String dishName, String price, String highlights, String fullDescription) {
        this.imageName = imageName;
        this.dishName = dishName;
        this.price = price;
        this.highlights = highlights;
        this.fullDescription = fullDescription;
    }

    public String getImageName() {
        return imageName;
    }

    public String getDishName() {
        return dishName;
    }

    public String getPrice() {
        return price;
    }

    public String getHighlights() {
        return highlights;
    }

    public String getFullDescription() {
        return fullDescription;
    }
}
