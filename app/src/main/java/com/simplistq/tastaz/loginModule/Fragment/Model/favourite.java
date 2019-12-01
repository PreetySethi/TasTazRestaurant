package com.simplistq.tastaz.loginModule.Fragment.Model;

public class favourite  {

    public favourite() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String name;
    private String desc;
    private int image;
    private String price;


    public favourite(String price, String name, String desc, int image ) {
        this.name=name;
        this.price=price;

    }
}
