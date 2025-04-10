package com.example.back_front.dto;


public class ProdutoDto {
    private Long id;
    private String nome;
    private  double valor;
    private int saldo;
    private int saldoMinimo;

    public ProdutoDto() {
    }

    public ProdutoDto(Long id, String nome, double valor, int saldo, int saldoMinimo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.saldo = saldo;
        this.saldoMinimo = saldoMinimo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldoMinimo() {
        return saldoMinimo;
    }

    public void setSaldoMinimo(int saldoMinimo) {
        this.saldoMinimo = saldoMinimo;
    }
}
