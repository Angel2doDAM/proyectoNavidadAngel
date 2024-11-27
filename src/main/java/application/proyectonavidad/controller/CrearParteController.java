package application.proyectonavidad.controller;

import application.proyectonavidad.Utils.ChangeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrearParteController implements Initializable {

    @FXML
    private AnchorPane fondoParte;

    @FXML
    private TextField NumExpedienteAlumnoText;

    @FXML
    private TextArea DescripcionText;

    @FXML
    private TextField NombreProfesor;

    @FXML
    private Label tituloPagina;

    @FXML
    private TextField GrupoText;

    @FXML
    private ComboBox HoraCombo;

    @FXML
    private DatePicker FechaPicker;

    private String tipoParte = "Advertencia";

    private String[] horas={"08:30", "09:25", "10:20", "11:40", "12:35", "13:30", "16:00", "16:55", "17:50", "19:00", "19:55", "20:50"};

    @FXML
    void OnCrearClic(ActionEvent event) throws IOException {
        if (camposVacios()){
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Parte creado");
            alerta.show();
        }
    }

    @FXML
    void OnParteNaranjaClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #fa9746;");
        tituloPagina.setText("PARTE NARANJA  DE NOTA NEGATIVA");
        tipoParte = "Suspensión";
    }

    @FXML
    void OnParteRojoClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #ff616c;");
        tituloPagina.setText("PARTE ROJO  DE NOTA NEGATIVA");
        tipoParte = "Expulsión";
    }

    @FXML
    void OnParteVerdeClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #befc77;");
        tituloPagina.setText("PARTE VERDE DE ADVERTENCIA");
        tipoParte = "Advertencia";
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        ChangeStage.cambioEscena("InicioJefeEstudios.fxml", fondoParte);
    }

    public boolean camposVacios(){
        if (Objects.equals(NumExpedienteAlumnoText.getText(), "") || Objects.equals(DescripcionText.getText(), "")
                || HoraCombo.getValue()==null || FechaPicker.getValue()==null){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Todos los campos deben estar rellenos");
            alerta.show();
            return false;
        }
        return true;
    }

    public void OnKeyPressed(KeyEvent keyEvent) {
        String teclaPulsada = keyEvent.getCode().toString();
        if (teclaPulsada.equals("TAB") || teclaPulsada.equals("ENTER")){

       }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HoraCombo.getItems().addAll(horas);
    }
}
