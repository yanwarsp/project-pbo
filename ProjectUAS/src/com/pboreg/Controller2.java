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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.net.URL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller2 implements Initializable {

    DecimalFormat de = new DecimalFormat("#.##"); //  format desimal 2 angka dibelakang koma
    public TextField sksmk, kodemk, namamk, nilaimk;
    public Label labelNotif, labelError, labelTotalSKS, labelIP;
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
            labelNotif.setText("");
            System.out.println("Harap isi semua field yang kosong");
            labelError.setText("Harap isi semua field yang kosong");
        }
    }

    public void buttonHapusMkClick(ActionEvent actionEvent) {
        String kodeMk = namamk.getText();
        if (!kodeMk.isEmpty()) {
            String query = "DELETE FROM indeksprestasi WHERE namamk='" + kodeMk + "'";
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
            int r = 10;
            ArrayList<Integer> arrayList = new ArrayList<Integer>(r);
            ArrayList<String> arrayList1 = new ArrayList<String>(r);

            double z = 0;
            double u = 0;
            double e;
            while (hasil.next()) {
                String kodeMk = hasil.getString(2);
                String namaMk = hasil.getString(3);
                int sks = hasil.getInt(4);
                int nilai = hasil.getInt(5);
                double bobot = hasil.getDouble(6);
                outputIndeksPrestasi.add(new OutputIndeksPrestasi(kodeMk, namaMk, sks, nilai, bobot));
                double jumlah = 0;
                double jumlahSks = 0;
                arrayList.add(sks);
                String b = String.valueOf(bobot);
                arrayList1.add(b);
                for (int i = 0; i < tabelIndeksPrestasi.getItems().size(); i++) {
                    int list = arrayList.get(i);
                    String list2 = arrayList1.get(i);
                    jumlahSks = (double) list;
                    jumlah = (double) list * Double.parseDouble(list2);
                }
                u = jumlahSks + u;
                z = jumlah + z;
                e = z / u;

                String x = "" + de.format(e);
                String q = "" + de.format(u);
                labelIP.setText(x);
                labelTotalSKS.setText(q);

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