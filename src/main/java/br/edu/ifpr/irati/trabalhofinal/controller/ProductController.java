package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.request.ProductRequestDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.ProductResponseDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import br.edu.ifpr.irati.trabalhofinal.service.ProductService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Page<ProductResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<ProductResponseDto> products = productService.findAll(pageNo, pageSize)
                .map(ProductResponseDto::fromEntity);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getOne(@PathVariable Long id) {
        Product product = this.productService.findById(id);
        return ResponseEntity.ok(ProductResponseDto.fromEntity(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> save(@RequestBody @Valid ProductRequestDto productDto) {
        Product productSaved = this.productService.save(productDto.toEntity(), productDto.clientId());
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductResponseDto.fromEntity(productSaved));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid ProductRequestDto productDto, @PathVariable Long id) {
        this.productService.update(productDto.toEntity(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}