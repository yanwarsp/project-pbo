package com.pboreg;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {
    public Button menuNilaiMhs, menuIP, menuBantuan, menuTentang, home;

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
}
