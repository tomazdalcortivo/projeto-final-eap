package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.request.AccountRequestDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.AccountLoginDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.infra.security.TokenService;
import br.edu.ifpr.irati.trabalhofinal.repository.AccountRepository;
import br.edu.ifpr.irati.trabalhofinal.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity registrar(@RequestBody @Valid AccountRequestDto data) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Account newAccount = Account.builder()
                .email(data.email())
                .password(encryptedPassword)
                .client(data.client().toEntity())
                .build();

        this.accountRepository.save(newAccount);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AccountLoginDto data) {
        UsernamePasswordAuthenticationToken userPassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        Authentication auth = this.authenticationManager.authenticate(userPassword);

        String token = tokenService.generateToken((Account) auth.getPrincipal());
        return ResponseEntity.ok(token);
    }
}