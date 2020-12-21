package com.pboreg;

public class Kalkulasi {

    public double absen, tugas, uts, uas, pAbsen, pTugas, pUts, pUas, nilaiAkhir;
    public double kehadiran;
    public double persentase;

    Kalkulasi(){
        this.kehadiran = 14;
        this.persentase = 100;
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

    public double penilaianMhs(double absen, double pAbsen, double tugas, double pTugas, double uts, double pUts, double uas, double pUas){
        return nilaiAkhir = (absen / kehadiran * pAbsen / persentase)
                + (tugas * pTugas / persentase) + (uts * pUts / persentase)
                + (uas * pUas / persentase);
    }
}