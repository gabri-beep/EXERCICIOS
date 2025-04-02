package com.example.back_front.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduto;
    private String nome;
    private  double valor;
    private int saldo;
    private int saldoMinimo;

    public Produto() {
    }

    public Produto(Long idProduto, String nome, double valor, int saldo, int saldoMinimo) {
        this.idProduto = idProduto;
        this.nome = nome;
        this.valor = valor;
        this.saldo = saldo;
        this.saldoMinimo = saldoMinimo;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
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
