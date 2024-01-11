/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

document.getElementById("nome_projeto").addEventListener("input", function () {
    var maxLength = 100; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("nome_gerente").addEventListener("input", function () {
    var maxLength = 100; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("descricao").addEventListener("input", function () {
    var maxLength = 255; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("prioridade").addEventListener("input", function () {
    var maxLength = 50; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("status_projeto").addEventListener("input", function () {
    var maxLength = 50; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("nome_requisito").addEventListener("input", function () {
    var maxLength = 100; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("complexidade").addEventListener("input", function () {
    var maxLength = 50; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("versao").addEventListener("input", function () {
    var maxLength = 50; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});

document.getElementById("tipo").addEventListener("input", function () {
    var maxLength = 5; // Altere este valor conforme necessário
    var inputValue = this.value;

    if (inputValue.length > maxLength) {
        // Se o valor exceder o comprimento máximo, trunca o texto
        inputValue = inputValue.substring(0, maxLength);
    }

    // Atualiza o valor do campo
    this.value = inputValue;
});


function validarDataFormato(inputId) {
    var input = document.getElementById(inputId);
    var regex = /^\d{2}-\d{2}-\d{4}$/;

    if (!regex.test(input.value)) {
        alert("Formato de data inválido. O formato deve ser: xx-xx-xxxx.");
        input.focus();
        return false;
    }

    return true;
}

function formatarData(inputId) {
    var input = document.getElementById(inputId);
    var inputValue = input.value;

    // Remover caracteres
    var cleanedValue = inputValue.replace(/[^\d-]/g, "");

    // Limitar o comprimento para 10 caracteres (xx-xx-xxxx)
    cleanedValue = cleanedValue.substring(0, 10);

    var parts = cleanedValue.split("-");

    if (parts.length > 1) {
        cleanedValue = parts.slice(0, 3).join("-");
    }

    input.value = cleanedValue;
}

function validarFormulario() {
    return validarDataFormato("data_inicio") && validarDataFormato("data_fim");
}

document.getElementById("data_inicio").addEventListener("input", function () {
    formatarData("data_inicio");
});

document.getElementById("data_fim").addEventListener("input", function () {
    formatarData("data_fim");
});

document.getElementById("formProjeto").addEventListener("submit", function (event) {
    if (!validarFormulario()) {
        event.preventDefault(); // Impede o envio do formulário se a validação falhar
    }
});

document.getElementById("formRequisito").addEventListener("submit", function (event) {
    if (!validarFormulario()) {
        event.preventDefault(); // Impede o envio do formulário se a validação falhar
    }
});


