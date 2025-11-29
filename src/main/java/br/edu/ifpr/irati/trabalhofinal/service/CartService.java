package br.edu.ifpr.irati.trabalhofinal.service;

import br.edu.ifpr.irati.trabalhofinal.dto.request.CartItemRequestDto;
import br.edu.ifpr.irati.trabalhofinal.entity.CartItem;
import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import br.edu.ifpr.irati.trabalhofinal.repository.CartItemRepository;
import br.edu.ifpr.irati.trabalhofinal.repository.ClientRepository;
import br.edu.ifpr.irati.trabalhofinal.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartItemRepository cartRepository;
    private final ProductRepository productRepository;
    private final ClientRepository clientRepository;

    public CartItem addItem(CartItemRequestDto dto) {
        Client client = clientRepository.findById(dto.clientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Product product = productRepository.findById(dto.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        // 1. Verifica se tem estoque suficiente para a adição
        if (product.getStock() < dto.quantity()) {
            throw new RuntimeException("Estoque insuficiente! Disponível: " + product.getStock());
        }

        // 2. Verifica se já existe no carrinho
        Optional<CartItem> existingItem = cartRepository.findByClientIdAndProductId(dto.clientId(), dto.productId());

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            int novaQuantidade = item.getQuantity() + dto.quantity();

            // Verifica estoque considerando o que já está no carrinho
            if (product.getStock() < novaQuantidade) {
                throw new RuntimeException("Estoque insuficiente para a quantidade total!");
            }

            item.setQuantity(novaQuantidade);
            return cartRepository.save(item);
        } else {
            // Cria novo item
            CartItem item = CartItem.builder()
                    .client(client)
                    .product(product)
                    .quantity(dto.quantity())
                    .build();
            return cartRepository.save(item);
        }
    }

    public List<CartItem> getCartItems(Long clientId) {
        return cartRepository.findByClientId(clientId);
    }

    public void removeItem(Long itemId) {
        cartRepository.deleteById(itemId);
    }

    @Transactional
    public void checkout(Long clientId) {
        List<CartItem> items = cartRepository.findByClientId(clientId);

        if (items.isEmpty()) {
            throw new RuntimeException("Carrinho vazio!");
        }

        // 3. Atualiza o estoque dos produtos
        for (CartItem item : items) {
            Product p = item.getProduct();
            if (p.getStock() < item.getQuantity()) {
                throw new RuntimeException("Produto " + p.getName() + " acabou durante a compra!");
            }
            p.setStock(p.getStock() - item.getQuantity());
            productRepository.save(p);
        }

        // Limpa o carrinho
        cartRepository.deleteAll(items);
    }
}