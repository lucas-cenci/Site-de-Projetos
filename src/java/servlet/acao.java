/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import dao.UsuarioDAO;
import dao.ProjetoDAO;
import entidade.Projeto;
import entidade.Usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Univates - EAD
 *
 * @author Lucas Cenci Beltrame
 */
public class acao extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet acao</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet acao at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        processRequest(request, response);

        System.out.println("Estou no GET.");

        String id_projeto = request.getParameter("id_projeto");
        String nome_projeto = request.getParameter("nome_projeto");
        String nome_gerente = request.getParameter("nome_gerente");
        String descricao = request.getParameter("descricao");
        String data_inicio = request.getParameter("data_inicio");
        String data_fim = request.getParameter("data_fim");
        String prioridade = request.getParameter("prioridade");
        String status_projeto = request.getParameter("status_projeto");

        System.out.println("Codigo: " + id_projeto);
        System.out.println("Nome do Projeto: " + nome_projeto);
        System.out.println("Nome do Gerente: " + nome_gerente);
        System.out.println("Descricao: " + descricao);
        System.out.println("Data Inicio: " + data_inicio);
        System.out.println("Data Fim: " + data_fim);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Status do Projeto: " + status_projeto);

        // -------------------------------------------------------------------
        String a = request.getParameter("a");

        if (a.equals("editarCateg")) {
            String id = request.getParameter("id");
            int codigo = Integer.parseInt(id);

            Projeto projeto = new ProjetoDAO().consultar(codigo);

            request.setAttribute("projeto", projeto);

            encaminharPagina("admintela.jsp", request, response);
        }

        if (a.equals("excluirCateg")) {
            String id = request.getParameter("id");
            int codigo = Integer.parseInt(id);

            if (new ProjetoDAO().excluir(codigo)) {
                request.setAttribute("sucesso", true);
                request.setAttribute("mensagem", "Cadastro excluído com sucesso.");
            } else {
                request.setAttribute("sucesso", false);
                request.setAttribute("mensagem", "Erro ao excluir cadastro.");
            }

            encaminharPagina("admintela.jsp", request, response);
        }

        if (a.equals("consultarCateg")) {
            ArrayList<Projeto> categorias = new ProjetoDAO().consultar(); // Chamando o método consultar() do ProjetoDAO

            if (!categorias.isEmpty()) {
                // Exibe no output
                System.out.println("---- Lista de Projeto ----");
                for (Projeto projeto : categorias) {
                    System.out.println("Codigo: " + projeto.getId_projeto()
                            + ", Nome do Projeto: " + projeto.getNome_projeto()
                            + ", Nome do Gerente: " + projeto.getNome_gerente()
                            + ", Descricao: " + projeto.getDescricao()
                            + ", Data Inicio: " + projeto.getData_inicio()
                            + ", Data Fim: " + projeto.getData_fim()
                            + ", Prioridade: " + projeto.getPrioridade()
                            + ", Status do Projeto: " + projeto.getStatus_projeto());
                }

                request.setAttribute("sucesso", true);
                request.setAttribute("mensagem", "Consulta realizada com sucesso.");
                request.setAttribute("categs", categorias);
            } else {

                request.setAttribute("sucesso", false);
                request.setAttribute("mensagem", "Nenhuma consulta encontrada.");
            }

            encaminharPagina("admintela.jsp", request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("a");

        if ("autenticar".equals(acao)) {
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");

            Usuario u = new Usuario();
            u.setUsuario(usuario);
            u.setSenha(senha);

            UsuarioDAO usuarioDAO = new UsuarioDAO();

            if (usuarioDAO.autenticar(usuario, senha)) {
                response.sendRedirect("usuariotela.jsp");
            } else {
                request.setAttribute("erro", "true");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else if (acao.equals("salvarCateg")) {
            String id_projeto = request.getParameter("id_projeto");
            String nome_projeto = request.getParameter("nome_projeto");
            String nome_gerente = request.getParameter("nome_gerente");
            String descricao = request.getParameter("descricao");
            String data_inicio = request.getParameter("data_inicio");
            String data_fim = request.getParameter("data_fim");
            String prioridade = request.getParameter("prioridade");
            String status_projeto = request.getParameter("status_projeto");

            int id = Integer.parseInt(id_projeto);
            Projeto projeto = new Projeto();
            projeto.setId_projeto(id);
            projeto.setNome_projeto(nome_projeto);
            projeto.setNome_gerente(nome_gerente);
            projeto.setDescricao(descricao);
            projeto.setData_inicio(data_inicio);
            projeto.setData_fim(data_fim);
            projeto.setPrioridade(prioridade);
            projeto.setStatus_projeto(status_projeto);

            if (id == 0) {
                if (new ProjetoDAO().salvar(projeto)) {
                    request.setAttribute("sucesso", true);
                    request.setAttribute("mensagem", "Projeto salvo com sucesso.");
                } else {
                    request.setAttribute("sucesso", false);
                    request.setAttribute("mensagem", "Erro ao salvar Projeto.");
                }
            } else {
                if (new ProjetoDAO().atualizar(projeto)) {
                    request.setAttribute("sucesso", true);
                    request.setAttribute("mensagem", "Projeto atualizado com sucesso.");
                } else {
                    request.setAttribute("sucesso", false);
                    request.setAttribute("mensagem", "Erro ao atualizar Projeto.");
                }
            }

            // Encaminhar para categoria.jsp
            request.getRequestDispatcher("admintela.jsp").forward(request, response);
        }      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void encaminharPagina(String pagina, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher(pagina);
            rd.forward(request, response);
        } catch (Exception e) {
            System.out.println("Erro ao encaminhar pagina: " + e);
        }
    }
}
