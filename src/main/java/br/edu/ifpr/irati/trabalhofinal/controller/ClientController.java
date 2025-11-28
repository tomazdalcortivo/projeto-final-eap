package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.ClientDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import br.edu.ifpr.irati.trabalhofinal.service.ClientService;
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
    public ResponseEntity<Page<Client>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<Client> clients = clientService.findAll(pageNo, pageSize);
        return ResponseEntity.ok(clients);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getOne(@PathVariable Long id) {
        Client client = this.clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody ClientDto clientDto) {
        Client clientSaved = this.clientService.save(clientDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(clientSaved);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody ClientDto clientDto, @PathVariable Long id) {
        this.clientService.update(clientDto.toEntity(), id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}