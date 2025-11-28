package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.request.ClientRequestDto;
import br.edu.ifpr.irati.trabalhofinal.dto.response.ClientResponseDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import br.edu.ifpr.irati.trabalhofinal.service.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<ClientResponseDto>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<ClientResponseDto> clients = clientService.findAll(pageNo, pageSize)
                .map(ClientResponseDto::fromEntity);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDto> getOne(@PathVariable Long id) {
        Client client = this.clientService.findById(id);
        return ResponseEntity.ok(ClientResponseDto.fromEntity(client));
    }

    @PostMapping
    public ResponseEntity<ClientResponseDto> save(@RequestBody @Valid ClientRequestDto clientDto) {
        Client clientSaved = this.clientService.save(clientDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(ClientResponseDto.fromEntity(clientSaved));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody @Valid ClientRequestDto clientDto, @PathVariable Long id) {
        this.clientService.update(clientDto.toEntity(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}