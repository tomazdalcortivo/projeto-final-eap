package br.edu.ifpr.irati.trabalhofinal.dto;

import br.edu.ifpr.irati.trabalhofinal.entity.Client;

public record ClientDto(
        String name,
        String description,
        Boolean completed) {

//    public Client toEntity() {
//        return Client.builder()
//                .name(name)
//                .description(description)
//                .completed(completed)
//                .build();
//    }
}
