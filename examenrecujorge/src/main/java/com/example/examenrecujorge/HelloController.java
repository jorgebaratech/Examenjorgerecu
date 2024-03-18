package com.example.examenrecujorge;


import com.example.examenrecujorge.model.Alumno;
import com.example.examenrecujorge.controller.JasperController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.sf.jasperreports.engine.JRException;

import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Button btnAddToTable;

    @FXML
    private Button btnExit;

    @FXML
    private TableColumn<?, ?> colAD;

    @FXML
    private TableColumn<?, ?> colApellido;

    @FXML
    private TableColumn<?, ?> colDI;

    @FXML
    private TableColumn<?, ?> colEIE;

    @FXML
    private TableColumn<?, ?> colHLC;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPMDM;

    @FXML
    private TableColumn<?, ?> colPSP;

    @FXML
    private TableColumn<?, ?> colSGE;

    @FXML
    private TextField inputAD;

    @FXML
    private TextField inputDI;

    @FXML
    private TextField inputEIE;

    @FXML
    private TextField inputHCL;

    @FXML
    private TextField inputLastName;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputPMDM;

    @FXML
    private TextField inputPSP;

    @FXML
    private TextField inputSGE;

    @FXML
    private TableView<Alumno> table;

    public ArrayList<Alumno> alumnos;

    @FXML
    private Button btnShowReport;

    @FXML
    private Button btnDownloadReport;

    @Override

    public void initialize(URL url, ResourceBundle resourceBundle) {
        alumnos = new ArrayList<>();
        inflateTable();
        updateTable();
    }



    @FXML
    private void showReport() {
        try {
            JasperController.showReport();
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void downloadReport() {
        try {
            JasperController.generateReport();
        } catch (JRException | ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void addToTheTable(ActionEvent event) {
        if (inputAD.getText().isEmpty() || inputDI.getText().isEmpty() || inputEIE.getText().isEmpty() ||
                inputHCL.getText().isEmpty() || inputLastName.getText().isEmpty() || inputName.getText().isEmpty() ||
                inputPMDM.getText().isEmpty() || inputPSP.getText().isEmpty() || inputSGE.getText().isEmpty()) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("ERROR");
            fail.setContentText("Faltan campos por rellenar");
            fail.showAndWait();
            return;

        } else if (Double.valueOf(inputAD.getText()) > 10 || Double.valueOf(inputDI.getText()) > 10 || Double.valueOf(inputEIE.getText()) > 10 ||
                Double.valueOf(inputHCL.getText()) > 10 || Double.valueOf(inputPMDM.getText()) > 10 || Double.valueOf(inputPSP.getText()) > 10 || Double.valueOf(inputSGE.getText()) > 10
                || Double.valueOf(inputAD.getText()) < 0 || Double.valueOf(inputDI.getText()) < 0 || Double.valueOf(inputEIE.getText()) < 0 ||
                Double.valueOf(inputHCL.getText()) < 0 || Double.valueOf(inputPMDM.getText()) < 0 || Double.valueOf(inputPSP.getText()) < 0 || Double.valueOf(inputSGE.getText()) < 0) {
            Alert fail = new Alert(Alert.AlertType.INFORMATION);
            fail.setHeaderText("ERROR");
            fail.setContentText("Las notas no pueden estar por debajo de 0 ni por encima de 10");
            fail.showAndWait();
            return;
        }

        Alumno alumno = new Alumno(
                inputName.getText(),
                inputLastName.getText(),
                Double.valueOf(inputAD.getText()),
                Double.valueOf(inputSGE.getText()),
                Double.valueOf(inputDI.getText()),
                Double.valueOf(inputPMDM.getText()),
                Double.valueOf(inputEIE.getText()),
                Double.valueOf(inputPSP.getText()),
                Double.valueOf(inputHCL.getText())
        );

        alumnos.add(alumno);
        updateTable();
    }


    private void inflateTable() {
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        colID.setCellValueFactory(new PropertyValueFactory("id"));
        colName.setCellValueFactory(new PropertyValueFactory("name"));
        colApellido.setCellValueFactory(new PropertyValueFactory("lastName"));
        colAD.setCellValueFactory(new PropertyValueFactory("AD"));
        colSGE.setCellValueFactory(new PropertyValueFactory("SGE"));
        colDI.setCellValueFactory(new PropertyValueFactory("DI"));
        colPMDM.setCellValueFactory(new PropertyValueFactory("PMDM"));
        colPSP.setCellValueFactory(new PropertyValueFactory("PSP"));
        colEIE.setCellValueFactory(new PropertyValueFactory("EIE"));
        colHLC.setCellValueFactory(new PropertyValueFactory("HLC"));
    }

    @FXML
    public void onClickTable(MouseEvent event) {
        table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alumno alumno = table.getSelectionModel().getSelectedItem();
                String text = "";
                if (alumno.getAD() >= 5 && alumno.getSGE() >= 5 && alumno.getDI() >= 5 && alumno.getPMDM() >= 5
                        && alumno.getPSP() >= 5 && alumno.getEIE() >= 5 && alumno.getHLC() >= 5) {

                    Double averagePoints = (alumno.getAD() + alumno.getSGE() + alumno.getDI()
                            + alumno.getPMDM() + alumno.getPSP() + alumno.getEIE() + alumno.getHLC()) / 7;

                    DecimalFormat decimalFormat = new DecimalFormat("##.00");
                    text = "La media de todos los módulos es: " + decimalFormat.format(averagePoints);
                } else {
                    int numberOfFailedSubjects = 0;
                    if (alumno.getAD() < 5) {
                        numberOfFailedSubjects++;
                    }
                    if (alumno.getSGE() < 5) {
                        numberOfFailedSubjects++;
                    }
                    if (alumno.getDI() < 5) {
                        numberOfFailedSubjects++;
                    }
                    if (alumno.getPMDM() < 5) {
                        numberOfFailedSubjects++;
                    }
                    if (alumno.getPSP() < 5) {
                        numberOfFailedSubjects++;
                    }
                    if (alumno.getEIE() < 5) {
                        numberOfFailedSubjects++;
                    }
                    if (alumno.getHLC() < 5) {
                        numberOfFailedSubjects++;
                    }
                    text = "El número de módulos suspensos es: " + numberOfFailedSubjects;
                }

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Información del Alumno");
                alert.setHeaderText(alumno.getName() + " " + alumno.getLastName());
                alert.setContentText(text);
                alert.showAndWait();
            }
        });
    }

    private void updateTable() {
        table.getItems().clear();
        table.getItems().addAll(alumnos);
    }



    @FXML
    private void salir() {
        System.exit(0);
    }
}