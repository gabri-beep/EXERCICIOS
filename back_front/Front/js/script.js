let formData = {
  nome: document.getElementById('nome'),
  valor: parseFloat(document.getElementById('valor')),
  saldo: document.getElementById('saldo'),
  saldoMinimo: document.getElementById('saldoMinimo')
}




const response = await fetch("http://localhost:8080/produto", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(produto),
});

if(!response.ok){
  const errorData = await response.json();
  throw new Error(errorData.message || 'Erro ao salvar produto')
} 
const data = await response.json();
console.log('Produto salvo com sucesso:', data);
alert('Produto salvo com sucesso');
