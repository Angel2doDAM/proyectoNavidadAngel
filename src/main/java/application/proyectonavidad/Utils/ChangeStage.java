package application.proyectonavidad.Utils;

import application.proyectonavidad.controller.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.IOException;

//Clase para abrir, cerrar y cambiar de stages
public class ChangeStage {

//    Función creada para cambiar de una pestaña a otra de la aplicación
    static public void cambioEscena(String fxmlnName, AnchorPane rootPane) throws IOException {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(R.getUI(fxmlnName));
            Stage newStage = new Stage();
            newStage.setScene(new Scene(loader.load()));
            newStage.sizeToScene();
            newStage.show();
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.close();

    }

    //    Función creada para cambiar de una pestaña a otra de la aplicación, pero esta devuelve el controller para cargar datos
    static public VistaParteController cambioEscenaParte(String fxmlnName, AnchorPane rootPane) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI(fxmlnName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loader.load()));
        newStage.sizeToScene();
        newStage.show();
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.close();
        return loader.getController();

    }

//    Función para abrir una escena sin cerrar la anterior
    static public CrearParteController crearEscena(String fxmlnName) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(R.getUI(fxmlnName));
        Stage newStage = new Stage();
        newStage.setScene(new Scene(loader.load()));
        newStage.initModality(Modality.APPLICATION_MODAL);
        newStage.sizeToScene();
        newStage.show();
        return loader.getController();
    }

//    Función creada para cerrar una única escena
    static public void cerrarEscena(AnchorPane rootPane){
        Stage currentStage = (Stage) rootPane.getScene().getWindow();
        currentStage.close();
    }

}
