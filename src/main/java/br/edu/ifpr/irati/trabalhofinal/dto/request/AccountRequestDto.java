package br.edu.ifpr.irati.trabalhofinal.dto.request;

import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;


public record AccountRequestDto(

        @NotBlank(message = "Email é obrigatório")
        @Email(message = "Formato de email inválido")
        String email,

        @NotBlank(message = "Senha é obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password,

        @NotNull(message = "Dados do cliente são obrigatórios")
        @Valid
        ClientRequestDto client) implements Serializable {

        public Account toEntity() {
            return Account.builder()
                    .email(email)
                    .password(password)
                    .client(client.toEntity())
                    .build();
        }
}