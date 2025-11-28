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

    public Account update(Account user, Long id) {
        Account user1 = this.accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User a will be updated not found"));

//        user1.setName(user.getName());
//        user1.setDescription(user.getDescription());
//        user1.setCompleted(user.getCompleted());

        this.accountRepository.save(user1);
        return user1;
    }

    public void delete(Long id) {
        Account user = this.accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
        this.accountRepository.delete(user);
    }

    public Account save(Account user) {
        return this.accountRepository.save(user);
    }

    public Account findById(Long id) {
        return this.accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("User not found"));
    }

}