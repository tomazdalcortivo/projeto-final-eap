package br.edu.ifpr.irati.trabalhofinal.repository;

import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}