package br.edu.ifpr.irati.trabalhofinal.controller;

import br.edu.ifpr.irati.trabalhofinal.dto.GuestDto;
import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import br.edu.ifpr.irati.trabalhofinal.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<Page<Client>> getAll(
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "5") int pageSize) {

        Page<Client> guests = clientService.findAll(pageNo, pageSize);
        return ResponseEntity.ok(guests);
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getOne(@PathVariable Long id) {
        Client client = this.clientService.findById(id);
        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Client> save(@RequestBody GuestDto guest) {
        Client guest1 = this.clientService.save(guest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(guest1);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Void> update(@RequestBody GuestDto guest, @PathVariable Long id) {
        this.clientService.update(guest.toEntity(), id);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
