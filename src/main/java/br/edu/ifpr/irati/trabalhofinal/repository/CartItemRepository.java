package br.edu.ifpr.irati.trabalhofinal.repository;

import br.edu.ifpr.irati.trabalhofinal.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByClientId(Long id);

    void deleteByClientId(Long clientId);

    Optional<CartItem> findByClientIdAndProductId(Long clientId, Long productId);
}
