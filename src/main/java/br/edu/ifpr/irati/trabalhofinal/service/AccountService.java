package br.edu.ifpr.irati.trabalhofinal.service;


import br.edu.ifpr.irati.trabalhofinal.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

}