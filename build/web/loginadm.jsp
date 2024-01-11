<%-- 
    Document   : loginadm
    Created on : 13 de out. de 2023, 15:14:00
    Author     : Lucas Cenci Beltrame
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Login</title>
        <link rel="stylesheet" href="assets/css/estilizacao.css">
        <style>
            /* CSS PopUp de erro */
            .popup {
                display: none;
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.5);
            }

            /* Marca D`agua */
            .watermark {
                position: absolute;
                bottom: 10px;
                right: 10px;
                color: #fff;
                font-size: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="page">
            <form method="POST" class="formLogin" action="acaod">
                <h1>Login do Administrador</h1>
                <p>Digite os seus dados de acesso no campo abaixo.</p>
                <label for="usuario">Usuario</label>
                <input type="text" id="usuario" name="usuario" placeholder="Digite seu nome" autofocus="true" />

                <label for="senha">Senha</label>
                <input type="text" id="senha" name="senha" placeholder="Digite sua senha" />
                <button id="mostrarSenha" type="button" class="btn" onclick="toggleSenha()">Mostrar Senha</button>

                <input type="hidden" name="a" value="autenticar">
                <input type="submit" value="Acessar" class="btn" />
            </form>
        </div>

        <!-- Popup de erro -->
        <div class="popup" id="popupErro">
            <p>Erro de autenticação! Tente novamente.</p>
            <button onclick="fecharPopupErro()">Fechar</button>
        </div>

        <!-- Marca D`agua -->
        <div class="watermark">Dev. Lucas Cenci Beltrame</div>


        <script>
            // Função para exibir o popup de erro
            function exibirPopupErro() {
                document.getElementById("popupErro").style.display = "block";
            }

            // Função para fechar o popup de erro
            function fecharPopupErro() {
                document.getElementById("popupErro").style.display = "none";
            }

            // Verifica se houve erro ao enviar o formulário
            <% String erro = (String) request.getAttribute("erro"); %>
            <% if (erro != null && erro.equals("true")) { %>
            exibirPopupErro();
            <% } %>
        </script>

        <script>
            function toggleSenha() {
                var senhaInput = document.getElementById("senha");
                var mostrarSenhaButton = document.getElementById("mostrarSenha");

                if (senhaInput.type === "password") {
                    senhaInput.type = "text";
                    mostrarSenhaButton.textContent = "Ocultar Senha";
                } else {
                    senhaInput.type = "password";
                    mostrarSenhaButton.textContent = "Mostrar Senha";
                }
            }

            // Ocultar a senha automaticamente quando a página é carregada
            document.addEventListener("DOMContentLoaded", function () {
                var senhaInput = document.getElementById("senha");
                senhaInput.type = "password";
            });
        </script>
    </body>
</html>

