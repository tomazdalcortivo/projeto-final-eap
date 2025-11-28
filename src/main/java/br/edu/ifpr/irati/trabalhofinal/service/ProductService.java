package br.edu.ifpr.irati.trabalhofinal.service;

import br.edu.ifpr.irati.trabalhofinal.entity.Product;
import br.edu.ifpr.irati.trabalhofinal.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return this.productRepository.findAll(pageable);
    }

    public Product update(Product product, Long id) {
        Product productToUpdate = this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produto não encontrado para atualização"));

        productToUpdate.setName(product.getName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCompleted(product.getCompleted());

        return this.productRepository.save(productToUpdate);
    }

    public void delete(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produto não encontrado"));
        this.productRepository.delete(product);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Produto não encontrado"));
    }
}