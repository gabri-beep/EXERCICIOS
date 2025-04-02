package com.example.back_front.controller;

import com.example.back_front.dto.ProdutoDto;
import com.example.back_front.entity.Produto;
import com.example.back_front.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @PostMapping()
    public ResponseEntity<ProdutoDto> created(@RequestBody ProdutoDto produtoDto){
        ProdutoDto produto = produtoService.save(produtoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> findAll(){
        return ResponseEntity.ok().body(produtoService.getAll());
    }

}
