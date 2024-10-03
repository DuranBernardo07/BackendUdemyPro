package plaxi.backend.dto;

public class TemaDto {

    private Long idTema;
    private String titulo;
    private int orden;
    private String descripcion;
    private String recursoMultimedia;
    private Long leccionId;  // Solo el ID de la lección a la que pertenece el tema

    public TemaDto() {}

    public TemaDto(Long idTema, String titulo, int orden, String descripcion, String recursoMultimedia, Long leccionId) {
        this.idTema = idTema;
        this.titulo = titulo;
        this.orden = orden;
        this.descripcion = descripcion;
        this.recursoMultimedia = recursoMultimedia;
        this.leccionId = leccionId;
    }

    // Getters y Setters
    public Long getIdTema() {
        return idTema;
    }

    public void setIdTema(Long idTema) {
        this.idTema = idTema;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRecursoMultimedia() {
        return recursoMultimedia;
    }

    public void setRecursoMultimedia(String recursoMultimedia) {
        this.recursoMultimedia = recursoMultimedia;
    }

    public Long getLeccionId() {
        return leccionId;
    }

    public void setLeccionId(Long leccionId) {
        this.leccionId = leccionId;
    }
}

