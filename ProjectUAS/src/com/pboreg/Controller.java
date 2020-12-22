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

public class Controller implements Initializable{

    DecimalFormat de = new DecimalFormat("#.##"); //  format desimal 2 angka dibelakang koma
    public Button menuNilaiMhs, menuIP, menuBantuan, menuTentang, home; // gonta ganti scene
    public TextField textNIM, textNama, textAbsen, textTugas, textUTS, textUAS, textPersenAbsen, textPersenTugas, textPersenUTS, textPersenUAS;  // id textfield yang ada di menu pertama
    public TextField sksmk, kodemk, namamk, nilaimk, total;
    public Button tambah;
    public Label labelNotif;

    // ini untuk membuat tabel pada menu pertama
    public TableView<OutputNilaiMhs> tableNilaiMhs;
    public TableColumn<OutputNilaiMhs, SimpleStringProperty> columnNIM;
    public TableColumn<OutputNilaiMhs, SimpleStringProperty> columnNama;
    public TableColumn<OutputNilaiMhs, SimpleIntegerProperty> columnAbsen;
    public TableColumn<OutputNilaiMhs, SimpleIntegerProperty> columnTugas;
    public TableColumn<OutputNilaiMhs, SimpleIntegerProperty> columnUTS;
    public TableColumn<OutputNilaiMhs, SimpleIntegerProperty> columnUAS;
    public TableColumn<OutputNilaiMhs, SimpleDoubleProperty> columnNilAkhir;

    private KoneksiDB konekDB = new KoneksiDB();
    private Kalkulasi kalkulasi = new Kalkulasi();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

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

        double nilaiAkhir = kalkulasi.penilaianMhs(absen, pAbsen, tugas, pTugas, uts, pUts, uas, pUas);

        String query = "INSERT INTO nilaimhs(nimmhs,namamhs,kehadiran,tugas,uts,uas,nilaiakhir) VALUES('" + getNim + "','" + getNama + "','" + absen + "','" + tugas + "','" + uts + "','" + uas + "','" + de.format(nilaiAkhir) + "')";
        int hasil = konekDB.manipulasiData(query);
        if (hasil == 1) {
            System.out.println("Data berhasil dimasukan");
            labelNotif.setText("Data berhasil dimasukan");
            tableViewNilaiMhs();
        }
    }

    public void buttonHapusNilaiClick(ActionEvent actionEvent) {
        // belum bikin gan
    }

    private void tableViewNilaiMhs() {
        columnNIM.setCellValueFactory(new PropertyValueFactory<>("NIM"));
        columnNama.setCellValueFactory(new PropertyValueFactory<>("Nama"));
        columnAbsen.setCellValueFactory(new PropertyValueFactory<>("Kehadiran"));
        columnTugas.setCellValueFactory(new PropertyValueFactory<>("Tugas"));
        columnUTS.setCellValueFactory(new PropertyValueFactory<>("UTS"));
        columnUAS.setCellValueFactory(new PropertyValueFactory<>("UAS"));
        columnNilAkhir.setCellValueFactory(new PropertyValueFactory<>("Nilai Akhir"));
        try {
            String query = "SELECT * FROM nilaimhs";
            ResultSet hasil = konekDB.getData(query);
            ObservableList<OutputNilaiMhs> outputNilMhs = FXCollections.observableArrayList();
            tableNilaiMhs.setItems(outputNilMhs);
            while (hasil.next()) {
                String nim = hasil.getString(2);
                String nama = hasil.getString(3);
                int absen = hasil.getInt(4);
                int tugas = hasil.getInt(5);
                int uts = hasil.getInt(6);
                int uas = hasil.getInt(7);
                double nilaiAkhir = hasil.getDouble(8);
                outputNilMhs.add(new OutputNilaiMhs(nim,nama,absen,tugas,uts,uas,nilaiAkhir));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void tambahNilaiMk(ActionEvent actionEvent) {
        String getSks = sksmk.getText();
        String getMk = namamk.getText();
        String getKodeMk = kodemk.getText();
        String getNilai = nilaimk.getText();
        double getSks2 = Double.parseDouble(getSks);
        double getNilai2 = Double.parseDouble(getNilai);
        double haha = kalkulasi.hitungIp(getSks2, getNilai2);
        total.setText(String.valueOf(haha));
    }
}
