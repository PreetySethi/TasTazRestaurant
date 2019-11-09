package com.zovvo.tastaz.Model;

public class OrderPopup {
    private String name,orderid, date, time, deliveredby, payment, image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDeliveredby() {
        return deliveredby;
    }

    public void setDeliveredby(String deliveredby) {
        this.deliveredby = deliveredby;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public OrderPopup(String name, String image, String orderid, String date,String time, String deliveredby, String payment ) {
        this.name = name;
        this.image = image;
        this.orderid= orderid;
        this.date = date;
        this.time= time;
        this.deliveredby= deliveredby;
        this.payment= payment;
    }
}
