package com.younes.capco.cartexercise.repository.adapter.client;

import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.client.model.CorporateClient;
import com.younes.capco.cartexercise.domain.client.model.Client;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;

public class InMemoryClientRepositoryTest {

    private final InMemoryClientRepository repo = new InMemoryClientRepository();

    @Test
    void shouldSaveAndFindIndividualClient() {
        // Given
        Client client = new IndividualClient("id1", "John", "Doe");
        // When
        repo.save(client);
        Optional<Client> found = repo.findById("id1");
        // Then
        assertTrue(found.isPresent());
        assertEquals(client, found.get());
    }

    @Test
    void shouldSaveAndFindCorporateClient() {
        // Given
        Client client = new CorporateClient("id2","Acme", Optional.of("VAT123"),"123456789",5_000_000);
        // When
        repo.save(client);
        Optional<Client> found = repo.findById("id2");
        // Then
        assertTrue(found.isPresent());
        assertEquals(client, found.get());
    }

    @Test
    void shouldReturnEmptyWhenNotFound() {
        // Given no client saved
        // When
        Optional<Client> found = repo.findById("unknown");
        // Then
        assertFalse(found.isPresent());
    }
}

