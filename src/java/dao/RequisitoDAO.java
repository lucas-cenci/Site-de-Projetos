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
import entidade.Requisito;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequisitoDAO {

    public boolean salvar(Requisito r) {
        try {
            PreparedStatement st = ConexaoBD.getInstance().getConnection().prepareStatement(
                    "INSERT INTO requisito (ID_PROJETO, NOME_REQUISITO, DESCRICAO, PRIORIDADE, COMPLEXIDADE, VERSAO, TIPO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");

            st.setInt(1, r.getId_projeto());
            st.setString(2, r.getNome_requisito());
            st.setString(3, r.getDescricao());
            st.setString(4, r.getPrioridade());
            st.setString(5, r.getComplexidade());
            st.setString(6, r.getVersao());
            st.setString(7, r.getTipo());

            st.executeUpdate();

            return true;
        } catch (Exception e) {
            System.out.println("Erro ao salvar Requisito: " + e);
            return false;
        }
    }

    public ArrayList consultar() {
        ArrayList<Requisito> categorias = new ArrayList<>();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from requisito";

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            while (resultado.next()) {
                Requisito r = new Requisito();

                r.setId_requisito(resultado.getInt("id_requisito"));
                r.setId_projeto(resultado.getInt("id_projeto"));
                r.setNome_requisito(resultado.getString("nome_requisito"));
                r.setDescricao(resultado.getString("descricao"));
                r.setPrioridade(resultado.getString("prioridade"));
                r.setComplexidade(resultado.getString("complexidade"));
                r.setVersao(resultado.getString("versao"));
                r.setTipo(resultado.getString("tipo"));

                categorias.add(r);
            }

        } catch (Exception e) {
            System.out.println("Erro ao consultar Requisito: " + e);
        }

        return categorias;
    }

    public Requisito consultar(int codigo) {
        Requisito requisito = new Requisito();

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "select * from requisito "
                    + "where "
                    + "id_requisito = " + codigo;

            System.out.println("SQL: " + sql);

            ResultSet resultado = st.executeQuery(sql);

            resultado.next();

            requisito.setId_requisito(resultado.getInt("id_requisito"));
            requisito.setId_projeto(resultado.getInt("id_projeto"));
            requisito.setNome_requisito(resultado.getString("nome_requisito"));
            requisito.setDescricao(resultado.getString("descricao"));
            requisito.setPrioridade(resultado.getString("prioridade"));
            requisito.setComplexidade(resultado.getString("complexidade"));
            requisito.setVersao(resultado.getString("versao"));
            requisito.setTipo(resultado.getString("tipo"));

        } catch (Exception e) {
            System.out.println("Erro ao consultar UM Requisito: " + e);
        }

        return requisito;
    }

    public boolean excluir(int codigo) {

        try {
            Statement st = ConexaoBD.getInstance().getConnection().createStatement();

            String sql = ""
                    + "delete from requisito "
                    + "where "
                    + "id_requisito = " + codigo;

            System.out.println("SQL: " + sql);

            st.executeUpdate(sql);

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao excluir Requisito: " + e);
            return false;
        }
    }

    public boolean atualizar(Requisito r) {
        try {
            PreparedStatement st = ConexaoBD.getInstance().getConnection().prepareStatement(
                    "UPDATE requisito "
                    + "SET id_projeto = ?, "
                    + "nome_requisito = ?, "
                    + "descricao = ?, "
                    + "prioridade = ?, "
                    + "complexidade = ?, "
                    + "versao = ?, "
                    + "tipo = ? "
                    + "WHERE id_requisito = ?");

            st.setInt(1, r.getId_projeto());
            st.setString(2, r.getNome_requisito());
            st.setString(3, r.getDescricao());
            st.setString(4, r.getPrioridade());
            st.setString(5, r.getComplexidade());
            st.setString(6, r.getVersao());
            st.setString(7, r.getTipo());
            st.setInt(8, r.getId_requisito());

            st.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Erro ao atualizar Requisito: " + e);
            return false;
        }
    }

    public static Requisito obterRequisitoPorId(int requisitoId) {
        Requisito requisito = null;

        try {
            PreparedStatement st = ConexaoBD.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM requisito WHERE id_requisito = ?"
            );
            st.setInt(1, requisitoId);

            ResultSet resultado = st.executeQuery();

            if (resultado.next()) {
                requisito = new Requisito();
                requisito.setId_requisito(resultado.getInt("id_requisito"));
                requisito.setId_projeto(resultado.getInt("id_projeto"));
                requisito.setNome_requisito(resultado.getString("nome_requisito"));
                requisito.setDescricao(resultado.getString("descricao"));
                requisito.setPrioridade(resultado.getString("prioridade"));
                requisito.setComplexidade(resultado.getString("complexidade"));
                requisito.setVersao(resultado.getString("versao"));
                requisito.setTipo(resultado.getString("tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requisito;
    }

    public static List<Requisito> listarRequisitosPorProjeto(int projetoId) {
        List<Requisito> requisitos = new ArrayList<>();

        try {
            PreparedStatement st = ConexaoBD.getInstance().getConnection().prepareStatement(
                    "SELECT * FROM requisito WHERE id_projeto = ?"
            );
            st.setInt(1, projetoId);

            ResultSet resultado = st.executeQuery();

            while (resultado.next()) {
                Requisito requisito = new Requisito();
                requisito.setId_requisito(resultado.getInt("id_requisito"));
                requisito.setId_projeto(resultado.getInt("id_projeto"));
                requisito.setNome_requisito(resultado.getString("nome_requisito"));
                requisito.setDescricao(resultado.getString("descricao"));
                requisito.setPrioridade(resultado.getString("prioridade"));
                requisito.setComplexidade(resultado.getString("complexidade"));
                requisito.setVersao(resultado.getString("versao"));
                requisito.setTipo(resultado.getString("tipo"));

                requisitos.add(requisito);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return requisitos;
    }

}
