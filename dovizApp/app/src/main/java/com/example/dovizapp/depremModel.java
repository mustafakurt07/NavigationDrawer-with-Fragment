package com.example.dovizapp;

public class depremModel {
    String tarih;
    String saat;
    String yer;
    String siddet;
    String derinlik;
    String tur;


    public String getTarih() {
        return tarih;
    }

    public void setTarih(String tarih) {
        this.tarih = tarih;
    }

    public String getSaat() {
        return saat;
    }

    public void setSaat(String saat) {
        this.saat = saat;
    }

    public String getYer() {
        return yer;
    }

    public void setYer(String yer) {
        this.yer = yer;
    }

    public String getSiddet() {
        return siddet;
    }

    public void setSiddet(String siddet) {
        this.siddet = siddet;
    }

    public String getDerinlik() {
        return derinlik;
    }

    public void setDerinlik(String derinlik) {
        this.derinlik = derinlik;
    }
    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }


    public depremModel(String tarih, String saat, String yer, String siddet, String derinlik,String tur) {
        this.tarih = tarih;
        this.saat = saat;
        this.yer = yer;
        this.siddet = siddet;
        this.derinlik = derinlik;
        this.tur=tur;
    }
}
