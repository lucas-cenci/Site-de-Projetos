
package entidade;

/**
 *
 * @author Lucas
 */
public class Admin {                   // REGIAO DOS GETTERS E SETTERS
    
    private int codigo;
    private String usuario;
    private String senha;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
}
