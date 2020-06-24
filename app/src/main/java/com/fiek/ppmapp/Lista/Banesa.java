package com.fiek.ppmapp.Lista;

public class Banesa {

    private String banesa;
    private String pershkrimi;
    private String lokacioni;
    private String cmimi;
    private String siperfaqja;
    private String dhoma;
    private String image_url;

    public Banesa() {
    }

    public Banesa(String banesa, String pershkrimi, String lokacioni, String cmimi, String siperfaqja, String dhoma, String image_url) {
        this.banesa = banesa;
        this.pershkrimi = pershkrimi;
        this.lokacioni = lokacioni;
        this.cmimi = cmimi;
        this.siperfaqja = siperfaqja;
        this.dhoma = dhoma;
        this.image_url = image_url;
    }

//    public String getTel() {
//        return tel;
//    }
//
//    public void setTel(String tel) {
//        this.tel = tel;
//    }

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
