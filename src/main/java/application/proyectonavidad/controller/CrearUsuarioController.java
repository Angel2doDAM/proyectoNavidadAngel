package application.proyectonavidad.controller;

import application.proyectonavidad.DAO.ProfesorDAO;
import application.proyectonavidad.Model.Profesores;
import application.proyectonavidad.Utils.AlertUtils;
import application.proyectonavidad.Utils.ChangeStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CrearUsuarioController extends SuperController implements Initializable {

    @FXML
    private PasswordField ContraseniaText;

    @FXML
    private TextField NombreText;

    @FXML
    private TextField NumeroAsignadoText;

    @FXML
    private ComboBox TipoChoice;

    @FXML
    private Label errorContrasenia;

    @FXML
    private Label errorNombre;

    @FXML
    private Label errorNumero;

    @FXML
    private Label errorTipo;

    @FXML
    private AnchorPane fondoUsuario;

    private String[] tipoUsuario={"Profesor", "Jefe de Estudios"};

    ProfesorDAO profesorDAO = new ProfesorDAO();

    @FXML
    void OnCrearUsuarioClic(ActionEvent event) {
        String tipo = "jefe_de_estudios";
        if (camposVacios()){
            if (Objects.equals(TipoChoice.getValue().toString(), "Profesor")){
                tipo = "profesor";
            }
            Profesores prof1 = new Profesores(ContraseniaText.getText(), NombreText.getText(), NumeroAsignadoText.getText(), tipo);
            if (!profesorDAO.buscarProfesor(prof1)) {
                if (profesorDAO.annadirProfesor(prof1)) {
                    AlertUtils.mostrarAcierto("Usuario creado");
                    vaciarCampos();
                } else {
                    AlertUtils.mostrarError("El Usuario no se ha creado");
                }
            } else {
                AlertUtils.mostrarError("El Usuario ya existe");
            }
        }
    }

    public boolean camposVacios(){
        boolean todoBien = true;
        if (Objects.equals(ContraseniaText.getText(), "")){
            errorContrasenia.setText("La contaseña es un campo obligatorio");
            todoBien = false;
        } else {
            errorContrasenia.setText("");
        }
        if (Objects.equals(NumeroAsignadoText.getText(), "")){
            errorNumero.setText("El número asignado es un campo obligatorio");
            todoBien = false;
        } else {
            errorNumero.setText("");
        }
        if (Objects.equals(NombreText.getText(), "")){
            errorNombre.setText("El nombre es un campo obligatorio");
            todoBien = false;
        } else {
            errorNombre.setText("");
        }
        if (TipoChoice.getValue() == null){
            errorTipo.setText("El tipo es un campo obligatorio");
            todoBien = false;
        } else {
            errorTipo.setText("");
        }
        return todoBien;
    }

    @FXML
    public void OnVolverClic(ActionEvent actionEvent) throws IOException {
        ChangeStage.cambioEscena("InicioJefeEstudios.fxml", fondoUsuario);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TipoChoice.getItems().addAll(tipoUsuario);
    }

    public void vaciarCampos(){
        ContraseniaText.setText("");
        NumeroAsignadoText.setText("");
        NombreText.setText("");
        TipoChoice.setValue(null);
    }
}
