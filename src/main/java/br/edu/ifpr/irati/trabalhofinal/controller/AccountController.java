package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.controller.docs.AccountControllerDocs;
import br.edu.ifpr.irati.trabalhofinal.dto.request.AccountRequestDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.AccountLoginDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.ClientResponseDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.LoginResponseDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Account;
import br.edu.ifpr.irati.trabalhofinal.infra.security.TokenService;
import br.edu.ifpr.irati.trabalhofinal.repository.AccountRepository;
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
public class AccountController implements AccountControllerDocs {

    private final AccountRepository accountRepository;

    private final AuthenticationManager authenticationManager;

    private final TokenService tokenService;

    @Override
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

    @Override
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody @Valid AccountLoginDto data) { // Retorno alterado para LoginResponseDto
        UsernamePasswordAuthenticationToken userPassword =
                new UsernamePasswordAuthenticationToken(data.email(), data.password());

        Authentication auth = this.authenticationManager.authenticate(userPassword);

        Account account = (Account) auth.getPrincipal();
        String token = tokenService.generateToken(account);

        ClientResponseDto clientDto = ClientResponseDto.fromEntity(account.getClient());

        // Retorna o objeto completo que o React espera
        return ResponseEntity.ok(new LoginResponseDto(token, clientDto));
    }
}