package plaxi.backend.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "Curso", catalog = "PlaxiDB", schema = "public")
public class Curso implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    // Relación con la portada, puede ser nula
    @JoinColumn(name = "portada", referencedColumnName = "s3_object_id", nullable = true)
    @ManyToOne(fetch = FetchType.LAZY)
    private S3Object portada;  // Este campo puede ser null inicialmente

    @Column(name = "dificultad", nullable = false)
    private String dificultad;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id_categoria", referencedColumnName = "id_categoria", nullable = false)
    private Categoria categoria;

    

    public Curso() {
    }

    //sin portada
    public Curso(String nombre, String descripcion, String dificultad, Boolean estado, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.estado = estado;
        this.categoria = categoria;
    }

    //con portada
    public Curso(String nombre, String descripcion, String dificultad, Boolean estado, Categoria categoria, S3Object portada) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.estado = estado;
        this.categoria = categoria;
        this.portada = portada;
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

    public S3Object getPortada(){
        return portada;
    }

    public void setPortada(S3Object portada){
        this.portada = portada;
    }
}
