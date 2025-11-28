package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.ProductDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import br.edu.ifpr.irati.trabalhofinal.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<Product> products = productService.findAll(pageNo, pageSize);
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable Long id) {
        Product product = this.productService.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping
    // Alteração: Recebe DTO, converte para entidade
    public ResponseEntity<Product> save(@RequestBody ProductDto productDto) {
        Product productSaved = this.productService.save(productDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(productSaved);
    }

    @PatchMapping("{id}")
    // Alteração: Recebe DTO, converte para entidade para atualizar
    public ResponseEntity<Product> update(@RequestBody ProductDto productDto, @PathVariable Long id) {
        this.productService.update(productDto.toEntity(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}