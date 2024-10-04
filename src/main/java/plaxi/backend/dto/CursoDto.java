package plaxi.backend.dto;


public class CursoDto {

    private Long idCurso;
    private String nombre;
    private String descripcion;
    private String dificultad;
    private String portada;
    private Boolean estado;
    private Long categoriaId;  // Solo el ID de la categoría


    public CursoDto() {}

    public CursoDto(Long idCurso, String nombre, String descripcion, String dificultad, String portada, Boolean estado, Long categoriaId) {
        this.idCurso = idCurso;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.portada = portada;
        this.estado = estado;
        this.categoriaId = categoriaId;
    }

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

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
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

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    @Override
    public String toString() {
        return "CursoDto{" +
                "idCurso=" + idCurso +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", portada='" + portada + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", estado=" + estado +
                ", categoriaId=" + categoriaId +
                '}';
    }
}
