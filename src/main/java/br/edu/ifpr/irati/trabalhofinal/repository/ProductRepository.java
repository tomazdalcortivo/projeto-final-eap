package br.edu.ifpr.irati.trabalhofinal.repository;

import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}