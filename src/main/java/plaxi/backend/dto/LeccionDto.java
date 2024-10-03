package plaxi.backend.dto;

public class LeccionDto {

    private Long idLeccion;
    private String titulo;
    private int orden;
    private int duracionEstimada;
    private String contenido;
    private Long cursoId;  // Solo el ID del curso al que pertenece la lección

    public LeccionDto() {}

    public LeccionDto(Long idLeccion, String titulo, int orden, int duracionEstimada, String contenido, Long cursoId) {
        this.idLeccion = idLeccion;
        this.titulo = titulo;
        this.orden = orden;
        this.duracionEstimada = duracionEstimada;
        this.contenido = contenido;
        this.cursoId = cursoId;
    }

    // Getters y Setters
    public Long getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(Long idLeccion) {
        this.idLeccion = idLeccion;
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

    public int getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(int duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }
}
