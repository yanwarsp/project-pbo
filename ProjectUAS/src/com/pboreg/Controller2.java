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
    public Label labelNotif, labelError;
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
        try {
            String getKodeMk = kodemk.getText();
            String getMk = namamk.getText();
            String getSks = sksmk.getText();
            String getNilai = nilaimk.getText();
            int sks = Integer.parseInt(getSks);
            int nilai = Integer.parseInt(getNilai);
            double hasilBobot = kalkulasi.hitungIp(nilai);

            String query = "INSERT INTO indeksprestasi(kodemk,namamk,sks,nilai,bobot) VALUES('" + getKodeMk + "','" + getMk + "', '" + sks + "' , '" + nilai + "','" + hasilBobot + "')";
            int hasil = konekDB.manipulasiData(query);
            if (hasil == 1) {
                System.out.println("Data berhasil ditambahkan");
                tableViewIndeksPrestasi();
                labelNotif.setText("MK berhasil ditambahkan");
            }
        } catch (Exception e) {
            System.out.println("Harap isi semua field yang kosong");
            labelError.setText("Harap isi semua field yang kosong");
        }
    }

    public void buttonEditMkClick(ActionEvent actionEvent) {
        try {
            String getKodeMk = kodemk.getText();
            String getMk = namamk.getText();
            String getSks = sksmk.getText();
            String getNilai = nilaimk.getText();
            int sks = Integer.parseInt(getSks);
            int nilai = Integer.parseInt(getNilai);
            double hasilBobot = kalkulasi.hitungIp(nilai);

            String query = "UPDATE indeksprestasi SET nilai='" + nilai + "', bobot='" + de.format(hasilBobot) + "' WHERE kodemk=" + getKodeMk;
            int hasil = konekDB.manipulasiData(query);
            if (hasil == 1) {
                System.out.println("Data Berhasil diedit");
                labelNotif.setText("Data berhasil diedit");
                tableViewIndeksPrestasi();
        }
    } catch (Exception e) {
            System.out.println("Harap pilih data yang akan diedit");
            labelError.setText("Harap pilih data yang akan diedit");        }
    }

    public void buttonHapusMkClick(ActionEvent actionEvent) {
        String namaMk = namamk.getText();
        if (!namaMk.isEmpty()) {
            String query = "DELETE FROM indeksprestasi WHERE namamk=" + namaMk;
            int hasil = konekDB.manipulasiData(query);
            if (hasil == 1) {
                System.out.println("MK berhasil dihapus");

                tableViewIndeksPrestasi();
                labelNotif.setText("MK berhasil dihapus");
            }
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
        labelError.setText("");

        tabelIndeksPrestasi.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showDetail(newValue));
    }

    private void showDetail(OutputIndeksPrestasi indeksprestasi) {
        if (indeksprestasi != null) {
            kodemk.setText(indeksprestasi.getKodeMk());
            namamk.setText(indeksprestasi.getNamaMk());
            sksmk.setText(indeksprestasi.sksProperty().getValue().toString());
            nilaimk.setText(indeksprestasi.nilaiProperty().getValue().toString());


        } else {
            kodemk.setText("");
            namamk.setText("");
            sksmk.setText("");
            nilaimk.setText("");

        }
    }



}