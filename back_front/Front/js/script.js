let formData = {
  nome: document.getElementById('nome').value,
  valor: parseFloat(document.getElementById('valor')),
  saldo: (document.getElementById('saldo')),
  saldoMinimo: document.getElementById('saldoMinimo')
}


const response = await fetch("http://localhost:8080/produto", {
  method: "POST",
  headers: {
    "Content-Type": "application/json",
  },
  body: JSON.stringify(produto),
})