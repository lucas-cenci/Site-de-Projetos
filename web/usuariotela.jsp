<%-- 
    Document   : concessionaria
    Created on : 13 de out. de 2023, 15:18:12
    Author     : Lucas
--%>

<%@page import="dao.ProjetoDAO"%>
<%@page import="entidade.Projeto"%>
<%@page import="dao.RequisitoDAO"%>
<%@page import="entidade.Requisito"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Marca D`agua */
            .watermark {
                position: absolute;
                bottom: 10px;
                right: 10px;
                color: #000000;
                font-size: 20px;
                font-weight: bold;
            }

            /* Menu */
            ul {
                list-style-type: none;
                margin: 0;
                padding: 0;
                overflow: hidden;
                background-color: #333;
            }

            li {
                float: left;
            }

            li a {
                display: block;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

            li a:hover:not(.active) {
                background-color: #111;
            }

            .active {
                background-color: #04AA6D;
            }

            /* Estilos para o header */
            header {
                background-color: #333;
                color: white;
                text-align: center;
                padding: 20px 0;
            }

            .menu {
                list-style-type: none;
                padding: 0;
                margin: 0;
                overflow: hidden;
            }

            .menu li {
                float: left;
                margin-right: 20px;
            }

            .menu li a {
                text-decoration: none;
                color: white;
                font-weight: bold;
            }

            .menu li a:hover {
                border-bottom: 2px solid #04AA6D;
            }


            /* Estilos para a tabela de listagem */
            .list-section {
                padding: 20px;
                margin: 20px;
            }

            .table {
                width: 100%;
                border-collapse: collapse;
            }

            .table th, .table td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ccc;
            }

            .table th {
                background-color: #333;
                color: white;
            }

            /* Estilos para o rodapé */
            footer {
                background-color: #333;
                color: white;
                text-align: right;
                padding: 10px;
            }
        </style>
    </head>
    <body>
        <ul>
            <li><a href="login.jsp">Sair</a></li>
            <li><a href="pdf.jsp">Baixar PDF</a></li>
            <li><a href="loginadm.jsp">Login do Admin</a></li>
            <li style="float:right"><a class="active" href="#" id="reloadLink">Recarregar</a></li>
        </ul>

        <h3>Listagem dos Projetos</h3>
        <% ArrayList<Projeto> categs = new ProjetoDAO().consultar(); %>

        <table class="table">
            <tr>
                <th>Código</th>
                <th>Nome do Projeto</th>
                <th>Nome do Gerente</th>
                <th>Descricao</th>
                <th>Data de Inicio</th>
                <th>Data de Conclusao</th>              
                <th>Prioridade</th>
                <th>Status do Projeto</th>              
            </tr>
            <% for (int i = 0; i < categs.size(); i++) { %>
            <tr>
                <td><%= categs.get(i).getId_projeto() %></td>
                <td><%= categs.get(i).getNome_projeto() %></td>
                <td><%= categs.get(i).getNome_gerente() %></td>
                <td><%= categs.get(i).getDescricao() %></td>
                <td><%= categs.get(i).getData_inicio() %></td>
                <td><%= categs.get(i).getData_fim() %></td> 
                <td><%= categs.get(i).getPrioridade() %></td> 
                <td><%= categs.get(i).getStatus_projeto() %></td> 
            </tr>
            <% } %>
        </table>

        <h3>Listagem dos Requisitos</h3>
        <% ArrayList<Requisito> regs = new RequisitoDAO().consultar(); %>

        <table class="table">
            <tr>
                <th>Código</th>
                <th>Código do Projeto</th>
                <th>Nome do Requisito</th>
                <th>Descricao</th>
                <th>Prioridade</th>
                <th>Complexidade</th>              
                <th>Versao</th>
                <th>Tipo</th>
            </tr>
            <% for (int i = 0; i < regs.size(); i++) { %>
            <tr>
                <td><%= regs.get(i).getId_requisito() %></td>
                <td><%= regs.get(i).getId_projeto() %></td>
                <td><%= regs.get(i).getNome_requisito() %></td>
                <td><%= regs.get(i).getDescricao() %></td>
                <td><%= regs.get(i).getPrioridade() %></td>
                <td><%= regs.get(i).getComplexidade() %></td>
                <td><%= regs.get(i).getVersao() %></td>
                <td><%= regs.get(i).getTipo() %></td> 
            </tr>
            <% } %>
        </table>

        <!-- Marca D`agua -->
        <div class="watermark">Dev. Lucas Cenci Beltrame</div>
        <script>
            // Adicionar um manipulador de eventos de clique ao link
            document.getElementById("reloadLink").addEventListener("click", function (event) {
                event.preventDefault(); // Impedir o comportamento padrão de seguir o link
                // window.location.reload(); // Troque isso por:
                location.href = location.href; // ou window.location.href = window.location.href;
            });
        </script> 

    </body>
</html>
