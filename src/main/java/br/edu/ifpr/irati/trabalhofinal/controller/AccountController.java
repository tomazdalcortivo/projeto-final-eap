package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.request.AccountRegisterDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.AccountLoginDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid AccountRegisterDto dto) {
        this.accountService.register(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AccountLoginDto dto) {
        Account loggedAccount = this.accountService.login(dto);

        return ResponseEntity.ok(loggedAccount);
    }
}