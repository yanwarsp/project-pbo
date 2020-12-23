package com.pboreg;

import javafx.beans.property.*;

public class OutputIndeksPrestasi {
    private StringProperty kodeMk;
    private StringProperty namaMk;
    private IntegerProperty sks;
    private IntegerProperty nilai;
    private DoubleProperty bobot;

    public String getKodeMk() {
        return kodeMk.get();
    }

    public StringProperty kodeMkProperty() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk.set(kodeMk);
    }

    public String getNamaMk() {
        return namaMk.get();
    }

    public StringProperty namaMkProperty() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk.set(namaMk);
    }

    public int getSks() {
        return sks.get();
    }

    public IntegerProperty sksProperty() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks.set(sks);
    }

    public int getNilai() {
        return nilai.get();
    }

    public IntegerProperty nilaiProperty() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai.set(nilai);
    }

    public double getBobot() {
        return bobot.get();
    }

    public DoubleProperty bobotProperty() {
        return bobot;
    }

    public void setBobot(double bobot) {
        this.bobot.set(bobot);
    }

    public OutputIndeksPrestasi(String kodeMk, String namaMk, int sks, int nilai, double bobot) {
        this.kodeMk = new SimpleStringProperty(kodeMk);
        this.namaMk = new SimpleStringProperty(namaMk);
        this.sks = new SimpleIntegerProperty(sks);
        this.nilai = new SimpleIntegerProperty(nilai);
        this.bobot = new SimpleDoubleProperty(bobot);
    }
}