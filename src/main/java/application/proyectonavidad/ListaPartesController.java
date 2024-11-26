package application.proyectonavidad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ListaPartesController implements Initializable{

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
    void OnVolverClic(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fondoParte.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
    }

}
