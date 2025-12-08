package com.uk;

import java.util.Arrays;
import java.util.Random;

public class ZbiorDanych {
    private double[][] x; // Dane wejściowe
    private double[] y;   // Dane wyjściowe (oczekiwane)
    private int[] prawdziweWspolczynniki; // Do weryfikacji

    public void generujDane(int liczbaWierszy, int liczbaKolumn, int zakresWartosci) {
        Random rand = new Random();
        x = new double[liczbaWierszy][liczbaKolumn];
        y = new double[liczbaWierszy];

        prawdziweWspolczynniki = new int[liczbaKolumn + 1];
            for (int i = 0; i < liczbaKolumn; i++) {
                prawdziweWspolczynniki[i] = rand.nextInt((2 * zakresWartosci) + 1) - zakresWartosci;
            }
        for (int i = 0; i < liczbaWierszy; i++) {
            double yVal = 0;
            for (int j = 0; j < liczbaKolumn; j++) {
                x[i][j] = rand.nextInt(201) - 100;
                yVal += prawdziweWspolczynniki[j] * x[i][j];
            }
            yVal += prawdziweWspolczynniki[liczbaKolumn];
            y[i] = yVal;
        }

    }

//    public ZbiorDanychStale(){
//    }

    public double getX(int wiersz, int kolumna) { return x[wiersz][kolumna]; }

    public double getY(int wiersz) { return y[wiersz]; }

    public int[] getPrawdziweWspolczynniki() {return prawdziweWspolczynniki;}

    public int getLiczbeWierszy() { return y.length; }

    public int getLiczbeKolumn() { return x[0].length; }

//    public void drukujPrzedmioty() {
//        System.out.println("Zbiór przedmiotów");
//        for (int i = 0; i< przedmioty.length; i++) {
//            System.out.println("Artykuł "+i+":"+" wartość: "+ przedmioty[i].wezWartosc()+" waga:"+ przedmioty[i].wezWaga());
//        }
//    }


    @Override
    public String toString() {
        return "ZbiorDanych{" +
                "x=" + Arrays.toString(x) +
                ", y=" + Arrays.toString(y) +
                ", prawdziweWspolczynniki=" + Arrays.toString(prawdziweWspolczynniki) +
                '}';
    }
}


