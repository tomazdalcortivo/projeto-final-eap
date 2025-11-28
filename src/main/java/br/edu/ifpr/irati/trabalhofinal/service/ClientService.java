package br.edu.ifpr.irati.trabalhofinal.service;

import br.edu.ifpr.irati.trabalhofinal.entity.Client;
import br.edu.ifpr.irati.trabalhofinal.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Page<Client> findAll(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return this.clientRepository.findAll(pageable);
    }

    public Client update(Client client, Long id) {
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found"));

        clientToUpdate.setName(client.getName());
        clientToUpdate.setDescription(client.getDescription());
        clientToUpdate.setCompleted(client.getCompleted());

        return this.clientRepository.save(clientToUpdate);
    }

    public void delete(Long id) {
        Client client = this.clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found"));
        this.clientRepository.delete(client);
    }

    public Client save(Client client) {
        return this.clientRepository.save(client);
    }

    public Client findById(Long id) {
        return this.clientRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Client not found"));
    }
}