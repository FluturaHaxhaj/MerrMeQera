package com.fiek.ppmapp.Lista;

public class BanesaShtepi {
    private String key_id;
    private String banesaShtepi;
    private String pershkrimi;
    private String lokacioni;
    private String cmimi;
    private String siperfaqja;
    private String kateDhoma;
    private String telefoni;
    private String image_url;
    private String favStatus;
    private String lat;
    private String lng;

    public BanesaShtepi() {
    }

    public BanesaShtepi(String key_id, String banesaShtepi, String pershkrimi, String lokacioni, String cmimi, String siperfaqja, String kateDhoma, String telefoni, String image_url, String favStatus,String lat,String lng) {
        this.key_id = key_id;
        this.banesaShtepi = banesaShtepi;
        this.pershkrimi = pershkrimi;
        this.lokacioni = lokacioni;
        this.cmimi = cmimi;
        this.lng = lng;
        this.lat = lat;
        this.siperfaqja = siperfaqja;
        this.kateDhoma = kateDhoma;
        this.telefoni = telefoni;
        this.image_url = image_url;
        this.favStatus = favStatus;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getBanesaShtepi() {
        return banesaShtepi;
    }

    public void setBanesaShtepi(String banesaShtepi) {
        this.banesaShtepi = banesaShtepi;
    }

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
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

    public String getSiperfaqja() {
        return siperfaqja;
    }

    public void setSiperfaqja(String siperfaqja) {
        this.siperfaqja = siperfaqja;
    }

    public String getKateDhoma() {
        return kateDhoma;
    }

    public void setKateDhoma(String kateDhoma) {
        this.kateDhoma = kateDhoma;
    }

    public String getTelefoni() {
        return telefoni;
    }

    public void setTelefoni(String telefoni) {
        this.telefoni = telefoni;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
