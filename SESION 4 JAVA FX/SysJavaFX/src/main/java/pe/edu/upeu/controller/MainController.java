package pe.edu.upeu.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainController {

    @FXML
    TextField txtNum1,txtNum2;

    @FXML
    Button btnSumar, btnRestar, btnMul, btnDiv ;

    @FXML
    Label txtResult;

    @FXML
    public void initialize(){

        btnSumar.setOnAction(actionEvent -> {
            double result = Double.parseDouble(txtNum1.getText()) + Double.parseDouble(txtNum2.getText());
            txtResult.setText(String.valueOf(result));
        });
    }

    @FXML
    public void btnRestar (ActionEvent e){
        double result = Double.parseDouble(txtNum1.getText()) - Double.parseDouble(txtNum2.getText());
        txtResult.setText(String.valueOf(result));

    }

    @FXML
    public void btnMultplicar (ActionEvent e) {
        double result = Double.parseDouble(txtNum1.getText()) * Double.parseDouble(txtNum2.getText());
        txtResult.setText(String.valueOf(result));
    }

    public void btnDividir (ActionEvent e) {
        double result = Double.parseDouble(txtNum1.getText()) / Double.parseDouble(txtNum2.getText());
        txtResult.setText(String.valueOf(result));
    }
}
