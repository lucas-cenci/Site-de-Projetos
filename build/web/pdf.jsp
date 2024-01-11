<%-- 
    Document   : pdf
    Created on : 30 de nov. de 2023, 19:18:12
    Author     : Lucas
--%>


<!DOCTYPE html>
<html lang="br">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Exportar Projeto para PDF</title>

        <style>
            body {
                font-family: 'Arial', sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 0;
                padding: 0;
                display: block;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }

            .centered-content {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                height: 100vh;
            }

            h1 {
                text-align: center;
                color: #04AA6D;
                transition: color 0.3s;
            }

            form {
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                max-width: 400px;
                width: 100%;
                box-sizing: border-box;
                transition: box-shadow 0.3s;
            }

            label {
                display: block;
                margin-bottom: 15px;
                font-weight: bold;
            }

            input {
                width: 100%;
                padding: 10px;
                margin-bottom: 16px;
                box-sizing: border-box;
                border: 1px solid #ccc;
                border-radius: 4px;
                transition: border-color 0.3s;
            }

            input[type="submit"] {
                background-color: #04AA6D;
                color: #fff;
                cursor: pointer;
                border: none;
                padding: 15px;
                border-radius: 4px;
                font-size: 16px;
                transition: background-color 0.3s;
            }

            input[type="submit"]:hover {
                background-color: #004d4d;
            }
        </style>
    </head>
    <body>
        <div class="centered-content">
            <h1>Baixe aqui seu PDF: </h1>
            <form action="pdf" method="post">
                <label for="projetoId">Digite o ID do Projeto</label>
                <input type="number" id="projetoId" name="projetoId" required>
                <input type="submit" value="Baixar PDF">
            </form>
        </div>
    </body>
</html>
