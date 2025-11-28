package br.edu.ifpr.irati.trabalhofinal.dto.response;

import br.edu.ifpr.irati.trabalhofinal.entity.Client;

public record ClientResponseDto(
        Long id,
        String name,
        String cpf,
        String phone,
        String email) {

    public static ClientResponseDto fromEntity(Client client) {
        String email = (client.getAccount() != null) ? client.getAccount().getEmail() : null;

        return new ClientResponseDto(
                client.getId(),
                client.getName(),
                client.getCpf(),
                client.getPhone(),
                email
        );
    }
}