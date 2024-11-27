package application.proyectonavidad.controller;

import application.proyectonavidad.Utils.ChangeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class IniProfController {

    @FXML
    private AnchorPane AreaCrear;

    @FXML
    private AnchorPane fondoProfe;

    @FXML
    void OnCrearParteClic(MouseEvent event) throws IOException {
        ChangeStage.crearEscena("CrearParte.fxml");
    }

    @FXML
    void OnMouseEnter(MouseEvent event) {
        cambiarFondoAzul(AreaCrear);
    }

    @FXML
    void OnMouseExit(MouseEvent event) {
        cambiarFondoGris(AreaCrear);
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        ChangeStage.cambioEscena("InicioSesion.fxml", fondoProfe);
    }

    public void cambiarFondoAzul(AnchorPane areaAzul){
        areaAzul.setStyle("-fx-background-color: #A3CEEF;");
    }

    public void cambiarFondoGris(AnchorPane areaGris){
        areaGris.setStyle("-fx-background-color: #E4E4E5;");
    }
}
