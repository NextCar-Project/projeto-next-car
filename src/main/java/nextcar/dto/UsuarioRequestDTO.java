package nextcar.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRequestDTO {

    @NotBlank(message = "Nome obrigatorio")
    private String nome;

    @Email
    @NotBlank(message = "Login obrigatorio")
    private String login;

    @NotBlank(message = "Senha obrigatoria")
    private String senha;

    public UsuarioRequestDTO() {}

    public UsuarioRequestDTO(String nome, String login, String senha) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }
}
