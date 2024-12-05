package application.proyectonavidad.controller;

import application.proyectonavidad.Model.Alumnos;
import application.proyectonavidad.Model.Partes_incidencia;
import application.proyectonavidad.Model.Profesores;

public abstract class SuperController {

    static Profesores profesor = null;

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profe) {
        profesor = profe;
    }

    static Alumnos alumno = null;

    public static Alumnos getAlumno() {
        return alumno;
    }

    public static void setAlumno(Alumnos alumno) {
        SuperController.alumno = alumno;
    }

    static Partes_incidencia parte = null;

    public static Partes_incidencia getParte() {
        return parte;
    }

    public static void setParte(Partes_incidencia parte) {
        SuperController.parte = parte;
    }
}