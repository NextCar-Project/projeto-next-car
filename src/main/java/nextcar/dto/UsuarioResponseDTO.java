package nextcar.dto;

import lombok.Getter;
import lombok.Setter;
import nextcar.model.Usuario;

@Getter
@Setter
public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String login;

    public UsuarioResponseDTO() {}

    public UsuarioResponseDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.login = usuario.getLogin();
    }
}
