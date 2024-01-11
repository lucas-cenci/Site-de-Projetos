/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import dao.AdminDAO;
import dao.RequisitoDAO;
import entidade.Admin;
import entidade.Requisito;
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
public class acaod extends HttpServlet {

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
            out.println("<title>Servlet acaod</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Servlet acaod at " + request.getContextPath() + "</h2>");
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

        String id_requisito = request.getParameter("id_requisito");
        String id_projeto = request.getParameter("id_projeto");
        String nome_requisito = request.getParameter("nome_requisito");
        String descricao = request.getParameter("descricao");
        String prioridade = request.getParameter("prioridade");
        String complexidade = request.getParameter("complexidade");
        String versao = request.getParameter("versao");
        String tipo = request.getParameter("tipo");


        System.out.println("Codigo: " + id_requisito);
        System.out.println("Codigo do Projeto: " + id_projeto);
        System.out.println("Nome do Requisito: " + nome_requisito);
        System.out.println("Descricao: " + descricao);
        System.out.println("Prioridade: " + prioridade);
        System.out.println("Complexidade: " + complexidade);
        System.out.println("Versao: " + versao);
        System.out.println("Tipo: " + tipo);

        // -------------------------------------------------------------------
        String a = request.getParameter("a");

        if (a.equals("editarCateg")) {
            String id = request.getParameter("id");
            int codigo = Integer.parseInt(id);

            Requisito requisito = new RequisitoDAO().consultar(codigo);

            request.setAttribute("requisito", requisito);

            encaminharPagina("admintela.jsp", request, response);
        }

        if (a.equals("excluirCateg")) {
            String id = request.getParameter("id");
            int codigo = Integer.parseInt(id);

            if (new RequisitoDAO().excluir(codigo)) {
                request.setAttribute("sucesso", true);
                request.setAttribute("mensagem", "Cadastro excluído com sucesso.");
            } else {
                request.setAttribute("sucesso", false);
                request.setAttribute("mensagem", "Erro ao excluir cadastro.");
            }

            encaminharPagina("admintela.jsp", request, response);
        }

        if (a.equals("consultarCateg")) {
            ArrayList<Requisito> categorias = new RequisitoDAO().consultar(); // Chamando o método consultar() do RequisitoDAO

            if (!categorias.isEmpty()) {
                // Exibe no output
                System.out.println("---- Lista de Requisitos ----");
                for (Requisito requisito : categorias) {
                    System.out.println("Codigo: " + requisito.getId_requisito()
                            + ", Codigo do Projeto: " + requisito.getId_projeto()
                            + ", Nome do Requisito: " + requisito.getNome_requisito()
                            + ", Descricao: " + requisito.getDescricao()
                            + ", Prioridade: " + requisito.getPrioridade()
                            + ", Complexidade " + requisito.getComplexidade()
                            + ", Versao: " + requisito.getVersao()
                            + ", Tipo: " + requisito.getTipo());
                }

                request.setAttribute("sucesso", true);
                request.setAttribute("mensagem", "Consulta realizada com sucesso.");
                request.setAttribute("regss", categorias);
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
        String acaod = request.getParameter("a");
        
        if ("autenticar".equals(acaod)) {
            String usuario = request.getParameter("usuario");
            String senha = request.getParameter("senha");
           
            Admin a = new Admin();
            a.setUsuario(usuario);
            a.setSenha(senha);

            AdminDAO adminDAO = new AdminDAO();

            if (adminDAO.autenticar(usuario, senha)) {
                response.sendRedirect("admintela.jsp");
            } else {
                request.setAttribute("erro", "true");
                request.getRequestDispatcher("loginadm.jsp").forward(request, response);
            }
        } else if (acaod.equals("salvarCateg")) {
            String id_requisito = request.getParameter("id_requisito");
            String id_projeto = request.getParameter("id_projeto");
            String nome_requisito = request.getParameter("nome_requisito");
            String descricao = request.getParameter("descricao");
            String prioridade = request.getParameter("prioridade");
            String complexidade = request.getParameter("complexidade");
            String versao = request.getParameter("versao");
            String tipo = request.getParameter("tipo");

            int id = Integer.parseInt(id_requisito);
            Requisito requisito = new Requisito();          
            requisito.setId_requisito(id);
            requisito.setId_projeto(Integer.parseInt(id_projeto));
            requisito.setNome_requisito(nome_requisito);
            requisito.setDescricao(descricao);
            requisito.setPrioridade(prioridade);
            requisito.setComplexidade(complexidade);
            requisito.setVersao(versao);
            requisito.setTipo(tipo);

            if (id == 0) {
                if (new RequisitoDAO().salvar(requisito)) {
                    request.setAttribute("sucesso", true);
                    request.setAttribute("mensagem", "Requisito salvo com sucesso.");
                } else {
                    request.setAttribute("sucesso", false);
                    request.setAttribute("mensagem", "Erro ao salvar Requisito.");
                }
            } else {
                if (new RequisitoDAO().atualizar(requisito)) {
                    request.setAttribute("sucesso", true);
                    request.setAttribute("mensagem", "Requisito atualizado com sucesso.");
                } else {
                    request.setAttribute("sucesso", false);
                    request.setAttribute("mensagem", "Erro ao atualizar Requisito.");
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

