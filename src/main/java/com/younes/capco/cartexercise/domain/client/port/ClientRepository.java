package com.younes.capco.cartexercise.domain.client.port;

import com.younes.capco.cartexercise.domain.client.model.Client;
import java.util.Optional;

public interface ClientRepository {
    void save(Client client);
    Optional<Client> findById(String id);
}

