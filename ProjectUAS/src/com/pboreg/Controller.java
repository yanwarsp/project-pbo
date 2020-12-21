package com.pboreg;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    public Button menuNilaiMhs, menuIP, menuBantuan, menuTentang, home; // gonta ganti scene
    public TextField textNIM, textNama, textAbsen, textTugas, textUTS, textUAS, textPersenAbsen, textPersenTugas, textPersenUTS, textPersenUAS;  // id textfield yang ada di menu pertama
    public TableView<String> tableNilaiMhs; // id tabel nilai yang ada di menu pertama
    public Label labelNotif;

    private KoneksiDB konekDB = new KoneksiDB();
    public Kalkulasi itung = new Kalkulasi();

    //gonta ganti scene
    public void menuNilaiClick() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("nilaimhs.fxml"));

        Stage window = (Stage) menuNilaiMhs.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void menuIPClick() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("indeksprestasi.fxml"));

        Stage window = (Stage) menuIP.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void menuBantuan() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("bantuan.fxml"));

        Stage window = (Stage) menuBantuan.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void menuTentang() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("tentangkami.fxml"));

        Stage window = (Stage) menuTentang.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void homeClick() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

        Stage window = (Stage) home.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //fungsi & method yang ada di menu pertama (nilai matkul)
    public void buttonTambahNIlaiClick(ActionEvent actionEvent) {
        String getNim = textNIM.getText();
        String getNama = textNama.getText();
        String getAbsen = textAbsen.getText();
        String getTugas = textTugas.getText();
        String getUTS = textUTS.getText();
        String getUAS = textUAS.getText();
        String getPrsnAbsen = textPersenAbsen.getText();
        String getPrsnTugas = textPersenTugas.getText();
        String getPrsnUTS = textPersenUTS.getText();
        String getPrsnUAS = textPersenUAS.getText();
        double absen = Double.parseDouble(getAbsen);
        double tugas = Double.parseDouble(getTugas);
        double uts = Double.parseDouble(getUTS);
        double uas = Double.parseDouble(getUAS);
        double pAbsen = Double.parseDouble(getPrsnAbsen);
        double pTugas = Double.parseDouble(getPrsnTugas);
        double pUts = Double.parseDouble(getPrsnUTS);
        double pUas = Double.parseDouble(getPrsnUAS);

        double nilaiAkhir = itung.ngitung(absen, pAbsen, tugas, pTugas, uts, pUts, uas, pUas);
//                (absen/14*100*pAbsen/100) + (tugas*pTugas/100) + (uts*pUts/100) + (uas*pUas/100);
        System.out.println(nilaiAkhir);
        String query = "INSERT INTO nilaimhs(nimmhs,namamhs,kehadiran,tugas,uts,uas,nilaiakhir) VALUES('" + getNim + "','" + getNama + "','" + absen + "','" + tugas + "','" + uts + "','" + uas + "','" + nilaiAkhir + "')";
        int hasil = konekDB.manipulasiData(query);
        if (hasil == 1) {
            System.out.println("Data berhasil dimasukan");
            labelNotif.setText("Data berhasil dimasukan");
        }

    }

    public void buttonHapusNilaiClick(ActionEvent actionEvent) {
        // belum bikin gan
    }
}
