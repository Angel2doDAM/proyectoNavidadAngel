package application.proyectonavidad.controller;

import application.proyectonavidad.Utils.AlertUtils;
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
    private AnchorPane SancionAMano;

    @FXML
    private TextArea SancionText;

    @FXML
    private AnchorPane SancionComboBox;

    @FXML
    private ComboBox ComboSancion;

    @FXML
    private TextArea MiniSancionText;

    @FXML
    private AnchorPane RellenarAAmano;

    @FXML
    private DatePicker FechaPicker;

    private String tipoParte = "Advertencia";

    private int punt_partes = 1;

    private String[] horas={"08:30", "09:25", "10:20", "11:40", "12:35", "13:30", "16:00", "16:55", "17:50", "19:00", "19:55", "20:50"};
    private String[] sanciones={"Incoación de expediente o expediente abreviado", "Reunion con la Comisión de Convivencia", "Obligatorio pedir disculpas a los afectados y reparar los daños causados", "✎ Rellenar a mano"};

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
        SancionAMano.setVisible(true);
        SancionComboBox.setVisible(false);
    }

    @FXML
    void OnParteRojoClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #ff616c;");
        tituloPagina.setText("PARTE ROJO  DE NOTA NEGATIVA");
        tipoParte = "Expulsión";
        SancionAMano.setVisible(false);
        SancionComboBox.setVisible(true);
    }

    @FXML
    void OnParteVerdeClic(ActionEvent event) {
        fondoParte.setStyle("-fx-background-color: #befc77;");
        tituloPagina.setText("PARTE VERDE DE ADVERTENCIA");
        tipoParte = "Advertencia";
        SancionAMano.setVisible(true);
        SancionComboBox.setVisible(false);
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        ChangeStage.cerrarEscena(fondoParte);
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
        ComboSancion.getItems().addAll(sanciones);

        ComboSancion.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (Objects.equals(newValue.toString(), "✎ Rellenar a mano")) {
                RellenarAAmano.setVisible(true);
            } else {
                RellenarAAmano.setVisible(false);
            }
        });
    }
}