package dao;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import entidade.Projeto;
import entidade.Requisito;
import dao.ProjetoDAO;
import dao.RequisitoDAO;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfDAO {

    public static void exportarProjetoERequisitosParaPDF(int projetoId, String caminhoPDF) throws FileNotFoundException {
        Document document = null;
        try {
            document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(caminhoPDF));
            document.open();

            Projeto projeto = ProjetoDAO.obterProjetoPorId(projetoId);
            if (projeto != null) {
                adicionarParagrafo(document, "PROJETO E REQUISITOS:");
                adicionarParagrafo(document, " ");
                adicionarParagrafo(document, "Projeto: " + projeto.getNome_projeto());
                adicionarParagrafo(document, "Descrição do Projeto: " + projeto.getDescricao());
                adicionarParagrafo(document, "Data de Início: " + projeto.getData_inicio());
                adicionarParagrafo(document, "Data de Conclusao: " + projeto.getData_fim());
                adicionarParagrafo(document, "Prioridade: " + projeto.getPrioridade());
                adicionarParagrafo(document, "Status Do Projeto: " + projeto.getStatus_projeto());

                adicionarParagrafo(document, "\nRequisitos:");

                List<Requisito> requisitos = RequisitoDAO.listarRequisitosPorProjeto(projetoId);
                for (Requisito requisito : requisitos) {
                    adicionarParagrafo(document, "Codigo: " + requisito.getId_requisito());
                    adicionarParagrafo(document, "Nome: " + requisito.getNome_requisito());
                    adicionarParagrafo(document, "Descrição: " + requisito.getDescricao());
                    adicionarParagrafo(document, "Prioridade: " + requisito.getPrioridade());
                    adicionarParagrafo(document, "Complexidade: " + requisito.getComplexidade());
                    adicionarParagrafo(document, "Versao: " + requisito.getVersao());
                    adicionarParagrafo(document, "Tipo: " + requisito.getTipo());
                    adicionarParagrafo(document, "Codigo do Projeto: " + requisito.getId_projeto());
                    adicionarParagrafo(document, "-----------------------------------");
                }
            } else {
                adicionarParagrafo(document, "Projeto não encontrado.");
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            if (document != null) {
                document.close();
            }
        }
    }

    private static void adicionarParagrafo(Document document, String texto) throws DocumentException {
        document.add(new Paragraph(texto));
    }
}