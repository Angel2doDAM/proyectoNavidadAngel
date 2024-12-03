package application.proyectonavidad.DAO;

import application.proyectonavidad.Model.Alumnos;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AlumnoDAO extends ConexionDAO {
    // SessionFactory factory = getFactory();
    Session session = getSession();

    public List<Alumnos> listarAlumnos() {
        List<Alumnos> alumnos = new ArrayList<>();
        try {
            session.beginTransaction();
            alumnos = session.createQuery("from Alumnos", Alumnos.class)
                    .stream().toList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumnos;
    }

    public List<Alumnos> listarAlumnosByExp(String numero_expediente) {
        List<Alumnos> alumnos = new ArrayList<>();
        try {
            session.beginTransaction();
            alumnos = session.createQuery("from Alumnos where numero_expediente=:numero_expediente", Alumnos.class)
                    .setParameter("numero_expediente", numero_expediente)
                    .stream().toList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumnos;
    }
}
