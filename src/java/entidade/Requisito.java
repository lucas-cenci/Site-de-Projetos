package entidade;

/**
 *
 * @author Lucas
 */
public class Requisito {                   // REGIAO DOS GETTERS E SETTERS

    private int id_requisito;
    private int id_projeto;
    private String nome_requisito;
    private String descricao;
    private String prioridade;
    private String complexidade;
    private String versao;
    private String tipo;

    public int getId_requisito() {
        return id_requisito;
    }

    public void setId_requisito(int id_requisito) {
        this.id_requisito = id_requisito;
    }

    public int getId_projeto() {
        return id_projeto;
    }

    public void setId_projeto(int id_projeto) {
        this.id_projeto = id_projeto;
    }

    public String getNome_requisito() {
        return nome_requisito;
    }

    public void setNome_requisito(String nome_requisito) {
        this.nome_requisito = nome_requisito;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getComplexidade() {
        return complexidade;
    }

    public void setComplexidade(String complexidade) {
        this.complexidade = complexidade;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
