package com.example.back_front.controller;

import com.example.back_front.dto.ProdutoDto;
import com.example.back_front.entity.Produto;
import com.example.back_front.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> findById(@PathVariable Long id){
        Optional<ProdutoDto> produtoDto = produtoService.getById(id);
        if (produtoDto.isPresent()){
            return ResponseEntity.ok(produtoDto.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> update(@PathVariable Long id, @RequestBody ProdutoDto produtoDto){
        Optional<ProdutoDto> produtoDtoOptional = produtoService.update(id, produtoDto);

        if (produtoDtoOptional.isPresent()){
            return ResponseEntity.ok(produtoDtoOptional.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if (produtoService.delete(id)){
            return ResponseEntity.noContent().build();
        } else {
            return  ResponseEntity.notFound().build();
        }
    }

}
