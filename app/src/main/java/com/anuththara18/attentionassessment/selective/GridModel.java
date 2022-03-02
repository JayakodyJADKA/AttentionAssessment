package com.anuththara18.attentionassessment.selective;

public class GridModel {

    private String image_name;
    private int imgid;

    public GridModel(String image_name, int imgid) {
        this.image_name = image_name;
        this.imgid = imgid;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public int getImgid() {
        return imgid;
    }

    public void setImgid(int imgid) {
        this.imgid = imgid;
    }
}