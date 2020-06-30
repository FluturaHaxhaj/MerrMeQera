package com.fiek.ppmapp.MenuItems.Favorites;

public class FavItem {
    private String key_id;
    private String titulli;
    private String lokacioni;
    private String cmimi;
    private String pershkrimi;
    private String siperfaqja;
    private String dhoma;
    private String telefoni;
    private String img;

    public FavItem() {
    }

    public FavItem(String key_id, String titulli,String pershkrimi, String lokacioni, String cmimi,String siperfaqja,String dhoma, String telefoni,String img) {
        this.key_id = key_id;
        this.titulli = titulli;
        this.lokacioni = lokacioni;
        this.cmimi = cmimi;
        this.img = img;
        this.pershkrimi = pershkrimi;
        this.siperfaqja = siperfaqja;
        this.dhoma = dhoma;
        this.telefoni = telefoni;
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

    public String getPershkrimi() {
        return pershkrimi;
    }

    public void setPershkrimi(String pershkrimi) {
        this.pershkrimi = pershkrimi;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
