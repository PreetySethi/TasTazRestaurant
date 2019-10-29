package com.zovvo.tastaz.Model;

public class History {
    private String orderid;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
    public History(String orderid, String date) {
        this.orderid = orderid;
        this.date=date;
    }
}
