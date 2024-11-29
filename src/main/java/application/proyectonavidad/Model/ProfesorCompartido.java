package application.proyectonavidad.Model;

public class ProfesorCompartido {
    private static Profesores ProfeIniciado;

    public static Profesores getProfeIniciado() {
        return ProfeIniciado;
    }

    public static void setProfeIniciado(Profesores ProfeIniciado) {
        ProfesorCompartido.ProfeIniciado = ProfeIniciado;
    }
}
