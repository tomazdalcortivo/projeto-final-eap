package br.edu.ifpr.irati.trabalhofinal.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CartItemRequestDto(
        @NotNull Long clientId,
        @NotNull Long productId,
        @NotNull @Positive Integer quantity) {}
