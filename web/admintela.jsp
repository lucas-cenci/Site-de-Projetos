<%-- 
    Document   : admintela
    Created on : 10 de set. de 2023, 15:18:12
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
            <li><a href="usuariotela.jsp">Voltar a tela de Usuario</a></li>
            <li><a href="">Admin Logado</a></li>
            <li style="float:right"><a class="active" href="#" id="reloadLink">Recarregar</a></li>
        </ul>

        <%
        Projeto p = (Projeto) request.getAttribute("projeto");
        Requisito r = (Requisito) request.getAttribute("requisito");

        if (p == null) {
            p = new Projeto();
        }

        if (r == null) {
            r = new Requisito();
        }
        %>

        <%-- Exibir mensagem de sucesso ou erro --%>
        <%
            Boolean sucesso = (Boolean) request.getAttribute("sucesso");
            String mensagem = (String) request.getAttribute("mensagem");
    
            // Verificar se sucesso não é nulo antes de usá-lo
            if (sucesso != null && sucesso.booleanValue()) {
        %>
        <div class="alert alert-success">
            <strong>Sucesso:</strong> <%= mensagem %>
        </div>
        <% } else if (mensagem != null && !mensagem.isEmpty()) { %>
        <div class="alert alert-danger">
            <strong>Erro:</strong> <%= mensagem %>
        </div>
        <% } %>

        <h1>Cadastro de Projetos</h1>

        <div class="table-container">
            <form method="post" action="acao?a=salvarCateg" onsubmit="return validarFormulario()">
                <label for="id_projeto">Código</label><br>
                <input type="text" id="" name="id_projeto" readonly="" value="<%= p.getId_projeto() %>"><br>

                <label for="nome_projeto">Nome Do Projeto</label><br>
                <input type="text" id="nome_projeto" name="nome_projeto" value="<%= (p.getNome_projeto() != null) ? p.getNome_projeto() : "" %>"><br>

                <label for="nome_gerente">Nome do Gerente</label><br>
                <input type="text" id="nome_gerente" name="nome_gerente" value="<%= (p.getNome_gerente() != null) ? p.getNome_gerente() : "" %>"><br>

                <label for="descricao">Descricao</label><br>
                <input type="text" id="descricao" name="descricao" value="<%= (p.getDescricao() != null) ? p.getDescricao() : "" %>"><br>

                <label for="data_inicio">Data de Inicio</label><br>
                <input type="text" id="data_inicio" name="data_inicio" placeholder="dd-mm-yyyy" value="<%= (p.getData_inicio() != null) ? p.getData_inicio() : "" %>"><br>

                <label for="data_fim">Data de Conclusao</label><br>
                <input type="text" id="data_fim" name="data_fim" placeholder="dd-mm-yyyy" value="<%= (p.getData_fim() != null) ? p.getData_fim() : "" %>"><br>

                <label for="prioridade">Prioridade</label><br>
                <input type="text" id="prioridade" name="prioridade" value="<%= (p.getPrioridade() != null) ? p.getPrioridade() : "" %>"><br>

                <label for="status_projeto">Status do Projeto</label><br>
                <input type="text" id="status_projeto" name="status_projeto" value="<%= (p.getStatus_projeto() != null) ? p.getStatus_projeto() : "" %>"><br>

                <br>
                <input type="submit" name="salvar" value="Salvar">
            </form>  
        </div>

        <h1>Listagem dos Projetos</h1>
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
                <th>Consultar</th>
                <th>Editar</th>
                <th>Excluir</th>
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
                <td><a href="acao?a=consultarCateg&id=<%= categs.get(i).getId_projeto() %>" class="btn btn-primary">Consultar</a></td>
                <td><a href="acao?a=editarCateg&id=<%= categs.get(i).getId_projeto() %>" class="btn btn-success">Editar</a></td>
                <td><a href="acao?a=excluirCateg&id=<%= categs.get(i).getId_projeto() %>" class="btn btn-danger">Excluir</a></td>
            </tr>
            <% } %>
        </table>

        <h2>Cadastro de Requisitos</h2>

        <div class="table-container">
            <form method="post" action="acaod?a=salvarCateg" onsubmit="return validarFormulario()">
                <label for="id_requisito">Código</label><br>
                <input type="text" id="" name="id_requisito" readonly="" value="<%= r.getId_requisito() %>"><br>

                <label for="id_projeto">Código do Projeto</label><br>
                <input type="text" id="" name="id_projeto" value="<%= r.getId_projeto() %>"><br>

                <label for="nome_requisito">Nome do Requisito</label><br>
                <input type="text" id="nome_requisito" name="nome_requisito" value="<%= (r.getNome_requisito() != null) ? r.getNome_requisito() : "" %>"><br>

                <label for="descricao">Descricao do Requisito</label><br>
                <input type="text" id="descricao" name="descricao" value="<%= (r.getDescricao() != null) ? r.getDescricao() : "" %>"><br>

                <label for="prioridade">Prioridade</label><br>
                <input type="text" id="prioridade" name="prioridade" value="<%= (r.getPrioridade() != null) ? r.getPrioridade() : "" %>"><br>

                <label for="complexidade">Complexidade</label><br>
                <input type="text" id="complexidade" name="complexidade" value="<%= (r.getComplexidade() != null) ? r.getComplexidade() : "" %>"><br>

                <label for="versao">Versao</label><br>
                <input type="text" id="versao" name="versao" value="<%= (r.getVersao() != null) ? r.getVersao() : "" %>"><br>

                <label for="tipo">Tipo</label><br>
                <input type="text" id="tipo" name="tipo" value="<%= (r.getTipo() != null) ? r.getTipo() : "" %>"><br>

                <br>
                <input type="submit" name="salvar" value="Salvar">
            </form>
        </div>

        <h2>Listagem dos Requisitos</h2>
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
                <th>Listar</th>
                <th>Editar</th>
                <th>Excluir</th>
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
                <td><a href="acaod?a=consultarCateg&id=<%= regs.get(i).getId_requisito() %>" class="btn btn-primary">Consultar</a></td>
                <td><a href="acaod?a=editarCateg&id=<%= regs.get(i).getId_requisito() %>" class="btn btn-success">Editar</a></td>
                <td><a href="acaod?a=excluirCateg&id=<%= regs.get(i).getId_requisito() %>" class="btn btn-danger">Excluir</a></td>
            </tr>
            <% } %>
        </table>        

        <script>
            // Adicionar um manipulador de eventos de clique ao link
            document.getElementById("reloadLink").addEventListener("click", function (event) {
                event.preventDefault(); // Impedir o comportamento padrão de seguir o link
                window.location.reload(); // Recarregar a página
            });
        </script>
      
        <script src="assets/js/validacao.js"></script>
        
        <!-- Marca D`agua -->
        <div class="watermark">Dev. Lucas Cenci Beltrame</div>       
    </body>
</html>