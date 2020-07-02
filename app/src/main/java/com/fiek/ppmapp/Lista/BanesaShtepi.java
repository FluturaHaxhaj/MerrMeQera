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
    private String img2;
    private String img3;
    private String img4;

    public BanesaShtepi() {
    }

    public BanesaShtepi(String key_id, String banesaShtepi, String pershkrimi, String lokacioni, String cmimi, String siperfaqja, String kateDhoma, String telefoni, String image_url, String favStatus, String lat, String lng,
                        String img2, String img3, String img4) {
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
        this.img2 = img2;
        this.img3 = img3;
        this.img4 = img4;
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

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }
}
