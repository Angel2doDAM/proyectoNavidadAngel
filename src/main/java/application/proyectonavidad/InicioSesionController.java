package application.proyectonavidad;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.util.Objects;

public class InicioSesionController {

    @FXML
    private TextField ContraseniaText;

    @FXML
    private TextField NumeroAsignadoText;

    @FXML
    void OnIniciarSesionClic(ActionEvent event) {
        if (camposVacios()) {
            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setContentText("Pantalla Inicio Usuario");
            alerta.show();
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
