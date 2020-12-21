package com.pboreg;

public class Kalkulasi {
    public void NilaiMhs() {
        double absen, tugas, uts, uas, pAbsen, pTugas, pUts, pUas, nilaiAkhir;
        absen = tugas = uts = uas = pAbsen = pTugas = pUts = pUas = 0;
        nilaiAkhir = (absen/16*100*pAbsen/100) + (tugas*pTugas/100) + (uts*pUts/100) + (uas*pUas/100);
        System.out.println(nilaiAkhir);
    }
}
