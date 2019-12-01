package com.simplistq.tastaz.loginModule.Fragment.Model;

public class Menu {
    private String name;
    private String desc;
    private  String pricetype;
    private String image;
    private String price;

    public String getImage() {
        return image;
    }

    public void setImage(String imageResource) {
        this.image = imageResource;
    }
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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
    public String getPricetype() {
        return pricetype;
    }

    public void setPricetype(String pricetype) {
        this.pricetype = pricetype;
    }

    public Menu(String price,String name, String desc,String pricetype, String image ) {
        setName(name);
        setPrice(price);
        setDesc(desc);
        setPricetype(pricetype);
        setImage(image);

    }
}
