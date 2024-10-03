package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Leccion;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-03T00:14:34", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Tema.class)
public class Tema_ { 

    public static volatile SingularAttribute<Tema, String> descripcion;
    public static volatile SingularAttribute<Tema, String> recursoMultimedia;
    public static volatile SingularAttribute<Tema, Leccion> leccion;
    public static volatile SingularAttribute<Tema, Long> idTema;
    public static volatile SingularAttribute<Tema, String> titulo;
    public static volatile SingularAttribute<Tema, Integer> orden;

}