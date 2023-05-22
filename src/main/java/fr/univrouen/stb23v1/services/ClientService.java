package fr.univrouen.stb23v1.services;

import fr.univrouen.stb23v1.entities.Client;
import fr.univrouen.stb23v1.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
