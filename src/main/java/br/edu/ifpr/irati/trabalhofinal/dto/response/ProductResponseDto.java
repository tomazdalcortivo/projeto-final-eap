package br.edu.ifpr.irati.trabalhofinal.dto.response;

import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import java.math.BigDecimal;

public record ProductResponseDto(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String clientName) {

    public static ProductResponseDto fromEntity(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getClient() != null ? product.getClient().getName() : "Sem Cliente"
        );
    }
}