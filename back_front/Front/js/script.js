document.addEventListener('DOMContentLoaded', function() {
  const form = document.getElementById('formProduto');
  const mensagem = document.getElementById('mensagem');
  const btnCarregar = document.getElementById('carregarProdutos');
  const listaProdutos = document.getElementById('lista');

  
  form.addEventListener('submit', async function(e) {
    e.preventDefault();

    try {
      const produto = {
        nome: form.nome.value,
        valor: parseFloat(form.valor.value),
        saldo: parseInt(form.saldo.value),
        saldoMinimo: parseInt(form.saldoMinimo.value)
      };

      
      if (!produto.nome || isNaN(produto.valor) || isNaN(produto.saldo) || isNaN(produto.saldoMinimo)) {
        throw new Error('Preencha todos os campos corretamente');
      }

      //post ----------------------------------------------------
      const response = await fetch("http://localhost:8080/produto", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(produto),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || 'Erro ao salvar produto');
      }
      console.log(produto);
      
    } catch (error) {
      mensagem.textContent = error.message;
      mensagem.style.color = 'red';
      console.error('Erro:', error);
    }
  });

  //getAll -----------------------------------------------------------------------
  async function carregarProdutos() {
    try {
      const response = await fetch("http://localhost:8080/produto");
      
      if (!response.ok) {
        throw new Error('Erro ao carregar produtos');
      }
      
      const produtos = await response.json();
      exibirProdutos(produtos);
      
    } catch (error) {
      listaProdutos.innerHTML = `<li>${error.message}</li>`;
      console.error('Erro:', error);
    }
  }

  function exibirProdutos(produtos) {
    if (produtos.length === 0) {
      listaProdutos.innerHTML = '<li>Nenhum produto cadastrado</li>';
      return;
    }

    listaProdutos.innerHTML = '';
    
    produtos.forEach(produto => {
      const item = document.createElement('li');
      item.innerHTML = `
        ${produto.nome} |
        Valor: R$ ${produto.valor.toFixed(2)} | 
        Estoque: ${produto.saldo} |
        Mínimo: ${produto.saldoMinimo}`;

      listaProdutos.appendChild(item);
    });
  }

  

  btnCarregar.addEventListener('click', carregarProdutos);
  
  
});