package br.edu.ifpr.irati.trabalhofinal.service;

import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Page<Account> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return this.accountRepository.findAll(pageable);
    }

    public Account update(Account account, Long id) {
        Account accountToUpdate = this.accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found"));

        // Atualiza email e senha baseados no objeto recebido
        accountToUpdate.setEmail(account.getEmail());
        accountToUpdate.setPassword(account.getPassword());

        return this.accountRepository.save(accountToUpdate);
    }

    public void delete(Long id) {
        Account account = this.accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found"));
        this.accountRepository.delete(account);
    }

    public Account save(Account account) {
        return this.accountRepository.save(account);
    }

    public Account findById(Long id) {
        return this.accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Account not found"));
    }
}