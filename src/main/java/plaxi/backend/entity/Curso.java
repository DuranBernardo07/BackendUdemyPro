package plaxi.backend.entity;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Curso")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    // Relación con la entidad S3Object (portada)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portada", referencedColumnName = "s3_object_id", nullable = true)  // Relación con la portada
    private S3Object portada;

    @Column(name = "dificultad", nullable = false)
    private String dificultad;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    // Relación con la categoría
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id_categoria", referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;

    // Constructor vacío
    public Curso() {}

    // Constructor con todos los parámetros
    public Curso(Long idCurso, String nombre, String descripcion, S3Object portada, String dificultad, Boolean estado, Categoria categoria) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.portada = portada;
        this.dificultad = dificultad;
        this.estado = estado;
        this.categoria = categoria;
    }

    // Getters y Setters
    public Long getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Long idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public S3Object getPortada() {
        return portada;
    }

    public void setPortada(S3Object portada) {
        this.portada = portada;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
