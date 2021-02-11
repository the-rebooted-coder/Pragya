package com.aaxena.pragya;

public class Model {
    private int image;
    private String title;
    private String desc;
    private String header;

    public Model(int image, String title, String desc, String header) {
        this.image = image;
        this.title = title;
        this.desc = desc;
        this.header=header;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public String getHeader() {return header;}

    public void setHeader(String header) {
        this.header = header;
    }
}