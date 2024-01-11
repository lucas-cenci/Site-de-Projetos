/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lucas
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import apoio.ConexaoBD;

public class UsuarioDAO {

    public boolean autenticar(String usuario, String senha) {
        try {
            Connection conexao = ConexaoBD.getInstance().getConnection();
            String sql = "SELECT * FROM usuario WHERE usuario = ? AND senha = ?";
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setString(2, senha);

            ResultSet resultado = stmt.executeQuery();
            
            if (resultado.next()) {
                // Nome, email e telefone corretos
                return true;
            } else {
                // Nome, email e telefone incorretos
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao autenticar Pessoa: " + e);
            return false;
        }
    }
}