package com.easy.air.finaleasydelivery.model;

public class Blogzone {
    private String title, desc, imageUrl, uid, key, from, where, phoneNum, price;

    public Blogzone() {
    }

    public Blogzone(String title, String desc, String imageUrl, String uid, String key, String from, String where, String phoneNum, String price) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.uid = uid;
        this.key = key;
        this.from = from;
        this.where = where;
        this.phoneNum = phoneNum;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWhere() {
        return where;
    }

    public void setWhere(String where) {
        this.where = where;
    }
}
