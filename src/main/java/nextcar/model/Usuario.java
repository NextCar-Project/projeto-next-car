package nextcar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Nome obrigatorio")
    private String nome;
    @Email
    @NotNull(message = "Login obrigatorio")
    private String login;
    @NotNull(message = "Senha obrigatoria")
    private String senha;

    public  Usuario() {
    }
    public Usuario(String nome, String login, String senha) {}
}
