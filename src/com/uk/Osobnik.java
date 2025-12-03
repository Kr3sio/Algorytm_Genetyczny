package com.uk;

import java.util.Arrays;
import java.util.Random;

public class Osobnik {

    private Integer[] przedmioty;

    private Float wagaPrzedmiotow;
    private Float wartoscPrzedmiotow;
    private Float wartoscFunkcjiOceniajacej;


    public Osobnik(Integer rozmiar ) {

        przedmioty = new Integer[rozmiar];
    }

  //konstruktor kopiujacy
    public Osobnik(Osobnik osobnik){

        przedmioty = new Integer[osobnik.wezRozmiar()];
        for (int i = 0; i< przedmioty.length; i++) przedmioty[i]=osobnik.wezPrzedmiot(i);
    }

    public Integer wezPrzedmiot(int i) {
        return przedmioty[i];
    }

    public void ustawPrzedmiot(int nrPrzedmiotu, int wartosc) {
        przedmioty[nrPrzedmiotu] = wartosc;
    }


    public void zainicjujOsobnikaLosowo() {

        Random random = new Random();
        for (int i = 0; i < przedmioty.length; i++){
            przedmioty[i]= random.nextInt(2);
        }
    }

    public void obliczWartoscFunkcjiOceniajacej(float wagaMaksymalna) {

        if (wagaPrzedmiotow > wagaMaksymalna) wartoscFunkcjiOceniajacej =-1f;
        else wartoscFunkcjiOceniajacej = wartoscPrzedmiotow;
    }

    public Float wezWartoscFunkcjiOceniajacej() {
        return wartoscFunkcjiOceniajacej;
    }

    public void obliczWagePrzedmiotow(ZbiorPrzedmiotow zbPrzedmiotow) {

        float wagaPom = 0;
        for (int nrPrzedmiotu = 0; nrPrzedmiotu < zbPrzedmiotow.wezLiczbePrzedmiotow(); nrPrzedmiotu++) {

            if (przedmioty[nrPrzedmiotu] == 1) wagaPom += zbPrzedmiotow.wezWagePrzedmiotu(nrPrzedmiotu);
        }
        wagaPrzedmiotow =wagaPom;
    }

    public void obliczWartoscPrzedmiotow(ZbiorPrzedmiotow zbiorPrzedmiotow) {

        float wartoscPom = 0;
        for (int nrPrzedmiotu = 0; nrPrzedmiotu < zbiorPrzedmiotow.wezLiczbePrzedmiotow(); nrPrzedmiotu++) {

            if (przedmioty[nrPrzedmiotu] == 1) wartoscPom += zbiorPrzedmiotow.wezWartoscPrzedmiotu(nrPrzedmiotu);
        }
        wartoscPrzedmiotow =wartoscPom;
    }

    public int wezRozmiar()
    {
        return przedmioty.length;
    }

    @Override
    public String toString() {
        return "Osobnik{" +
                "przedmioty=" + Arrays.toString(przedmioty) +
                ", wartość Przedmiotow=" + wartoscPrzedmiotow +
                ", waga Przedmiotow=" + wagaPrzedmiotow +
                ", ocena osobnika=" + wartoscFunkcjiOceniajacej +
                '}';
    }
}
