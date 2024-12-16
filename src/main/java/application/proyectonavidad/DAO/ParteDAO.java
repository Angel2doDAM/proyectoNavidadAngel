package application.proyectonavidad.DAO;

import application.proyectonavidad.Conexion.Conexion;
import application.proyectonavidad.Model.*;
import application.proyectonavidad.Utils.HibernateUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    public List<Partes_incidencia> filtarByAlumno(Alumnos alumno) {
        List<Partes_incidencia> partesIncidencias = new ArrayList<>();
        try {
            session.beginTransaction();
            partesIncidencias = session.createQuery("from Partes_incidencia where id_alum=:alumno_id", Partes_incidencia.class)
                    .setParameter("alumno_id", alumno)
                    .stream().toList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
    }

    public boolean insertarParte(Alumnos alumno, Profesores profesor, int puntos, LocalDate fecha, String hora, String descripcion, String sancion) {
        Partes_incidencia parte = new Partes_incidencia(alumno, profesor, puntos, descripcion, fecha, hora, sancion);
        try {
            session.beginTransaction();
            session.save(parte);
            session.getTransaction().commit();
            modificarAlumno(parte.getId_alum());
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.clear();
        }
    }

    public Partes_incidencia[] buscarPartes() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Partes_incidencia> partesList = null;

        try {
            transaction = session.beginTransaction();
            partesList = session.createQuery("from Partes_incidencia", Partes_incidencia.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return partesList != null ? partesList.toArray(new Partes_incidencia[0]) : new Partes_incidencia[0];
    }

    public Partes_incidencia[] buscarPartesPorId(Alumnos alumno) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<Partes_incidencia> partesList = null;

        try {
            transaction = session.beginTransaction();
            partesList = session.createQuery("from Partes_incidencia where id_alum=:id_alum", Partes_incidencia.class)
                    .setParameter("id_alum", alumno).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return partesList != null ? partesList.toArray(new Partes_incidencia[0]) : new Partes_incidencia[0];
    }

    public List<Partes_incidencia> listarPartes() {
        List<Partes_incidencia> partesIncidencias = new ArrayList<>();
        try {
            session.beginTransaction();
            partesIncidencias = session.createQuery("from Partes_incidencia", Partes_incidencia.class)
                    .stream().toList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return partesIncidencias;
    }

    public Partes_incidencia[] buscarPorFecha(LocalDate fechaEmpieza, LocalDate fechaAcaba) {
        List<Partes_incidencia> listaPartes = listarPartes();
        if (fechaEmpieza == null && fechaAcaba != null) {
            listaPartes = listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isBefore(fechaAcaba))
                    .toList();
        } else if (fechaAcaba == null && fechaEmpieza != null) {
            listaPartes = listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isAfter(fechaEmpieza))
                    .toList();
        } else if (fechaAcaba != null && fechaEmpieza != null) {
            listaPartes = listaPartes.stream()
                    .filter(partesIncidencia ->
                            partesIncidencia.getFecha().isAfter(fechaEmpieza) &&
                                    partesIncidencia.getFecha().isBefore(fechaAcaba))
                    .toList();
        }
        return listaPartes.toArray(new Partes_incidencia[0]);
    }

    public void eliminarParte(Partes_incidencia parte) {
        try {
            session.beginTransaction();
            session.delete(parte);
            session.getTransaction().commit();
            modificarAlumno(parte.getId_alum());
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
    }

    public boolean modificarParte(Partes_incidencia parte) {
        boolean modificado = false;
        try {
            session.beginTransaction();
            session.update(parte);
            session.getTransaction().commit();
            modificarAlumno(parte.getId_alum());
            modificado = true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.clear();
        }
        return modificado;
    }

    public void modificarAlumno(Alumnos alumno) {
        AlumnoDAO dao = new AlumnoDAO();
        dao.modificarAlumno(alumno);
    }

}
