/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lucas
 */
import apoio.ConexaoBD;
import entidade.Projeto;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjetoDAO {

    public boolean salvar(Projeto p) {
        try {
            PreparedStatement st = ConexaoBD.getInstance().getConnection().prepareStatement(
                    "INSERT INTO projeto (NOME_PROJETO, NOME_GERENTE, DESCRICAO,"
                    + " DATA_INICIO, DATA_FIM, PRIORIDADE, STATUS_PROJETO) "
                    + "VALUES (?, ?, ?, ?::date, ?::date, ?, ?)");

            st.setString(1, p.getNome_projeto());
            st.setString(2, p.getNome_gerente());
            st.setString(3, p.getDescricao());
            st.setString(4, p.getData_inicio());
            st.setString(5, p.getData_fim());
            st.setString(6, p.getPrioridade());
            st.setString(7, p.getStatus_projeto());

            st.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Projeto: " + e);
            return false;
        }
    }

    public ArrayList consultar() {
        ArrayList<Projeto> categorias = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from projeto";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Projeto p = new Projeto();

                p.setId_projeto(resultado.getInt("id_projeto"));
                p.setNome_projeto(resultado.getString("nome_projeto"));
                p.setNome_gerente(resultado.getString("nome_gerente"));
                p.setDescricao(resultado.getString("descricao"));
                p.setData_inicio(resultado.getString("data_inicio"));
                p.setData_fim(resultado.getString("data_fim"));
                p.setPrioridade(resultado.getString("prioridade"));
                p.setStatus_projeto(resultado.getString("status_projeto"));

                categorias.add(p);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consular Projeto: " + e);
        }

        return categorias;
    }

    public Projeto consultar(int codigo) {
        Projeto projeto = new Projeto();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from projeto "
                    + "where "
                    + "id_projeto = " + codigo;

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            resultado.next();

            projeto.setId_projeto(resultado.getInt("id_projeto"));
            projeto.setNome_projeto(resultado.getString("nome_projeto"));
            projeto.setNome_gerente(resultado.getString("nome_gerente"));
            projeto.setDescricao(resultado.getString("descricao"));
            projeto.setData_inicio(resultado.getString("data_inicio"));
            projeto.setData_fim(resultado.getString("data_fim"));
            projeto.setPrioridade(resultado.getString("prioridade"));
            projeto.setStatus_projeto(resultado.getString("status_projeto"));

        } catch (Exception e) {
            System.out.println("Erro ao consultar UM Projeto: " + e);
        }

        return projeto;
    }

    public boolean excluir(int codigo) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "delete from projeto "
                    + "where "
                    + "id_projeto = " + codigo;

            System.out.println("SQL: " + sql);

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir Projeto: " + e);
            return false;
        }
    }

    public boolean atualizar(Projeto p) {
        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = "UPDATE projeto "
                    + "SET nome_projeto = '" + p.getNome_projeto() + "', "
                    + "nome_gerente = '" + p.getNome_gerente() + "', "
                    + "descricao = '" + p.getDescricao() + "', "
                    + "data_inicio = '" + p.getData_inicio() + "', "
                    + "data_fim = '" + p.getData_fim() + "', "
                    + "prioridade = '" + p.getPrioridade() + "', "
                    + "status_projeto = '" + p.getStatus_projeto() + "' "
                    + "WHERE id_projeto = " + p.getId_projeto();

            System.out.println("SQL: " + sql);

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar Projeto: " + e);
            return false;
        }
    }

    public static Projeto obterProjetoPorId(int projetoId) {
        Projeto projeto = null;

        try {
            PreparedStatement st = ConexaoBD.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM projeto WHERE id_projeto = ?"
            );
            st.setInt(1, projetoId);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                projeto = new Projeto();
                projeto.setId_projeto(resultado.getInt("id_projeto"));
                projeto.setNome_projeto(resultado.getString("nome_projeto"));
                projeto.setNome_gerente(resultado.getString("nome_gerente"));
                projeto.setDescricao(resultado.getString("descricao"));
                projeto.setData_inicio(resultado.getString("data_inicio"));
                projeto.setData_fim(resultado.getString("data_fim"));
                projeto.setPrioridade(resultado.getString("prioridade"));
                projeto.setStatus_projeto(resultado.getString("status_projeto"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projeto;
    }
}
