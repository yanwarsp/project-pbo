package com.pboreg;

public class Kalkulasi {

    public double absen, tugas, uts, uas, pAbsen, pTugas, pUts, pUas, nilaiAkhir;
    public double kehadiran;
    public double persentase;
    public double sks, nilai2, nilaiAkhir2;

    Kalkulasi() {
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
        this.sks = 0;
        this.nilai2 = 0;
        this.nilaiAkhir2 = 0;
    }

    public double penilaianMhs(double absen, double pAbsen, double tugas, double pTugas, double uts, double pUts, double uas, double pUas) {
        return nilaiAkhir = (absen / kehadiran * persentase * pAbsen / persentase)
                + (tugas * pTugas / persentase) + (uts * pUts / persentase)
                + (uas * pUas / persentase);
    }

    public double hitungIp(double nilai2) {
        if (nilai2 >= 90 && nilai2 <= 100) {
            double a = 4.0;
            return this.nilaiAkhir2 = a;
        } else if (nilai2 >= 80 && nilai2 <= 89.99) {
            double b = 3.7;
            return this.nilaiAkhir2 = b;
        } else if (nilai2 >= 75 && nilai2 <= 79.99) {
            double c = 3.3;
            return this.nilaiAkhir2 = c;
        } else if (nilai2 >= 70 && nilai2 <= 74.99) {
            double d = 3.0;
            return this.nilaiAkhir2 = d;
        } else if (nilai2 >= 65 && nilai2 <= 69.99) {
            double e = 2.7;
            return this.nilaiAkhir2 = e;
        } else if (nilai2 >= 60 && nilai2 <= 64.99) {
            double f = 2.3;
            return this.nilaiAkhir2 = f;
        } else if (nilai2 >= 55 && nilai2 <= 59.99) {
            double g = 2.0;
            return this.nilaiAkhir2 = g;
        } else if (nilai2 >= 50 && nilai2 <= 54.99) {
            double h = 1.7;
            return this.nilaiAkhir2 = h;
        } else if (nilai2 >= 40 && nilai2 <= 49.99) {
            double i = 1;
            return this.nilaiAkhir2 = i;
        } else {
            double j = 0;
            return this.nilaiAkhir2 = j;
        }
    }
}