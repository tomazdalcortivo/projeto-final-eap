package br.edu.ifpr.irati.trabalhofinal.dto.request;

import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public record ClientRequestDto(
        @NotBlank(message = "Nome é obrigatório")
        String name,

        @CPF(message = "CPF inválido")
        @NotBlank(message = "CPF é obrigatório")
        String cpf,

        @NotBlank(message = "Telefone é obrigatório")
        String phone,

        @NotBlank(message = "Endereço é obrigatório")
        String address) implements Serializable {

    public Client toEntity() {
        return Client.builder()
                .name(name)
                .cpf(cpf)
                .phone(phone)
                .address(address)
                .build();
    }
}