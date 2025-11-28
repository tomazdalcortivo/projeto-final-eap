package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;


}