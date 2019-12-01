package com.simplistq.tastaz.loginModule.Fragment.Model;

public class DataModel {

    private String heading, desc_notify, time_notify, imgURL;

    public String getImgURL(){
        return imgURL;
    }

    public void setImgURL(String imgURL){
        this.imgURL = imgURL;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getDesc_notify() {
        return desc_notify;
    }

    public void setDesc_notify(String desc_notify) {
        this.desc_notify = desc_notify;
    }

    public String getTime_notify() {
        return time_notify;
    }

    public void setTime_notify(String time_notify) {
        this.time_notify = time_notify;
    }
}
