package com.uk;

import java.util.Date;
import java.util.Random;

public class Populacja {
    private Osobnik[] osobniki;
    private int zakresGenow;

    public Populacja(int liczbaOsobnikow, int liczbaGenow, int zakresGenow) {
        this.osobniki = new Osobnik[liczbaOsobnikow];
        this.zakresGenow = zakresGenow;
        for (int i = 0; i < liczbaOsobnikow; i++) {
            osobniki[i] = new Osobnik(liczbaGenow, zakresGenow);
        }
    }

    public void zainicjujLosowo() {
        for (Osobnik os : osobniki) {
            os.zainicjujOsobnikaLosowo();
        }
    }

    public void ocenOsobniki(ZbiorDanych dane) {
        for (Osobnik os : osobniki) {
            os.obliczWartoscFunkcjiOceniajacej(dane);
        }
    }

    public void wybierzOsobnikiTurniej(Populacja staraPopulacja, int rozmiarTurnieju) {
        Random rand = new Random();
        for (int i = 0; i < osobniki.length; i++) {
            Osobnik najlepszyWTurnieju = null;

            for (int k = 0; k < rozmiarTurnieju; k++) {
                int losowyIndeks = rand.nextInt(staraPopulacja.wezRozmiar());
                Osobnik kandydat = staraPopulacja.wezOsobnik(losowyIndeks);

                if (najlepszyWTurnieju == null || kandydat.wezWartoscFunkcjiOceniajacej() < najlepszyWTurnieju.wezWartoscFunkcjiOceniajacej()) {
                    najlepszyWTurnieju = kandydat;
                }
            }
            // Kopiujemy zwycięzcę do nowej populacji
            osobniki[i] = new Osobnik(najlepszyWTurnieju);
        }
    }

//    public void wypiszPopulacje(){
//        for(int nrOsobnika = 0; nrOsobnika< populacja.length; nrOsobnika++){
//            System.out.println("Osobnik "+ nrOsobnika+":");
//            System.out.println(populacja[nrOsobnika].toString());
//        }
//    }
    public void drukujNajlepszePrzystosowanie(){
        System.out.println("Najlepsze przystosowanie: "+ szukajNajlepszejOceny());
    }

    public void krzyzujPopulacje() {
        // Przechodzimy parami (0,1), (2,3) itd.
        for (int i = 0; i < osobniki.length - 1; i += 2) {
            krzyzujOsobniki(osobniki[i], osobniki[i+1]);
        }
    }

    private void krzyzujOsobniki(Osobnik o1, Osobnik o2) {
        Random rand = new Random();
        int punktCiecia = rand.nextInt(o1.getLiczbaGenow());

        // Wymiana ogonów
        for (int i = punktCiecia; i < o1.getLiczbaGenow(); i++) {
            int temp = o1.getGen(i);
            o1.setGen(i, o2.getGen(i));
            o2.setGen(i, temp);
        }
    }

    public void mutujPopulacje(float pMutacji) {
        for (Osobnik os : osobniki) {
            os.mutacja(pMutacji);
        }
    }


    public int wezRozmiar(){
        return osobniki.length;
    }

    public Osobnik wezOsobnik(int nrInd){
        return osobniki[nrInd];
    }

    public float szukajNajlepszejOceny(){
        float max_temp=0f;
        for (int i = 1; i< osobniki.length; i++) {
            if (max_temp<= osobniki[i].wezWartoscFunkcjiOceniajacej()) max_temp= osobniki[i].wezWartoscFunkcjiOceniajacej();
        }
        return max_temp;
    }

    public Osobnik szukajNajlepszegoOsobnika() {
        Osobnik najlepszy = osobniki[0];
        for (int i = 1; i < osobniki.length; i++) {
            if (osobniki[i].getWartoscFunkcjiOceniajacej() < najlepszy.getWartoscFunkcjiOceniajacej()) {
                najlepszy = osobniki[i];
            }
        }
        return najlepszy;
    }

}
