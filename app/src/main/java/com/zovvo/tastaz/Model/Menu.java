package com.zovvo.tastaz.Model;

public class Menu {
    private String name;
    private String desc;
    private int imageResource;
    private String price;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getImageResource()
    {
        return imageResource;
    }

    public void setImageResource(int imageResource)
    {
        this.imageResource = imageResource;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Menu(String name, String desc,int imageResource, String price) {
        this.name = name;
        this.desc=desc;
        this.imageResource = imageResource;
        this.price = price;
    }
}
