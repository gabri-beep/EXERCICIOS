package com.example.back_front.service;

import com.example.back_front.dto.ProdutoDto;
import com.example.back_front.entity.Produto;
import com.example.back_front.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto fromDto(ProdutoDto produtoDto){
        Produto produto = new Produto();
        produto.setNome(produtoDto.getNome());
        produto.setValor(produtoDto.getValor());
        produto.setSaldo(produtoDto.getSaldo());
        produto.setSaldoMinimo(produtoDto.getSaldoMinimo());

        return produto;
    }

    public ProdutoDto toDto(Produto produto){
        ProdutoDto produtoDto = new ProdutoDto();
        produtoDto.setIdProduto(produto.getIdProduto());
        produtoDto.setNome(produto.getNome());
        produtoDto.setValor(produto.getValor());
        produtoDto.setSaldo(produtoDto.getSaldo());
        produtoDto.setSaldoMinimo(produto.getSaldoMinimo());

        return produtoDto;
    }

    public List<Produto> getAll(){
        return produtoRepository.findAll();
    }

    public ProdutoDto save(ProdutoDto produtoDto){
        Produto produto = this.fromDto(produtoDto);
        Produto produtoBd = produtoRepository.save(produto);
        return this.toDto(produtoBd);
    }

}
