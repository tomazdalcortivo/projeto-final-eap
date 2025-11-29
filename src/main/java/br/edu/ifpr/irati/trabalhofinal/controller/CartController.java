package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.request.CartItemRequestDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.CartItemResponseDto;
import br.edu.ifpr.irati.trabalhofinal.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<Void> addToCart(@RequestBody @Valid CartItemRequestDto dto) {
        cartService.addItem(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<List<CartItemResponseDto>> getCart(@PathVariable Long clientId) {
        List<CartItemResponseDto> items = cartService.getCartItems(clientId)
                .stream().map(CartItemResponseDto::fromEntity).toList();
        return ResponseEntity.ok(items);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItem(@PathVariable Long id) {
        cartService.removeItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/checkout/{clientId}")
    public ResponseEntity<Void> checkout(@PathVariable Long clientId) {
        cartService.checkout(clientId);
        return ResponseEntity.ok().build();
    }
}