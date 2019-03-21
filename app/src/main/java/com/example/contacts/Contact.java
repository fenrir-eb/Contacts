package com.example.contacts;

import com.squareup.picasso.Picasso;

public class Contact {

    private int id;
    private String name;
    private String data;
    private String imgUrl;

    public Contact(int id, String name){
        this.id = id;
        this.name = name;
        this.data = "066/9910362\nrand@rand.com";
        this.imgUrl = "https://picsum.photos/300/300/?random";
    }

    public Contact(int id, String name, String data){
        this.id = id;
        this.name = name;
        this.data = data;
        this.imgUrl = "https://picsum.photos/300/300/?random";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
