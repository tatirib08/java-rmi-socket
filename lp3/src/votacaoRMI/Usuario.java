package votacaoRMI;

import java.io.Serializable;

public class Usuario implements Serializable
{

    public static final long serialVersionUID = 1234L;
    private String nome;
    private String senha;

    public Usuario(String nomeUser, String senhaUser)
    {
        this.nome = nomeUser;
        this.senha = senhaUser;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
