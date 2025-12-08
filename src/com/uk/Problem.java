package com.uk;

class Problem {
    int liczbaKolumn;
    int liczbaOsobnikow;
    int liczbaEpok;
    float prawdopodobienstwoMutacji;
    int zakresWspolczynnikow;

    public Problem(int liczbaKolumn, int liczbaOsobnikow, int liczbaEpok, float prawdMutacji, int zakres) {
        this.liczbaKolumn = liczbaKolumn;
        this.liczbaOsobnikow = liczbaOsobnikow;
        this.liczbaEpok = liczbaEpok;
        this.prawdopodobienstwoMutacji = prawdMutacji;
        this.zakresWspolczynnikow = zakres;
    }
}
