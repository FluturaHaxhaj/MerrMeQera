package com.fiek.ppmapp.Favorites;

public class FavItem {
    private String key_id;
    private String titulli;
    private String lokacioni;
    private String cmimi;
    private String img;

    public FavItem() {
    }

    public FavItem(String key_id, String titulli, String lokacioni, String cmimi, String img) {
        this.key_id = key_id;
        this.titulli = titulli;
        this.lokacioni = lokacioni;
        this.cmimi = cmimi;
        this.img = img;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getTitulli() {
        return titulli;
    }

    public void setTitulli(String titulli) {
        this.titulli = titulli;
    }

    public String getLokacioni() {
        return lokacioni;
    }

    public void setLokacioni(String lokacioni) {
        this.lokacioni = lokacioni;
    }

    public String getCmimi() {
        return cmimi;
    }

    public void setCmimi(String cmimi) {
        this.cmimi = cmimi;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
