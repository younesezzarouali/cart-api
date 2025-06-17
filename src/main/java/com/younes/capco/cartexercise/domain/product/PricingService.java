package com.younes.capco.cartexercise.domain.product;

import com.younes.capco.cartexercise.domain.product.model.ProductType;
import com.younes.capco.cartexercise.domain.client.model.Client;
import com.younes.capco.cartexercise.domain.client.model.IndividualClient;
import com.younes.capco.cartexercise.domain.client.model.CorporateClient;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    public double getUnitPrice(ProductType type, Client client) {
        if (client instanceof IndividualClient) {
            return switch (type) {
                case HIGH_END_PHONE -> 1500;
                case MID_RANGE_PHONE -> 800;
                case LAPTOP -> 1200;
            };
        } else if (client instanceof CorporateClient corp) {
            if (corp.annualRevenue() > 10_000_000) {
                return switch (type) {
                    case HIGH_END_PHONE -> 1000;
                    case MID_RANGE_PHONE -> 550;
                    case LAPTOP -> 900;
                };
            } else {
                return switch (type) {
                    case HIGH_END_PHONE -> 1150;
                    case MID_RANGE_PHONE -> 600;
                    case LAPTOP -> 1000;
                };
            }
        }
        throw new IllegalArgumentException("Unknown client type");
    }
}

