package com.uk;

public class Problem {
    int liczbaPrzedmiotow;
    int liczbaOsobnikow;
    float wagaMaksymalna;
    int liczbaEpok;
    float prawdopodobienstwoMutacji;

    public Problem(int liczbaPrzedmiotow, float wagaMaksymalna,
                   int liczbaOsobnikow, int liczbaPokolen,
                   float prawdopodobienstwoMutacji) {
        this.liczbaPrzedmiotow = liczbaPrzedmiotow;
        this.liczbaOsobnikow = liczbaOsobnikow;
        this.wagaMaksymalna = wagaMaksymalna;
        this.liczbaEpok = liczbaPokolen;
        this.prawdopodobienstwoMutacji = prawdopodobienstwoMutacji;
    }
}
