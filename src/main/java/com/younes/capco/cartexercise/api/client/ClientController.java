package com.younes.capco.cartexercise.api.client;

import com.younes.capco.cartexercise.domain.client.model.Client;
import com.younes.capco.cartexercise.domain.client.port.ClientRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createClient(@RequestBody Client client) {
        clientRepository.save(client);
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
