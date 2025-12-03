package com.uk;

import java.util.Date;
import java.util.Random;

public class Populacja {
    private Osobnik[] populacja;
    private Date date;

    public Populacja(Integer numerOsobnika, Integer numerPrzedmiotu)
    {
        this.populacja = new Osobnik[numerOsobnika];
        for (int i=0;i<numerOsobnika;i++){
            populacja[i]=new Osobnik(numerPrzedmiotu);
        }
    }

    public void zainicjujLosowo() {
        for (int i = 0; i < populacja.length; i++) {
            populacja[i].zainicjujOsobnikaLosowo();
        }
    }

    public void ocenOsobniki(float wagaMaksymalna, ZbiorPrzedmiotow zbiorPrzedmiotow) {
        for (int nrOsobnika = 0; nrOsobnika < populacja.length; nrOsobnika++) {
            populacja[nrOsobnika].obliczWagePrzedmiotow(zbiorPrzedmiotow);
            populacja[nrOsobnika].obliczWartoscPrzedmiotow(zbiorPrzedmiotow);
            populacja[nrOsobnika].obliczWartoscFunkcjiOceniajacej(wagaMaksymalna);
        }
    }

    public void wypiszPopulacje(){
        for(int nrOsobnika = 0; nrOsobnika< populacja.length; nrOsobnika++){
            System.out.println("Osobnik "+ nrOsobnika+":");
            System.out.println(populacja[nrOsobnika].toString());
        }
    }
    public void drukujNajlepszePrzystosowanie(){
        System.out.println("Najlepsze przystosowanie: "+ szukajNajlepszejOceny());
    }

    public void krzyzujOsobniki(int ind_1, int ind_2){
        Osobnik osobnikPom=new Osobnik(populacja[ind_1]);
        Random random=new Random();
        int punktKrzyzowania= random.nextInt(osobnikPom.wezRozmiar());
        for (int i = punktKrzyzowania; i<osobnikPom.wezRozmiar(); i++)
        {
            populacja[ind_1].ustawPrzedmiot(i, populacja[ind_2].wezPrzedmiot(i));
            populacja[ind_2].ustawPrzedmiot(i,osobnikPom.wezPrzedmiot(i));
        }

    }

    public void krzyzujPopulacje(){

        for (int i = 0; i< populacja.length/2; i++){
            krzyzujOsobniki(2*i,2*i+1);
        }
    }
    public int wezRozmiar(){
        return populacja.length;
    }

    public Osobnik wezOsobnik(int nrInd){
        return populacja[nrInd];
    }

    public float szukajNajlepszejOceny(){
        float max_temp=0f;
        for (int i = 1; i< populacja.length; i++) {
            if (max_temp<= populacja[i].wezWartoscFunkcjiOceniajacej()) max_temp= populacja[i].wezWartoscFunkcjiOceniajacej();
        }
        return max_temp;
    }

    public Osobnik szukajNajlepszegoOsobnika(){
        float max_temp=0f;
        int nr_mejor=0;
        for (int i = 1; i< populacja.length; i++) {
            if (max_temp<= populacja[i].wezWartoscFunkcjiOceniajacej()) {
                max_temp = populacja[i].wezWartoscFunkcjiOceniajacej();
                nr_mejor=i;
            }
            }
        return populacja[nr_mejor];
        };



    public void wybierzOsobniki(Populacja populacjaPoczatkowa){

        float max_ocena=populacjaPoczatkowa.szukajNajlepszejOceny();
        Random random=new Random();

        //losujemy osobniki aż nie będziemy mieli odpowiedniej liczby osobników w nowej populacji
        int liczbaOsobnikowWPopulacji=0;
        int rozmiarPopulacjiPoczatkowej = populacjaPoczatkowa.wezRozmiar();
        while (liczbaOsobnikowWPopulacji<(rozmiarPopulacjiPoczatkowej) )
       {
        for (int i = 0; i< populacja.length && (liczbaOsobnikowWPopulacji<(rozmiarPopulacjiPoczatkowej)); i++){
            float aktualnaOcena=populacjaPoczatkowa.wezOsobnik(i).wezWartoscFunkcjiOceniajacej();
            float wartoscLosowa= random.nextFloat();
            if (aktualnaOcena>=0) {
               if (wartoscLosowa*max_ocena<=aktualnaOcena){    //10,15,25
                 populacja[liczbaOsobnikowWPopulacji]=new Osobnik(populacjaPoczatkowa.wezOsobnik(i));
                 liczbaOsobnikowWPopulacji++;
               };
            }
            //pozwalamy na to, żeby bardzo słabe osobniki miały szansę na krzyżowanie, ale z małym prawdopodobieństwem
            else if (wartoscLosowa<0.1f) {
                populacja[liczbaOsobnikowWPopulacji]=new Osobnik(populacjaPoczatkowa.wezOsobnik(i));
                liczbaOsobnikowWPopulacji++;
            }
            }
        }

    }

    public void podmienPopulacjeAktualna(Populacja nowaPopulacja) {
        this.populacja = new Osobnik[nowaPopulacja.wezRozmiar()];

        for (int i = 0; i< populacja.length; i++){
            populacja[i]=new Osobnik(nowaPopulacja.wezOsobnik(i));
        }
    }
}
