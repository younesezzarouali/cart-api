package com.younes.capco.cartexercise.api.client;

import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.client.port.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClientController.class)
public class ClientControllerTest {

    @Autowired private MockMvc mockMvc;
    @MockBean private ClientRepository clientRepository;

    @Test
    void shouldCreateClient() throws Exception {
        // Given
        String json = "{\"id\":\"id1\",\"firstName\":\"John\",\"lastName\":\"Doe\"}";
        // When / Then
        mockMvc.perform(post("/api/clients")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk());
        then(clientRepository).should().save(any(IndividualClient.class));
    }

    @Test
    void shouldGetClientWhenExists() throws Exception {
        // Given
        IndividualClient client = new IndividualClient("id1","John","Doe");
        given(clientRepository.findById("id1")).willReturn(java.util.Optional.of(client));
        // When / Then
        mockMvc.perform(get("/api/clients/id1"))
            .andExpect(status().isOk())
            .andExpect(content().json(jsonOf(client)));
    }

    @Test
    void shouldReturn404WhenClientNotFound() throws Exception {
        // Given
        given(clientRepository.findById("unknown")).willReturn(java.util.Optional.empty());
        // When / Then
        mockMvc.perform(get("/api/clients/unknown"))
            .andExpect(status().isNotFound());
    }

    // Utilitaire pour sérialiser un objet en JSON (à adapter selon votre projet)
    private String jsonOf(Object obj) {
        try {
            return new com.fasterxml.jackson.databind.ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

