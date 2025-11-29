package br.edu.ifpr.irati.trabalhofinal.dto.response;

import br.edu.ifpr.irati.trabalhofinal.entity.CartItem;
import java.math.BigDecimal;

public record CartItemResponseDto(
        Long id,
        Long productId,
        String productName,
        BigDecimal price,
        Integer quantity,
        BigDecimal total
) {
    public static CartItemResponseDto fromEntity(CartItem item) {
        BigDecimal total = item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity()));
        return new CartItemResponseDto(
                item.getId(),
                item.getProduct().getId(),
                item.getProduct().getName(),
                item.getProduct().getPrice(),
                item.getQuantity(),
                total
        );
    }
}