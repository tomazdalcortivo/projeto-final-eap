package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import br.edu.ifpr.irati.trabalhofinal.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

        Page<Product> user = productService.findAll(pageNo, pageSize);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<Product> getOne(@PathVariable Long id) {
        Product teacher = this.productService.findById(id);
        return ResponseEntity.ok(teacher);
    }

    @PostMapping
    public ResponseEntity<Product> save(@RequestBody Product product) {
        Product product1 = this.productService.save(product);
        return ResponseEntity.ok(product1);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id) {
        this.productService.update(product, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}