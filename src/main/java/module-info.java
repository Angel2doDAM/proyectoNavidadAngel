module application.proyectonavidad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens application.proyectonavidad to javafx.fxml;
    exports application.proyectonavidad;
}