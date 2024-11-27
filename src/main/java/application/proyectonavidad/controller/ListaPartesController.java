package application.proyectonavidad.controller;

import application.proyectonavidad.Utils.ChangeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ListaPartesController {

    @FXML
    private TextField BuscarNumeroExpediente;

    @FXML
    private TableColumn<?, ?> DescripcionColumn;

    @FXML
    private TableColumn<?, ?> ExpedienteColumn;

    @FXML
    private TableColumn<?, ?> FechaColumn;

    @FXML
    private DatePicker FechaFinal;

    @FXML
    private DatePicker FechaInicio;

    @FXML
    private TableColumn<?, ?> GrupoColumn;

    @FXML
    private TableView<?> LaTabla;

    @FXML
    private TableColumn<?, ?> NombreAlumnoColumn;

    @FXML
    private TableColumn<?, ?> ProfesorColumn;

    @FXML
    private TableColumn<?, ?> SancionColumn;

    @FXML
    private AnchorPane fondoParte;

    @FXML
    void OnBuscarFechaClic(ActionEvent event) {

    }

    @FXML
    void OnBuscarNumeroClic(ActionEvent event) {

    }

    @FXML
    void OnVolverClic(ActionEvent event) throws IOException {
        ChangeStage.cambioEscena("InicioJefeEstudios.fxml", fondoParte);
    }

}
