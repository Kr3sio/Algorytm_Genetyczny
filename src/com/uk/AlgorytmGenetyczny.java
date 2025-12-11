package com.uk;

public class AlgorytmGenetyczny {

    ZbiorDanych zbiorDanych;
    Problem problem;
    Populacja populacjaAktualna;
    Populacja populacjaNowa;

    public AlgorytmGenetyczny(Problem problem, ZbiorDanych zbiorDanych) {
        this.problem = problem;
        this.zbiorDanych = zbiorDanych;
    }

    public void szukajRozwiazania() {
        int liczbaGenow = problem.liczbaKolumn + 1;


        //Inicjacja populacji początkowej:
        this.populacjaAktualna = new Populacja( problem.liczbaOsobnikow,liczbaGenow, problem.zakresWspolczynnikow);
        this.populacjaAktualna.zainicjujLosowo();
        populacjaAktualna.ocenOsobniki(zbiorDanych);
        //najlepsze przystosowanie początkowe
        Osobnik najlepszyGlobalnie = populacjaAktualna.szukajNajlepszegoOsobnika();

        //dla każdego pokolenia
        for (int nrPokolenia = 1; nrPokolenia <= problem.liczbaEpok; nrPokolenia++) {
            // 1. Selekcja (Turniejowa)
            populacjaNowa = new Populacja(problem.liczbaOsobnikow, liczbaGenow, problem.zakresWspolczynnikow);
            populacjaNowa.wybierzOsobnikiTurniej(populacjaAktualna, 5); // Turniej rozmiaru 5

            // 2. Krzyżowanie
            populacjaNowa.krzyzujPopulacje();

            // 3. Mutacja
            populacjaNowa.mutujPopulacje(problem.prawdopodobienstwoMutacji);

            // 4. Ocena
            populacjaNowa.ocenOsobniki(zbiorDanych);

            // Zachowaj najlepszego, jeśli nowy najlepszy jest gorszy
            Osobnik najlepszyWPokoleniu = populacjaNowa.szukajNajlepszegoOsobnika();
            if (najlepszyWPokoleniu.getWartoscFunkcjiOceniajacej() < najlepszyGlobalnie.getWartoscFunkcjiOceniajacej()) {
                najlepszyGlobalnie = new Osobnik(najlepszyWPokoleniu);
            }

            // Podmiana populacji
            populacjaAktualna = populacjaNowa;

            // Logowanie co X pokoleń
            if (nrPokolenia % 10 == 0 || nrPokolenia == 1) {
                System.out.printf("Pokolenie %d | Geny %S| Najlepsze MSE: %.4f%n", nrPokolenia, najlepszyGlobalnie.getGeny(),najlepszyGlobalnie.getWartoscFunkcjiOceniajacej());
            }

            // Jeśli błąd wynosi 0, kończymy wcześniej
            if (najlepszyGlobalnie.getWartoscFunkcjiOceniajacej() == 0.0f) {
                System.out.println("Znaleziono idealne rozwiązanie w pokoleniu " + nrPokolenia);
                break;
            }
        }
        System.out.println("KONIEC. Znaleziony wzór: " + najlepszyGlobalnie);
    }



}

