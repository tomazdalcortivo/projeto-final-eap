package br.edu.ifpr.irati.trabalhofinal.dto;

import br.edu.ifpr.irati.trabalhofinal.entity.Client;

public record AccountRegisterDto(
        String email,
        String password,
        String name,
        String city,
        String postalCode,
        Client client) {


}