package application.proyectonavidad.controller;

import application.proyectonavidad.DAO.ProfesorDAO;
import application.proyectonavidad.Model.Partes_incidencia;
import application.proyectonavidad.Utils.ChangeStage;
import application.proyectonavidad.Utils.ParsearFecha;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class VistaParteController extends SuperController{

    @FXML
    private Label AnioText;

    @FXML
    private Label DescripcionText;

    @FXML
    private Label DiaText;

    @FXML
    private Label FechaText;

    @FXML
    private AnchorPane FondoParte;

    @FXML
    private Label GrupoText;

    @FXML
    private Label HoraText;

    @FXML
    private Label MesText;

    @FXML
    private Label NombreAlumnoText;

    @FXML
    private Label NombreProfeText;

    @FXML
    private Label NombreProfeText2;

    @FXML
    private Label SancionText;

    @FXML
    private Label TextoParteVerde;

    @FXML
    private Label TituloParte;

    ProfesorDAO profesorDAO = new ProfesorDAO();

    public void rellenarParte(Partes_incidencia parte1){
        NombreAlumnoText.setText(parte1.getId_alum().getNombre_alum());
        GrupoText.setText(parte1.getId_alum().getId_grupo().getNombre_grupo());
        NombreProfeText.setText(parte1.getId_profesor().getNombre());
        NombreProfeText2.setText(parte1.getId_profesor().getNombre());
        FechaText.setText(parte1.getFecha().toString());
        HoraText.setText(parte1.getHora());
        DescripcionText.setText(parte1.getDescripcion());
        SancionText.setText(parte1.getSancion());
        AnioText.setText(String.valueOf(parte1.getFecha().getYear()));
        MesText.setText(ParsearFecha.fechaGetMonth(parte1.getFecha()));
        DiaText.setText(String.valueOf(parte1.getFecha().getDayOfMonth()));
        if (parte1.getId_punt_partes()==1){
            TextoParteVerde.setText("Faltas leves de disciplina contrarias a las normas de convivencia");
            TituloParte.setText("PARTE VERDE DE ADVERTENCIA");
            FondoParte.setStyle("-fx-background-color: #befc77;");
        } else if (parte1.getId_punt_partes()==6) {
            TextoParteVerde.setText("");
            TituloParte.setText("PARTE NARANJA DE NOTA NEGATIVA");
            FondoParte.setStyle("-fx-background-color: #fa9746;");
        } else {
            TextoParteVerde.setText("");
            TituloParte.setText("PARTE ROJO DE NOTA NEGATIVA");
            FondoParte.setStyle("-fx-background-color: #ff616c;");
        }
    }

    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        ChangeStage.cambioEscena("ListaPartes.fxml", FondoParte);
    }
}
