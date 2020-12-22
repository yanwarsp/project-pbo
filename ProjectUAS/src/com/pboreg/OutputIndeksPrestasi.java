package com.pboreg;

import javafx.beans.property.*;

public class OutputIndeksPrestasi {
    private StringProperty namaMk;
    private IntegerProperty kodeMk;
    private IntegerProperty sks;
    private IntegerProperty nilai;
    private DoubleProperty bobot;

    public OutputIndeksPrestasi(int a, String b, int c, int d, double e){
        this.kodeMk = new SimpleIntegerProperty(a);
        this.namaMk = new SimpleStringProperty(b);
        this.sks = new SimpleIntegerProperty(c);
        this.nilai = new SimpleIntegerProperty(d);
        this.bobot = new SimpleDoubleProperty(e);
    }

    public String getNamaMk() { return namaMk.get(); }
    public int getKodeMk() { return kodeMk.get(); }
    public int getSks() { return sks.get(); }
    public int getNilai() { return nilai.get(); }
    public double getBobot(){ return bobot.get(); }

    public StringProperty namaMkProperty() { return namaMk; }
    public IntegerProperty kodeMkProperty() { return kodeMk; }
    public IntegerProperty sksProperty() { return sks; }
    public IntegerProperty nilaiProperty() { return nilai; }
    public DoubleProperty bobotProperty() { return  bobot; }

    public void setNamaMk(String a) { this.namaMk.set(a); }
    public void setKodeMk(int b) { this.kodeMk.set(b); }
    public void setSks(int c) { this.sks.set(c); }
    public void setNilai(int d) { this.nilai.set(d); }
    public void setBobot(double e) { this.bobot.set(e); }
}
