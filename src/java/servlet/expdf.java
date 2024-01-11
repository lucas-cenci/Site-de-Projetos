package servlet;

import dao.PdfDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.awt.Desktop;

import java.io.File;
import java.io.IOException;

@WebServlet("/pdf")
public class expdf extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String projetoIdString = request.getParameter("projetoId");

        if (projetoIdString != null && !projetoIdString.isEmpty()) {
            int projetoId = Integer.parseInt(projetoIdString);
            String caminhoPDF = "arquivo.pdf";  // Substitua pelo caminho desejado

            PdfDAO.exportarProjetoERequisitosParaPDF(projetoId, caminhoPDF);

            // Abre o PDF no visualizador padrão
            abrirPDFNoVisualizadorPadrao(caminhoPDF);

            response.getWriter().println("Sucesso!");
        } else {
            response.getWriter().println("ID do Projeto inválido.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/usuariotela.jsp").forward(request, response);
    }

    private void abrirPDFNoVisualizadorPadrao(String caminhoPDF) {
        try {
            File arquivoPDF = new File(caminhoPDF);
            Desktop.getDesktop().open(arquivoPDF);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}