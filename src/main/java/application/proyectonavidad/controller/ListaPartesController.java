package application.proyectonavidad.controller;

import application.proyectonavidad.DAO.ParteDAO;
import application.proyectonavidad.Model.Alumnos;
import application.proyectonavidad.Model.Partes_incidencia;
import application.proyectonavidad.Utils.ChangeStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

public class ListaPartesController {

    @FXML
    private TextField BuscarNumeroExpediente;

    @FXML
    private TableColumn<Partes_incidencia, String> DescripcionColumn;

    @FXML
    private TableColumn<Partes_incidencia, String> ExpedienteColumn;

    @FXML
    private TableColumn<Partes_incidencia, String> FechaColumn;

    @FXML
    private DatePicker FechaFinal;

    @FXML
    private DatePicker FechaInicio;

    @FXML
    private TableColumn<Partes_incidencia, String> GrupoColumn;

    @FXML
    private TableView<Partes_incidencia> LaTabla;

    @FXML
    private TableColumn<Partes_incidencia, String> NombreAlumnoColumn;

    @FXML
    private TableColumn<Partes_incidencia, String> ProfesorColumn;

    @FXML
    private TableColumn<Partes_incidencia, String> SancionColumn;

    @FXML
    private AnchorPane fondoParte;

    ParteDAO parteDAO = new ParteDAO();
    Partes_incidencia[] partes;
    Alumnos alumno;
    Partes_incidencia parte = new Partes_incidencia();

    private ObservableList<Partes_incidencia> partesList;

    @FXML
    void initialize() {
        // Inicializa las columnas de la tabla
        ExpedienteColumn.setCellValueFactory(new PropertyValueFactory<>("id_alum")); // Cambia esto para que devuelva el número de expediente
        NombreAlumnoColumn.setCellValueFactory(new PropertyValueFactory<>("id_alum")); // Cambia esto para que devuelva el nombre del alumno
        GrupoColumn.setCellValueFactory(new PropertyValueFactory<>("id_alum")); // Cambia esto para que devuelva el grupo
        ProfesorColumn.setCellValueFactory(new PropertyValueFactory<>("id_profesor")); // Cambia esto para que devuelva el nombre del profesor
        FechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        DescripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        SancionColumn.setCellValueFactory(new PropertyValueFactory<>("sancion"));

        // Cargar los partes en la tabla
        partes = parteDAO.buscarPartes();
        cargarPartes();
    }

    private void cargarPartes() {
        partesList = FXCollections.observableArrayList(partes);
        LaTabla.setItems(partesList);
    }

    @FXML
    void OnBuscarFechaClic(ActionEvent event) {
        // Implementar la lógica para buscar por fecha
    }

    @FXML
    void OnBuscarNumeroClic(ActionEvent event) {
        if (Objects.equals(BuscarNumeroExpediente.getText(), "")){
            partes = parteDAO.buscarPartes();
        } else {
            alumno = parteDAO.buscarAlumnoByExp(BuscarNumeroExpediente.getText());
            partes = parteDAO.buscarPartesPorId(alumno);
        }
        cargarPartes();
        vaciarCampos();
    }

    public void vaciarCampos(){
        BuscarNumeroExpediente.setText("");
        FechaInicio.setValue(null);
        FechaFinal.setValue(null);
    }

    @FXML
    void OnVolverClic(ActionEvent event) throws IOException {
        ChangeStage.cambioEscena("InicioJefeEstudios.fxml", fondoParte);
    }

    public void OnMouseClic(javafx.scene.input.MouseEvent mouseEvent) {
        parte = (Partes_incidencia) LaTabla.getSelectionModel().getSelectedItem();
    }
}