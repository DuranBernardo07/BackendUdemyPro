package plaxi.backend.dto;

import org.springframework.web.multipart.MultipartFile;

public class ActualizarPerfilDto {
    private Long idUsuario;
    private String username;
    private String gmail;
    private boolean status;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String telefono;
    private String ci;
    private MultipartFile file;

    public ActualizarPerfilDto() {
    }

    public ActualizarPerfilDto(Long idUsuario, String username, String gmail, boolean status, String nombre, String primerApellido, String segundoApellido, String telefono, String ci, MultipartFile file) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.gmail = gmail;
        this.status = status;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.telefono = telefono;
        this.ci = ci;
        this.file = file;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ActualizarPerfilDto{" +
                "idUsuario=" + idUsuario +
                ", username='" + username + '\'' +
                ", gmail='" + gmail + '\'' +
                ", status=" + status +
                ", nombre='" + nombre + '\'' +
                ", primerApellido='" + primerApellido + '\'' +
                ", segundoApellido='" + segundoApellido + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ci='" + ci + '\'' +
                ", file=" + file +
                '}';
    }
    
}
