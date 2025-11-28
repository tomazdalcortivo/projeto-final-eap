package br.edu.ifpr.irati.trabalhofinal.repository;

import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}