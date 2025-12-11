package com.uk;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Parametry eksperymentu
        // Scenariusze: liczba kolumn (zmiennych x) np. 3, 7, 10
        int[] scenariuszeLiczbyKolumn = {4, 4, 4};

        for (int liczbaKolumn : scenariuszeLiczbyKolumn) {
            System.out.println("\n==========================================");
            System.out.println("START EKSPERYMENTU DLA " + liczbaKolumn + " KOLUMN");
            System.out.println("==========================================");

            int liczbaOsobnikow = 200; // Większa populacja dla trudniejszych zadań
            int liczbaPokolen = 100;
            float prawdopodobienstwoMutacji = 0.05f; // 5% szansy na mutację genu
            int zakresWspolczynnikow = 10; // szukamy liczb od -10 do 10

            // 1. Generowanie danych treningowych
            int[] wzor = {2, 3, 5, 3,3,-4,3,8,6,4,-5};
            // Losujemy "prawdziwy" wzór, którego algorytm ma się nauczyć
            ZbiorDanych zbiorDanych = new ZbiorDanych();
            zbiorDanych.generujDaneBezWzoru(100, liczbaKolumn, zakresWspolczynnikow, wzor);
//            zbiorDanych.generujDane(100, liczbaKolumn, zakresWspolczynnikow);
            // 100 przykładów uczących


            System.out.println("Prawdziwy wzór (do odgadnięcia): " + Arrays.toString(zbiorDanych.getPrawdziweWspolczynniki()));

            // 2. Definicja problemu
            Problem problem = new Problem(
                    liczbaKolumn,
                    liczbaOsobnikow,
                    liczbaPokolen,
                    prawdopodobienstwoMutacji,
                    zakresWspolczynnikow
            );

            // 3. Uruchomienie AG
            AlgorytmGenetyczny algGen = new AlgorytmGenetyczny(problem, zbiorDanych);
            algGen.szukajRozwiazania();
        }
    }
}
