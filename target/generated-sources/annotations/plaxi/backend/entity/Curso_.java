package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Categoria;
import plaxi.backend.entity.S3Object;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-03T00:19:47", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Curso.class)
public class Curso_ { 

    public static volatile SingularAttribute<Curso, String> descripcion;
    public static volatile SingularAttribute<Curso, Boolean> estado;
    public static volatile SingularAttribute<Curso, Categoria> categoria;
    public static volatile SingularAttribute<Curso, Long> idCurso;
    public static volatile SingularAttribute<Curso, String> nombre;
    public static volatile SingularAttribute<Curso, S3Object> portada;
    public static volatile SingularAttribute<Curso, String> dificultad;

}