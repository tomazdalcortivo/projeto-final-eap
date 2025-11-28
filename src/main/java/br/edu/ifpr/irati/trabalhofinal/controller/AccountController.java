package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.AccountDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public ResponseEntity<Page<Account>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<Account> accounts = accountService.findAll(pageNo, pageSize);
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getOne(@PathVariable Long id) {
        Account account = this.accountService.findById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody AccountDto accountDto) {
        Account accountSaved = this.accountService.save(accountDto.toEntity());
        return ResponseEntity.ok(accountSaved);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody AccountDto accountDto, @PathVariable Long id) {
        this.accountService.update(accountDto.toEntity(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}