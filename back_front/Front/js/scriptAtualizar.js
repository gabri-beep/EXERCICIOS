function paramUrl(){
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    if (id) {
        carregarProdutoPorId(id);
    }
}

async function carregarProdutoPorId(id) {
    // requisição do tipo get
    // preenche os campos do formulário com base no produto encontrado

    try {
        let response = await fetch(`http://localhost:8080/produto/${id}`, { // url da requisição / mesma url colocada no Postman
            method: "GET", // método da requisição
            headers: { "Content-Type": "application/json" }, // informa que um json esta sendo enviado
        });

        if(!response.ok){ // erro retornado pelo back-end
            alert("Erro do back-end " + response.status);
            return
        }

        let data = await response.json();

        // daqui para baixo seria o que será realizado com o objeto retornado caso de certo a requisição
        document.getElementById("produtoId").value = data.id;
        document.getElementById("nome").value = data.nome;
        document.getElementById("valor").value = data.valor;
        document.getElementById("saldo").value = data.saldo;
        document.getElementById("saldoMinimo").value = data.saldoMinimo;

    } catch (error) {
        alert("Erro na requisição: " + error.message)
    }
}

async function enviarProduto(event) {
    event.preventDefault(); // Evita o envio tradicional do formulário
    let id = document.getElementById("produtoId").value;
    
    // cria objeto para ser enviado
    let formData = {
        nome: document.getElementById("nome").value,
        valor: parseFloat(document.getElementById("valor").value),
        saldo: parseInt(document.getElementById("saldo").value),
        saldoMinimo: parseInt(document.getElementById("saldoMinimo").value)
    };

    try {
        let response = await fetch(`http://localhost:8080/produto/${id}`, { // url da requisição / mesma url colocada no Postman
            method: "PUT", // método da requisição
            headers: { "Content-Type": "application/json" }, // informa que um json esta sendo enviado
            // converte objeto em json
            body: JSON.stringify(formData) // conteúdo que esta sendo enviado, JSON neste caso
        });

        if(!response.ok){ // erro retornado pelo back-end
            alert("Erro do back-end " + response.status);
            return
        }

        let data = await response.json();
        // daqui para baixo seria o que será realizado com o objeto retornado caso de certo a requisição
        alert("Sucesso: " + JSON.stringify(data));

    } catch (error) {
        alert("Erro na requisição: " + error.message)
    }
}

document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("formProdutoAtualizar").addEventListener("submit", enviarProduto);
    paramUrl();
});