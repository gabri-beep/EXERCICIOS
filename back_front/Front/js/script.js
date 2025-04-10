async function enviarProduto(event) {
  event.preventDefault(); // Evita o envio tradicional do formulário
  
  // cria objeto para ser enviado
  let formData = {
      nome: document.getElementById("nome").value,
      valor: parseFloat(document.getElementById("valor").value),
      saldo: parseInt(document.getElementById("saldo").value),
      saldoMinimo: parseInt(document.getElementById("saldo_minimo").value)
  };
  
  try {
      let response = await fetch("http://localhost:8080/produto", { // url da requisição / mesma url colocada no Postman
      method: "POST", // método da requisição
          headers: { "Content-Type": "application/json" }, // informa que um json esta sendo enviado
          // converte objeto em json
          body: JSON.stringify(formData) // conteúdo que esta sendo enviado, JSON neste caso
      });

      if(!response.ok){ // erro retornado pelo back-end
          alert("Erro do back-end" + response.status)
          return
      }

      let data = await response.json() // converte o JSON que esta no body para Objeto JS

      // daqui para baixo seria o que será realizado com o objeto retornado caso de certo a requisição

      alert("Sucesso: " + JSON.stringify(data)); // se der certo mostra um alerta na tela mostrando o json salvo no banco de dados
      carregarProdutos();
  } catch (error) {
      alert("Erro na requisição: " + error.message)
      
  }
}

// função que busca as informações no banco de dados e cria uma lista com a informações
async function carregarProdutos() {

  try {
      let response = await fetch("http://localhost:8080/produto", { // url da requisição / mesma url colocada no Postman
      method: "GET", // método da requisição
          headers: { "Content-Type": "application/json" }, // informa que um json esta sendo enviado
      });

      if(!response.ok){ // erro retornado pelo back-end
          alert("Erro do back-end" + response.status)
          return
      }

      let data = await response.json() // converte o JSON que esta no body para Objeto JS

      // daqui para baixo seria o que será realizado com o objeto retornado caso de certo a requisição

      let lista = document.getElementById("listaProdutos");
      lista.innerHTML = ""; // Limpa a lista antes de adicionar
      data.forEach(produto => {
          let item = document.createElement("li");
          item.textContent = `ID: ${produto.id} - ${produto.nome} - R$ ${produto.valor} - Saldo: ${produto.saldo} - Saldo Mínimo: ${produto.saldoMinimo}`;
          let btnDeletar = document.createElement("button")
          btnDeletar.textContent = "Deletar";
          btnDeletar.style.marginLeft = "10px";
          btnDeletar.onclick = function(){
              deletarProduto(produto.id)
          }
          item.appendChild(btnDeletar);
          lista.appendChild(item);
      });
  } catch (error) {
      alert("Erro na requisição: " + error.message)
  }
}

// função para deletar o produto
async function deletarProduto(id) {
  if (confirm("Tem certeza que deseja deletar este produto?")) {
      try {
          let response = await fetch(`http://localhost:8080/produto/${id}`, { // url da requisição / mesma url colocada no Postman
          method: "DELETE", // método da requisição
              headers: { "Content-Type": "application/json" }, // informa que um json esta sendo enviado
          });

          if(!response.ok){ // erro retornado pelo back-end
              alert("Erro do back-end" + response.status)
              return
          }
          // daqui para baixo seria o que será realizado com o objeto retornado caso de certo a requisição
          alert("Produto deletado com sucesso!");
          carregarProdutos();
      } catch (error) {
          alert("Erro na requisição: " + error.message)
      }
  }
}

document.addEventListener("DOMContentLoaded", () => {
  document.getElementById("produtoForm").addEventListener("submit", enviarProduto);
  document.getElementById("carregarProdutos").addEventListener("click", carregarProdutos);
});

