package com.younes.capco.cartexercise.domain.product;

import com.younes.capco.cartexercise.domain.product.PricingService;
import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.client.model.Client;
import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.client.model.CorporateClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PricingServiceTest {

    private PricingService pricingService = new PricingService();

    @Test
    void shouldReturnCorrectPricesForIndividualClient() {
        // Given
        Client client = new IndividualClient("id1", "John", "Doe");
        // When
        double priceHigh = pricingService.getUnitPrice(ProductType.HIGH_END_PHONE, client);
        double priceMid  = pricingService.getUnitPrice(ProductType.MID_RANGE_PHONE, client);
        double priceLap  = pricingService.getUnitPrice(ProductType.LAPTOP, client);
        // Then
        assertEquals(1500, priceHigh);
        assertEquals(800,  priceMid);
        assertEquals(1200, priceLap);
    }

    @Test
    void shouldReturnCorrectPricesForCorporateClientHighRevenue() {
        // Given
        Client client = new CorporateClient("id2", "Acme Corp", Optional.empty(), "123456789", 20_000_000);
        // When
        double high = pricingService.getUnitPrice(ProductType.HIGH_END_PHONE, client);
        double mid  = pricingService.getUnitPrice(ProductType.MID_RANGE_PHONE, client);
        double lap  = pricingService.getUnitPrice(ProductType.LAPTOP, client);
        // Then
        assertEquals(1000, high);
        assertEquals(550,  mid);
        assertEquals(900,  lap);
    }

    @Test
    void shouldReturnCorrectPricesForCorporateClientLowRevenue() {
        // Given
        Client client = new CorporateClient("id3", "Small Biz", Optional.of("VAT123"), "987654321", 5_000_000);
        // When
        double high = pricingService.getUnitPrice(ProductType.HIGH_END_PHONE, client);
        double mid  = pricingService.getUnitPrice(ProductType.MID_RANGE_PHONE, client);
        double lap  = pricingService.getUnitPrice(ProductType.LAPTOP, client);
        // Then
        assertEquals(1150, high);
        assertEquals(600,  mid);
        assertEquals(1000, lap);
    }
}

