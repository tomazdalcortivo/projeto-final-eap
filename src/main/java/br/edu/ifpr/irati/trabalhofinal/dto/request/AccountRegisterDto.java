package br.edu.ifpr.irati.trabalhofinal.dto.request;

import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountRegisterDto(

        @Email(message = "Digite um email valido")
        @NotBlank(message = "email não pode ser vazio")
        String email,

        @NotBlank(message = "Senha não pode ser vazia")
        String password,

        Client client){

        public Account toEntity() {
            return Account.builder()
                    .email(email)
                    .password(password)
                    .client(client)
                    .build();
        }
}