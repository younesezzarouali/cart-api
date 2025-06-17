package com.younes.capco.cartexercise.repository.adapter.client;

import com.younes.capco.cartexercise.domain.client.model.Client;
import com.younes.capco.cartexercise.domain.client.port.ClientRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryClientRepository implements ClientRepository {
    private final Map<String, Client> store = new ConcurrentHashMap<>();

    @Override
    public void save(Client client) {
        store.put(client.id(), client);
    }

    @Override
    public Optional<Client> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }
}
