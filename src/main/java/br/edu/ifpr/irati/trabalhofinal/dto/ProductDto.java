package br.edu.ifpr.irati.trabalhofinal.dto;

import br.edu.ifpr.irati.trabalhofinal.entity.Product;

public record ProductDto(
        String name,
        String description,
        Boolean completed) {

//    public Product toEntity() {
//        return Product.builder()
//                .name(name)
//                .description(description)
//                .completed(completed)
//                .build();
//    }
}
