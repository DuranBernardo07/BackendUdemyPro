package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Curso;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-03T00:19:37", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Leccion.class)
public class Leccion_ { 

    public static volatile SingularAttribute<Leccion, String> contenido;
    public static volatile SingularAttribute<Leccion, Curso> curso;
    public static volatile SingularAttribute<Leccion, String> titulo;
    public static volatile SingularAttribute<Leccion, Integer> orden;
    public static volatile SingularAttribute<Leccion, Long> idLeccion;
    public static volatile SingularAttribute<Leccion, Integer> duracionEstimada;

}