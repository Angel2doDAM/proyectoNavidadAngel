package application.proyectonavidad.DAO;

import application.proyectonavidad.Conexion.Conexion;
import application.proyectonavidad.Model.Profesores;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class ProfesorDAO {
    private SessionFactory factory;
    private Session session;

    public ProfesorDAO() {
        Conexion.conexion();
        factory = Conexion.getFactory();
        session = Conexion.getSession();
    }

    public Profesores comprobarProfesor(String numero_asignado, String contrasenna) {
        Profesores profesor = null;
        String contrasena = DigestUtils.sha256Hex(contrasenna);
        try {
            session.beginTransaction();
            profesor = session.createQuery("from Profesores where numero_asignado=:numero_asignado and contrasena=:contrasena", Profesores.class)
                    .setParameter("numero_asignado", numero_asignado)
                    .setParameter("contrasena", contrasena)
                    .stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return profesor;
    }

    public boolean buscarProfesor(Profesores prof1) {
        Profesores profesor = null;
        try {
            session.beginTransaction();
            profesor = session.createQuery("from Profesores where numero_asignado=:numero_asignado or nombre=:nombre", Profesores.class)
                    .setParameter("numero_asignado", prof1.getNumero_asignado())
                    .setParameter("nombre", prof1.getNombre())
                    .stream().findFirst().orElse(null);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        if (profesor==null){
            return false;
        } else {
            return true;
        }
    }

    public boolean annadirProfesor(Profesores profesor) {
        profesor.setContrasena(DigestUtils.sha256Hex(profesor.getContrasena()));
        try {
            session.beginTransaction();
            session.save(profesor);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.clear();
        }
    }

    public Set<Profesores> listarProfesores() {
        Set<Profesores> profesores = new HashSet<>();
        try {
            session.beginTransaction();
            profesores = session.createQuery("from Profesores", Profesores.class)
                    .stream().collect(Collectors.toSet());
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return profesores;
    }

}
