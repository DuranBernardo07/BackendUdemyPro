package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Curso;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-03T00:19:37", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(ConocimientoPrevio.class)
public class ConocimientoPrevio_ { 

    public static volatile SingularAttribute<ConocimientoPrevio, String> descripcion;
    public static volatile SingularAttribute<ConocimientoPrevio, Curso> curso;
    public static volatile SingularAttribute<ConocimientoPrevio, Long> idConocimiento;

}