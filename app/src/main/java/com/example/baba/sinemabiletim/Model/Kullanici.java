package com.example.baba.sinemabiletim.Model;

// Kullanici means User. Isim = Name, Sifre = Password

public class Kullanici {

    private String Isim;
    private String Sifre;


    public Kullanici() {
    }

    public Kullanici(String isim, String sifre) {
        Isim = isim;
        Sifre = sifre;
    }

    public String getIsim() {
        return Isim;
    }

    public void setIsim(String isim) {
        Isim = isim;
    }

    public String getSifre() {
        return Sifre;
    }

    public void setSifre(String sifre) {
        Sifre = sifre;
    }

}
