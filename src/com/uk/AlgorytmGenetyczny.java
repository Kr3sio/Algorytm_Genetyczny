package com.uk;

public class AlgorytmGenetyczny {

    ZbiorPrzedmiotow zbiorPrzedmiotow;
    Problem problem;
    Populacja populacjaAktualna;
    Populacja populacjaNowa;

    public AlgorytmGenetyczny(Problem problem, ZbiorPrzedmiotow zbiorPrzedmiotow) {
        this.problem = problem;
        this.zbiorPrzedmiotow = zbiorPrzedmiotow;
    }

    public void szukajRozwiazania() {

        zbiorPrzedmiotow.drukujPrzedmioty();
        //Inicjacja populacji początkowej:
        this.populacjaAktualna = new Populacja(problem.liczbaOsobnikow, problem.liczbaPrzedmiotow);
        this.populacjaAktualna.zainicjujLosowo();
        populacjaAktualna.ocenOsobniki(problem.wagaMaksymalna, zbiorPrzedmiotow);
        System.out.println("populacja początkowa:");
        //najlepsze przystosowanie początkowe
        populacjaAktualna.drukujNajlepszePrzystosowanie();

        //Inicjacja nowej populacji:
        this.populacjaNowa = new Populacja(problem.liczbaOsobnikow, problem.liczbaPrzedmiotow);

        //dla każdego pokolenia
        for (int nrPokolenia = 1; nrPokolenia <= problem.liczbaEpok; nrPokolenia++) {
            System.out.println("Pokolenie:"+nrPokolenia);
            //oceń osobniki:
            populacjaAktualna.ocenOsobniki(problem.wagaMaksymalna, zbiorPrzedmiotow);
            //Selekcja osobników

            populacjaNowa.wybierzOsobniki(populacjaAktualna);

            populacjaNowa.krzyzujPopulacje();

            //TUTAJ POWINNA BYĆ MUTACJA

            populacjaNowa.ocenOsobniki(problem.wagaMaksymalna, zbiorPrzedmiotow);
            populacjaNowa.drukujNajlepszePrzystosowanie();

            populacjaAktualna.podmienPopulacjeAktualna(populacjaNowa);

        }
        System.out.println("Najlepszy osobnik:");
        populacjaAktualna.ocenOsobniki(problem.wagaMaksymalna, zbiorPrzedmiotow);
        System.out.println(populacjaAktualna.szukajNajlepszegoOsobnika().toString());
    }

}
