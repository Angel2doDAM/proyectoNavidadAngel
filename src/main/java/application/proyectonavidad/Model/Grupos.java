package application.proyectonavidad.Model;

/*
DROP TABLE IF EXISTS `gestionpartes`.`grupos`;
CREATE TABLE IF NOT EXISTS `gestionpartes`.`grupos` (
  `id_grupo` INT NOT NULL AUTO_INCREMENT,
  `nombre_grupo` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id_grupo`))
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_general_ci;
 */

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grupos")
public class Grupos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grupo")
    private int id_grupo;

    @Column(name = "nombre_grupo")
    private String nombre_grupo;

    @OneToMany(mappedBy = "id_alum", cascade = CascadeType.ALL)
    Set<Alumnos> alumnos = new HashSet<>();

    @OneToMany(mappedBy = "id_parte", cascade = CascadeType.ALL)
    Set<Partes_incidencia> partesIncidencias = new HashSet<>();

    public Grupos() {
    }

    public Grupos(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public int getId_grupo() {
        return id_grupo;
    }

    public void setId_grupo(int id_grupo) {
        this.id_grupo = id_grupo;
    }

    public String getNombre_grupo() {
        return nombre_grupo;
    }

    public void setNombre_grupo(String nombre_grupo) {
        this.nombre_grupo = nombre_grupo;
    }

    public Set<Alumnos> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(Set<Alumnos> alumnos) {
        this.alumnos = alumnos;
    }

    public Set<Partes_incidencia> getPartesIncidencias() {
        return partesIncidencias;
    }

    public void setPartesIncidencias(Set<Partes_incidencia> partesIncidencias) {
        this.partesIncidencias = partesIncidencias;
    }

    @Override
    public String toString() {
        return "Grupos{" +
                ", nombre_grupo='" + nombre_grupo + '\'' +
                '}';
    }
}
