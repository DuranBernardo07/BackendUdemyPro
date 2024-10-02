package plaxi.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CursoDto {
    private Long idCurso;
    private String nombre;
    private String descripcion;
    private String portada;
    private String dificultad;
    private Boolean estado;
}
