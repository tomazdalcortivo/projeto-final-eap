package br.edu.ifpr.irati.trabalhofinal.dto;

import br.edu.ifpr.irati.trabalhofinal.entity.Account;

public record AccountDto(
        String email,
        String password) {

    public Account toEntity() {
        return Account.builder()
                .email(email)
                .password(password)
                .build();
    }
}