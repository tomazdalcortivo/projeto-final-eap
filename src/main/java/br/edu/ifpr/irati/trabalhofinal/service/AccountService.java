package br.edu.ifpr.irati.trabalhofinal.service;


import br.edu.ifpr.irati.trabalhofinal.dto.response.AccountLoginDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account register(Account account) {
        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já está cadastrado.");
        }
        return this.accountRepository.save(account);
    }

    public Account login(AccountLoginDto loginDto) {
        Account account = accountRepository.findByEmail(loginDto.email())
                .orElseThrow(() -> new RuntimeException("Email ou senha inválidos"));

        if (!account.getPassword().equals(loginDto.password())) {
            throw new RuntimeException("Email ou senha inválidos");
        }
        return account;
    }
}