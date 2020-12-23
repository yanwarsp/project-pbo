package com.pboreg;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    DecimalFormat de = new DecimalFormat("#.##"); //  format desimal 2 angka dibelakang koma
    public TextField sksmk, kodemk, namamk, nilaimk;
    public Label labelNotif, bobot;
    public Button home;
    // ini untuk tabel menu indeks prestasi
    public TableView<OutputIndeksPrestasi> tabelIndeksPrestasi;
    public TableColumn<OutputIndeksPrestasi, SimpleIntegerProperty> kolomKode;
    public TableColumn<OutputIndeksPrestasi, SimpleStringProperty> kolomNamaMK;
    public TableColumn<OutputIndeksPrestasi, SimpleIntegerProperty> kolomSKS;
    public TableColumn<OutputIndeksPrestasi, SimpleIntegerProperty> kolomNilai;
    public TableColumn<OutputIndeksPrestasi, SimpleDoubleProperty> kolomBobot;

    private KoneksiDB konekDB = new KoneksiDB();
    private Kalkulasi kalkulasi = new Kalkulasi();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void homeClick() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));

        Stage window = (Stage) home.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    public void tambahNilaiMK(ActionEvent actionEvent) {
        String getKodeMk = kodemk.getText();
        String getMk = namamk.getText();
        String getSks = sksmk.getText();
        String getNilai = nilaimk.getText();
        String getBobot = bobot.getText();
        int sks = Integer.parseInt(getSks);
        int nilai = Integer.parseInt(getNilai);
        double hasilBobot = kalkulasi.hitungIp(nilai);
        String cetak = "" + hasilBobot;
        bobot.setText(cetak);

        String query = "INSERT INTO indeksprestasi(kodemk,namamk,sks,nilai,bobot) VALUES('" + getKodeMk + "','" + getMk + "', '" + sks + "' , '" + nilai + "','" + hasilBobot + "')";
        int hasil = konekDB.manipulasiData(query);
        if (hasil == 1) {
            System.out.println("Data berhasil dimasukan");
            tableViewIndeksPrestasi();
        }
    }

    private void tableViewIndeksPrestasi() {
        kolomKode.setCellValueFactory(new PropertyValueFactory<>("kodeMk"));
        kolomNamaMK.setCellValueFactory(new PropertyValueFactory<>("namaMk"));
        kolomSKS.setCellValueFactory(new PropertyValueFactory<>("sks"));
        kolomNilai.setCellValueFactory(new PropertyValueFactory<>("nilai"));
        kolomBobot.setCellValueFactory(new PropertyValueFactory<>("bobot"));

        try {
            String query = "SELECT * FROM indeksprestasi";
            ResultSet hasil = konekDB.getData2(query);
            ObservableList<OutputIndeksPrestasi> outputIndeksPrestasi = FXCollections.observableArrayList();
            tabelIndeksPrestasi.setItems(outputIndeksPrestasi);
            while (hasil.next()) {
                String kodeMk = hasil.getString(2);
                String namaMk = hasil.getString(3);
                int sks = hasil.getInt(4);
                int nilai = hasil.getInt(5);
                double bobot = hasil.getDouble(6);
                outputIndeksPrestasi.add(new OutputIndeksPrestasi(kodeMk, namaMk, sks, nilai, bobot));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        kodemk.setText("");
        namamk.setText("");
        sksmk.setText("");
        nilaimk.setText("");
        bobot.setText("");
    }

    public void buttonHapusMkClick(ActionEvent actionEvent) {

    }

}