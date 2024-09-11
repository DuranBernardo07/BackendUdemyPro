package plaxi.backend.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import plaxi.backend.dto.PersonaDto;
import plaxi.backend.dto.UsuarioDto;
import plaxi.backend.entity.Persona;
import plaxi.backend.entity.Usuario;
import plaxi.backend.repository.UsuarioRepository;
import plaxi.backend.repository.RolRepository;
import plaxi.backend.repository.PersonaRepository;
import jakarta.security.auth.message.AuthException;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PersonaRepository personaRepository;

    public Usuario registerUser(UsuarioDto usuarioDto, PersonaDto personaDto) throws AuthException {
        if (usuarioRepository.findByUsername(usuarioDto.getUsername()).isPresent()) {
            throw new AuthException("El nombre de usuario ya está en uso.");
        }

        String encryptedPassword = BCrypt.hashpw(usuarioDto.getPassword(), BCrypt.gensalt());

        Persona persona = new Persona();
        persona.setNombre(personaDto.getNombre());
        persona.setPrimerApellido(personaDto.getPrimer_apellido());
        persona.setSegundoApellido(personaDto.getSegundo_apellido());
        persona.setTelefono(personaDto.getTelefono());
        persona.setCi(personaDto.getCi());

        Persona savedPersona = personaRepository.save(persona);

        Usuario usuario = new Usuario();
        usuario.setUsername(usuarioDto.getUsername());
        usuario.setPassword(encryptedPassword);
        usuario.setGmail(usuarioDto.getGmail());
        usuario.setStatus(true);
        usuario.setPersonaId(savedPersona);
        usuario.setRolId(rolRepository.findById(usuarioDto.getId_rol())
                .orElseThrow(() -> new AuthException("Rol no encontrado")));

        return usuarioRepository.save(usuario);
    }

    public Usuario loginUser(String usernameOrEmail, String password) throws AuthException {
        Usuario usuario = usuarioRepository.findByUsernameOrGmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new AuthException("Usuario no encontrado"));

        if (!BCrypt.checkpw(password, usuario.getPassword())) {
            throw new AuthException("Contraseña incorrecta.");
        }

        return usuario;
    }
}
