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
        Product teacher = this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("teacher a will be updated not found"));

        teacher.setName(product.getName());
        teacher.setDescription(product.getDescription());
        teacher.setCompleted(product.getCompleted());

        this.productRepository.save(teacher);
        return teacher;
    }

    public void delete(Long id) {
        Product teacher = this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher not found"));
        this.productRepository.delete(teacher);
    }

    public Product save(Product product) {
        return this.productRepository.save(product);
    }

    public Product findById(Long id) {
        return this.productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Teacher not found"));
    }

}