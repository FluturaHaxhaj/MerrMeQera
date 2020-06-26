package com.fiek.ppmapp.Lista;

public class Banesa {

    private String key_id;
    private String banesa;
    private String pershkrimi;
    private String lokacioni;
    private String cmimi;
    private String siperfaqja;
    private String dhoma;
    private String telefoni;
    private String image_url;
    private String favStatus;



    public Banesa() {
    }

    public Banesa(String key_id,String banesa, String pershkrimi, String lokacioni, String cmimi, String siperfaqja, String dhoma, String telefoni, String image_url,String favStatus) {
        this.banesa = banesa;
        this.key_id = key_id;
        this.pershkrimi = pershkrimi;
        this.lokacioni = lokacioni;
        this.cmimi = cmimi;
        this.siperfaqja = siperfaqja;
        this.dhoma = dhoma;
        this.telefoni = telefoni;
        this.image_url = image_url;
        this.favStatus = favStatus;
    }

    public String getBanesa() {
        return banesa;
    }

    public void setBanesa(String banesa) {
        this.banesa = banesa;
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

    public String getDhoma() {
        return dhoma;
    }

    public void setDhoma(String dhoma) {
        this.dhoma = dhoma;
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

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }
}