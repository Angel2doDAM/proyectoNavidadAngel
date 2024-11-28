package application.proyectonavidad.controller;

import application.proyectonavidad.DAO.ProfesorDAO;
import application.proyectonavidad.Model.Profesores;
import application.proyectonavidad.Utils.ChangeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.Objects;

public class InicioSesionController {

    @FXML
    private TextField ContraseniaText;

    @FXML
    private TextField NumeroAsignadoText;

    @FXML
    private AnchorPane InicioFondo;

    ProfesorDAO profesorDAO = new ProfesorDAO();

    Profesores prof1 = new Profesores();

    IniJefController controllerJefe;
    IniProfController controllerProfe;

    @FXML
    void OnIniciarSesionClic(ActionEvent event) throws IOException {
        if (camposVacios()) {
            prof1 = profesorDAO.comprobarProfesor(NumeroAsignadoText.getText(), ContraseniaText.getText());
            if (prof1!=null){
                if (Objects.equals(prof1.getTipo(), "profesor")) {
                    controllerProfe = ChangeStage.cambioEscenaProfe("InicioProfesor.fxml", InicioFondo);
                    controllerProfe.guardarProfeIniciado(prof1);
                } else {
                    controllerJefe = ChangeStage.cambioEscenaJefe("InicioJefeEstudios.fxml", InicioFondo);
                    controllerJefe.guardarProfeIniciado(prof1);
                }
            }
        }
    }

    public boolean camposVacios(){
        if (Objects.equals(ContraseniaText.getText(), "") || Objects.equals(NumeroAsignadoText.getText(), "")){
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setContentText("Todos los campos deben estar rellenos");
            alerta.show();
            return false;
        }
        return true;
    }

}
