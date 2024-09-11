package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-10T23:19:17", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Rol.class)
public class Rol_ { 

    public static volatile SingularAttribute<Rol, Integer> idRol;
    public static volatile CollectionAttribute<Rol, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Rol, String> nombre;
    public static volatile SingularAttribute<Rol, Boolean> status;

}