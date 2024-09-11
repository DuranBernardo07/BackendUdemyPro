package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Persona;
import plaxi.backend.entity.Rol;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-10T22:34:06", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> gmail;
    public static volatile SingularAttribute<Usuario, Rol> rolId;
    public static volatile SingularAttribute<Usuario, String> password;
    public static volatile SingularAttribute<Usuario, Integer> idUsuario;
    public static volatile SingularAttribute<Usuario, Persona> personaId;
    public static volatile SingularAttribute<Usuario, String> username;
    public static volatile SingularAttribute<Usuario, Boolean> status;

}