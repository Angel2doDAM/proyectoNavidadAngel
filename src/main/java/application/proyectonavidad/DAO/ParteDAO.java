package application.proyectonavidad.DAO;

import application.proyectonavidad.Conexion.Conexion;
import application.proyectonavidad.Model.Alumnos;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ParteDAO {

    private SessionFactory factory;
    private Session session;

    public ParteDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public void insertarParte(Alumnos alumno) {

    }

    public Alumnos buscarAlumnoByExp(String expediente) {
        Alumnos alumno = null;
        try {
            session.beginTransaction();
            alumno = session.createQuery("rom Alumnos where numero_expediente:numero_expediente", Alumnos.class)
                    .setParameter("numero_expediente", expediente).stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return alumno;
    }

}
