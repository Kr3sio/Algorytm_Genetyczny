package com.uk;

import java.util.Arrays;
import java.util.Random;

public class Osobnik {

    private int[] geny;
    private Float wartoscFunkcjiOceniajacej;
    private int zakresWspolczynnikow;

    public Osobnik(int liczbaGenow, int zakresWspolczynnikow ) {

        geny = new int[liczbaGenow];
        this.zakresWspolczynnikow = zakresWspolczynnikow;
    }

  //konstruktor kopiujacy
    public Osobnik(Osobnik osobnik){
        this.geny = Arrays.copyOf(osobnik.geny, osobnik.geny.length);
        this.wartoscFunkcjiOceniajacej = osobnik.wartoscFunkcjiOceniajacej;
        this.zakresWspolczynnikow = osobnik.zakresWspolczynnikow;
    }

    public Integer wezPrzedmiot(int i) {
        return geny[i];
    }

    public void ustawPrzedmiot(int nrPrzedmiotu, int wartosc) {
        geny[nrPrzedmiotu] = wartosc;
    }


    public void zainicjujOsobnikaLosowo() {

        Random random = new Random();
        for (int i = 0; i < geny.length; i++){
            geny[i]= random.nextInt((2 * zakresWspolczynnikow) + 1) -zakresWspolczynnikow;
        }
    }

    public void obliczWartoscFunkcjiOceniajacej(ZbiorDanych dane) { //TODO zabiór danych
        double MSE = 0;
        int liczbaPrzykladow = dane.getLiczbeWierszy();
        int liczbaZmiennych = dane.getLiczbeKolumn();

        for (int i = 0; i < liczbaPrzykladow; i++) {
            double yObliczone = 0;

            for (int j = 0; j < liczbaZmiennych; j++) {
                yObliczone += geny[j] * dane.getX(i, j);
            }
            yObliczone += geny[liczbaZmiennych];

            double yPrawdziwe = dane.getY(i);
            double blad = yObliczone - yPrawdziwe;
            MSE += (blad * blad);
        }
        this.wartoscFunkcjiOceniajacej = (float) (MSE / liczbaPrzykladow);
    }

    public Float wezWartoscFunkcjiOceniajacej() {
        return wartoscFunkcjiOceniajacej;
    }


    public int wezRozmiar()
    {
        return geny.length;
    }

    public void mutacja (float prawdMutacji) {
        Random random = new Random();
        for (int i = 0; i < geny.length; i++){
            if (random.nextFloat() < prawdMutacji) {
                geny[i] = random.nextInt((2 * zakresWspolczynnikow) + 1) -zakresWspolczynnikow;
            }
        }
    }

//    @Override
//    public String toString() {
//        return "Osobnik{" +
//                "przedmioty=" + Arrays.toString(geny) +
//                ", wartość Przedmiotow=" + wartoscPrzedmiotow +
//                ", waga Przedmiotow=" + wagaPrzedmiotow +
//                ", ocena osobnika=" + wartoscFunkcjiOceniajacej +
//                '}';
//    }


    public int getGen(int i) {
        return geny[i];
    }

    public void setGen(int i, int wartosc) {
        geny[i] = wartosc;
    }

    public int getLiczbaGenow() {
        return geny.length;
    }

    public Float getWartoscFunkcjiOceniajacej() {
        return wartoscFunkcjiOceniajacej;
    }

    public void setWartoscFunkcjiOceniajacej(Float wartoscFunkcjiOceniajacej) {
        this.wartoscFunkcjiOceniajacej = wartoscFunkcjiOceniajacej;
    }

    public int getZakresWspolczynnikow() {
        return zakresWspolczynnikow;
    }

    public void setZakresWspolczynnikow(int zakresWspolczynnikow) {
        this.zakresWspolczynnikow = zakresWspolczynnikow;
    }

    @Override
    public String toString() {
        return "Osobnik{" +
                "geny=" + Arrays.toString(geny) +
                ", wartoscFunkcjiOceniajacej=" + wartoscFunkcjiOceniajacej +
                ", zakresWspolczynnikow=" + zakresWspolczynnikow +
                '}';
    }
}
