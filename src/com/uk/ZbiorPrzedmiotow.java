package com.uk;

import java.util.Random;

public class ZbiorPrzedmiotow {
    private Przedmiot[] przedmioty;

    public ZbiorPrzedmiotow(Integer LiczbaPrzedmiotow) {
        przedmioty = new Przedmiot[LiczbaPrzedmiotow];
        Random random = new Random();
        for (int i=0;i<LiczbaPrzedmiotow;i++){

            przedmioty[i]=new Przedmiot(random.nextFloat(), random.nextFloat());

        }
    }

    public ZbiorPrzedmiotow(){
        przedmioty = new Przedmiot[12];
        przedmioty[0]=new Przedmiot(1f, 0.03f);
        przedmioty[1]=new Przedmiot(0.1f, 0.83f);
        przedmioty[2]=new Przedmiot(0.21f, 0.33f);
        przedmioty[3]=new Przedmiot(0.51f, 0.43f);
        przedmioty[4]=new Przedmiot(0.71f, 0.63f);
        przedmioty[5]=new Przedmiot(0.25f, 0.39f);
        przedmioty[6]=new Przedmiot(0.31f, 0.23f);
        przedmioty[7]=new Przedmiot(0.91f, 0.23f);
        przedmioty[8]=new Przedmiot(0.61f, 0.03f);
        przedmioty[9]=new Przedmiot(0.01f, 0.4f);
        przedmioty[10]=new Przedmiot(0.31f, 0.23f);
        przedmioty[11]=new Przedmiot(0.05f, 0.93f);
    }

    public float wezWagePrzedmiotu(int nrArt)
    {
        return przedmioty[nrArt].wezWaga();
    }

    public float wezWartoscPrzedmiotu(int nrArt)
    {
        return przedmioty[nrArt].wezWartosc();
    }

    public int wezLiczbePrzedmiotow(){
        return przedmioty.length;
    }

    public void drukujPrzedmioty() {
        System.out.println("Zbiór przedmiotów");
        for (int i = 0; i< przedmioty.length; i++) {
            System.out.println("Artykuł "+i+":"+" wartość: "+ przedmioty[i].wezWartosc()+" waga:"+ przedmioty[i].wezWaga());
        }
    }
}
