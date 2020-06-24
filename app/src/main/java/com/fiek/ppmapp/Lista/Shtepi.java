package com.fiek.ppmapp.Lista;

public class Shtepi {

    private String shtepi;
    private String pershkrimi;
    private String lokacioni;
    private String cmimi;
    private String siperfaqja;
    private String kate;
    private String telefoni;
    private String image_url;

    public Shtepi() {
    }

    public Shtepi(String shtepi, String pershkrimi, String lokacioni, String cmimi, String siperfaqja, String kate, String telefoni, String image_url) {
        this.shtepi = shtepi;
        this.pershkrimi = pershkrimi;
        this.lokacioni = lokacioni;
        this.cmimi = cmimi;
        this.siperfaqja = siperfaqja;
        this.kate = kate;
        this.telefoni = telefoni;
        this.image_url = image_url;
    }

    public String getShtepi() {
        return shtepi;
    }

    public void setShtepi(String shtepi) {
        this.shtepi = shtepi;
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

    public String getKate() {
        return kate;
    }

    public void setKate(String kate) {
        this.kate = kate;
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
}
