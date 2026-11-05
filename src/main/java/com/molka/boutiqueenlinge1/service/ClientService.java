package com.molka.boutiqueenlinge1.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.molka.boutiqueenlinge1.model.Client;
import com.molka.boutiqueenlinge1.repository.ClientRepository;

@Service
public class ClientService {

    private final ClientRepository repository;

    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getAllClients() {
        return repository.findAll();
    }

    public Client getClientById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return repository.save(client);
    }

    public Client updateClient(Long id, Client nouveauClient) {
        return repository.findById(id).map(client -> {
            client.setNom(nouveauClient.getNom());
            client.setPrenom(nouveauClient.getPrenom());
            client.setEmail(nouveauClient.getEmail());
            client.setTelephone(nouveauClient.getTelephone());
            return repository.save(client);
        }).orElse(null);
    }

    public void deleteClient(Long id) {
        repository.deleteById(id);
    }
}
