package com.pboreg;

// Class buat nampung perhitungan di halaman Buku Nilai dan Indeks Prestasi

public class Kalkulasi {

    public double absen, tugas, uts, uas, pAbsen, pTugas, pUts, pUas, nilaiAkhir;
    public double kehadiran;
    public double presentase;

    Kalkulasi(){
        this.kehadiran = 14;
        this.presentase = 100;
        this.absen = 0;
        this.tugas = 0;
        this.uts = 0;
        this.uas = 0;
        this.pAbsen = 0;
        this.pTugas = 0;
        this.pUts = 0;
        this.pUas = 0;
        this.nilaiAkhir = 0;
    }

//    public void NilaiMhs() {
//        double absen, tugas, uts, uas, pAbsen, pTugas, pUts, pUas, nilaiAkhir;
//        absen = tugas = uts = uas = pAbsen = pTugas = pUts = pUas = 0;
//        nilaiAkhir = (absen/16*100*pAbsen/100) + (tugas*pTugas/100) + (uts*pUts/100) + (uas*pUas/100);
//        System.out.println(nilaiAkhir);
//    }

    public double ngitung(double absen, double pAbsen, double tugas, double pTugas, double uts, double pUts, double uas, double pUas){
        return nilaiAkhir = (absen / kehadiran * pAbsen / presentase)
                + (tugas * pTugas / presentase) + (uts * pUts / presentase)
                + (uas * pUas / presentase);
    }
}
