package application.proyectonavidad.controller;

import application.proyectonavidad.DAO.ParteDAO;
import application.proyectonavidad.Model.Alumnos;
import application.proyectonavidad.Model.Partes_incidencia;
import application.proyectonavidad.Utils.AlertUtils;
import application.proyectonavidad.Utils.ChangeStage;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class ListaPartesController extends SuperController{

    public Pagination pagination;

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

    VistaParteController controller;

    @FXML
    void initialize() {
        // Inicializa las columnas de la tabla
        ExpedienteColumn.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getId_alum().getNumero_expediente()));
        NombreAlumnoColumn.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getId_alum().getNombre_alum()));
        GrupoColumn.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getId_alum().getId_grupo().getNombre_grupo()));
        ProfesorColumn.setCellValueFactory(data->new ReadOnlyObjectWrapper<>(data.getValue().getId_profesor().getNombre()));
        FechaColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        DescripcionColumn.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        SancionColumn.setCellValueFactory(new PropertyValueFactory<>("sancion"));
        partes = parteDAO.buscarPartes();
        calcularFilas();
        cargarPartes();

        // Establecer la fábrica de filas
        LaTabla.setRowFactory(tv -> new TableRow<Partes_incidencia>() {
            @Override
            protected void updateItem(Partes_incidencia parte1, boolean empty) {
                super.updateItem(parte1, empty);
                if (empty || parte1 == null) {
                    setStyle(""); // Restablecer el estilo si la fila está vacía
                } else {
                    // Cambiar el color según las condiciones
                    if (parte1.getId_punt_partes()==1) {
                        setStyle("-fx-background-color: #befc77;");
                    } else if (parte1.getId_punt_partes()==6) {
                        setStyle("-fx-background-color: #fa9746;");
                    } else {
                        setStyle("-fx-background-color: #ff616c;");
                    }
                }
            }
        });
    }

    public void calcularFilas(){
        int paginas=1;

        if(partes.length%filaporPagina()==0){
            paginas= partes.length/filaporPagina();
        } else if (partes.length>filaporPagina()) {
            paginas= partes.length/filaporPagina()+1;
        }
        pagination.setPageCount(paginas);
        pagination.setCurrentPageIndex(0);
        pagination.setPageFactory(this::crearPaginas);
    }

    public int filaporPagina(){
        return 7;
    }

    private Node crearPaginas(int pageIndex){
        int fromIndex=pageIndex*filaporPagina();
        int toIndex=Math.min(fromIndex+filaporPagina(),partes.length);
        LaTabla.setItems(FXCollections.observableList(Arrays.stream(partes).toList().subList(fromIndex,toIndex)));

        return new BorderPane(LaTabla);
    }

    private void cargarPartes() {
        partesList = FXCollections.observableArrayList(partes);
        LaTabla.setItems(partesList);
    }

    @FXML
    void OnBuscarFechaClic(ActionEvent event) {
        partes = parteDAO.buscarPorFecha(FechaInicio.getValue(), FechaFinal.getValue());
        calcularFilas();
        cargarPartes();
    }

    @FXML
    void OnBuscarNumeroClic(ActionEvent event) {
        BuscarPorNumero();
    }

    public void BuscarPorNumero(){
        if (Objects.equals(BuscarNumeroExpediente.getText(), "")){
            partes = parteDAO.buscarPartes();
        } else {
            alumno = parteDAO.buscarAlumnoByExp(BuscarNumeroExpediente.getText());
            partes = parteDAO.buscarPartesPorId(alumno);
        }
        calcularFilas();
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

    public void OnMouseClic(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        parte = (Partes_incidencia) LaTabla.getSelectionModel().getSelectedItem();
        controller = ChangeStage.cambioEscenaParte("VistaParte.fxml", fondoParte);
        controller.cargarParte(parte);
    }

    public void OnNumeroPressed(KeyEvent keyEvent) {
        String teclaPulsada = keyEvent.getCode().toString();
        if (teclaPulsada.equals("ENTER")) {
            BuscarPorNumero();
        }
    }

}