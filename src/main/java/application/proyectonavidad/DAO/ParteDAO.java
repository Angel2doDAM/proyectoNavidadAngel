package application.proyectonavidad.DAO;

import application.proyectonavidad.Conexion.Conexion;
import application.proyectonavidad.Model.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;

public class ParteDAO {

    private SessionFactory factory;
    private Session session;

    public ParteDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public Alumnos buscarAlumnoByExp(String expediente) {
        Alumnos alumno = null;
        try {
            session.beginTransaction();
            alumno = session.createQuery("from Alumnos where numero_expediente=:numero_expediente", Alumnos.class)
                    .setParameter("numero_expediente", expediente).stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumno;
    }

    public Puntuacion_partes buscarPuntuacionByPuntos(int puntos) {
        Puntuacion_partes puntuacion = null;
        try {
            session.beginTransaction();
            puntuacion = session.createQuery("from Puntuacion_partes where puntos=:puntos", Puntuacion_partes.class)
                    .setParameter("puntos", puntos).stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return puntuacion;
    }

    public boolean insertarParte(Alumnos alumno, Profesores profesor, Puntuacion_partes puntos, LocalDate fecha, String hora, String descripcion, String sancion) {
        Partes_incidencia parte = new Partes_incidencia(alumno, profesor, puntos, descripcion, fecha.toString(), hora, sancion);
        try {
            session.beginTransaction();
            session.save(parte);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.clear();
        }
    }

}
