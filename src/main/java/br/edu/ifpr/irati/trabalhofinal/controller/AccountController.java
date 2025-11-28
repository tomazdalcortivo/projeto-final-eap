package br.edu.ifpr.irati.trabalhofinal.controller;

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

        Page<Account> user = accountService.findAll(pageNo, pageSize);
        return ResponseEntity.ok(user);
    }

    @GetMapping("{id}")
    public ResponseEntity<Account> getOne(@PathVariable Long id) {
        Account user = this.accountService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Account> save(@RequestBody Account account) {
        Account user1 = this.accountService.save(account);
        return ResponseEntity.ok(user1);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Account> update(@RequestBody Account user, @PathVariable Long id) {
        this.accountService.update(user, id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.accountService.delete(id);
        return ResponseEntity.noContent().build();
    }
}