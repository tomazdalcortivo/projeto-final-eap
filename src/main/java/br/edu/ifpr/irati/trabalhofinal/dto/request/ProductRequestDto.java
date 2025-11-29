package br.edu.ifpr.irati.trabalhofinal.dto.request;

import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProductRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
        String name,

        @NotBlank(message = "Descrição é obrigatória")
        String description,

        @NotNull(message = "Preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        BigDecimal price,

        @NotNull(message = "Estoque é obrigatório")
        @PositiveOrZero(message = "Estoque deve não deve ser negativo")
        Integer stock,

        @NotNull(message = "ID do cliente é obrigatório")
        Long clientId) implements Serializable {

    public Product toEntity() {
        return Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .stock(stock)
                .build();
    }
}