package br.edu.ifpr.irati.trabalhofinal.dto.response;

import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AccountLoginDto(

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        String password) {

    public Account toEntity() {
        return Account.builder()
                .email(email)
                .password(password)
                .build();
    }
}