package application.proyectonavidad.controller;

import application.proyectonavidad.Model.Profesores;

public abstract class SuperController {

    static Profesores profesor = null;

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profe) {
        profesor = profe;
    }
}