package com.uk;

public class Przedmiot {
    private Float wartosc;

    private Float waga;


    public Przedmiot(Float wartosc, Float waga) {
        this.wartosc = wartosc;
        this.waga = waga;
    }
    public Float wezWartosc() {
        return wartosc;
    }
    public Float wezWaga() {
        return waga;
    }

}
