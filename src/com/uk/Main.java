package com.uk;

public class Main {
//    public static final int LICZBA_PRZEDMIOTOW =10;
//    public static final int LICZBA_OSOBNIKOW =100;
//    public static final float CIEZAR_MAKS =3.5f;
//    public static final int LICZBA_POKOLEN=10;
//    public static final float PRAWDOPODOBIENSTWO_MUTACJI=3.5f;


    public static void main(String[] args) {

      int liczba_przedmiotow =15;
      int liczba_osobnikow =1000;
      float ciezar_maks =2.5f;
      int liczba_pokolen=100;
      float prawdopodobieństwo_mutacji=3.5f;

  //  ZbiorPrzedmiotow zbiorPrzedmiotow = new ZbiorPrzedmiotow(liczba_przedmiotow);

    //liczba_przedmiotow =12;
    ZbiorPrzedmiotow zbiorPrzedmiotow = new ZbiorPrzedmiotow();;


    Problem problem = new Problem(liczba_przedmiotow, ciezar_maks,liczba_osobnikow,
                                  liczba_pokolen, prawdopodobieństwo_mutacji);

    AlgorytmGenetyczny algGen = new AlgorytmGenetyczny(problem,zbiorPrzedmiotow);


    algGen.szukajRozwiazania();


    }
}
