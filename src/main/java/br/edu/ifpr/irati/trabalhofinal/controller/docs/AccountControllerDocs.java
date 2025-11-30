package br.edu.ifpr.irati.trabalhofinal.controller.docs;

import br.edu.ifpr.irati.trabalhofinal.dto.request.AccountRequestDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.AccountLoginDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.LoginResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "Contas", description = "Endpoints para cadastro e login de usuários")
public interface AccountControllerDocs {

    @Operation(summary = "Registrar novo usuário", description = "Cria uma nova conta de acesso vinculada a um cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos ou email já existente")
    })
    ResponseEntity<?> registrar(@RequestBody AccountRequestDto data);

    @Operation(summary = "Realizar Login", description = "Autentica o usuário e retorna o Token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Credenciais inválidas")
    })
    ResponseEntity<LoginResponseDto> login(@RequestBody AccountLoginDto data);
}