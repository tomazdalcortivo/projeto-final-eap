package br.edu.ifpr.irati.trabalhofinal.service;


import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + username));
    }

    public Account register(Account account) {
        if (accountRepository.findByEmail(account.getEmail()).isPresent()) {
            throw new RuntimeException("Este email já está cadastrado.");
        }
        // Criptografa a senha antes de salvar
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return this.accountRepository.save(account);
    }
}