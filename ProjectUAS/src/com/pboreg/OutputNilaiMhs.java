package com.pboreg;

import javafx.beans.property.*;

public class OutputNilaiMhs {
    private StringProperty nim;
    private StringProperty nama;
    private IntegerProperty absen;
    private IntegerProperty tugas;
    private IntegerProperty uts;
    private IntegerProperty uas;
    private DoubleProperty nilaiAkhir;

    public String getNim() {
        return nim.get();
    }

    public StringProperty nimProperty() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim.set(nim);
    }

    public String getNama() {
        return nama.get();
    }

    public StringProperty namaProperty() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama.set(nama);
    }

    public int getAbsen() {
        return absen.get();
    }

    public IntegerProperty absenProperty() {
        return absen;
    }

    public void setAbsen(int absen) {
        this.absen.set(absen);
    }

    public int getTugas() {
        return tugas.get();
    }

    public IntegerProperty tugasProperty() {
        return tugas;
    }

    public void setTugas(int tugas) {
        this.tugas.set(tugas);
    }

    public int getUts() {
        return uts.get();
    }

    public IntegerProperty utsProperty() {
        return uts;
    }

    public void setUts(int uts) {
        this.uts.set(uts);
    }

    public int getUas() {
        return uas.get();
    }

    public IntegerProperty uasProperty() {
        return uas;
    }

    public void setUas(int uas) {
        this.uas.set(uas);
    }

    public double getNilaiAkhir() {
        return nilaiAkhir.get();
    }

    public DoubleProperty nilaiAkhirProperty() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir(double nilaiAkhir) {
        this.nilaiAkhir.set(nilaiAkhir);
    }

    public OutputNilaiMhs(String nim, String nama, int absen, int tugas, int uts, int uas, double nilaiAkhir) {
        this.nim = new SimpleStringProperty(nim);
        this.nama = new SimpleStringProperty(nama);
        this.absen = new SimpleIntegerProperty(absen);
        this.tugas = new SimpleIntegerProperty(tugas);
        this.uts = new SimpleIntegerProperty(uts);
        this.uas = new SimpleIntegerProperty(uas);
        this.nilaiAkhir = new SimpleDoubleProperty(nilaiAkhir);
    }
}