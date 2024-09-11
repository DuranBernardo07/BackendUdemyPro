package plaxi.backend.entity;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import plaxi.backend.entity.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-09-10T23:19:17", comments="EclipseLink-2.7.9.v20210604-rNA")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> primerApellido;
    public static volatile SingularAttribute<Persona, String> ci;
    public static volatile SingularAttribute<Persona, String> segundoApellido;
    public static volatile SingularAttribute<Persona, String> telefono;
    public static volatile CollectionAttribute<Persona, Usuario> usuarioCollection;
    public static volatile SingularAttribute<Persona, String> nombre;
    public static volatile SingularAttribute<Persona, Integer> idPersona;

}