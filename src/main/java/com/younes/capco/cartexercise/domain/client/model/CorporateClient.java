package com.younes.capco.cartexercise.domain.client.model;

import java.util.Optional;

public record CorporateClient(
    String id,
    String companyName,
    Optional<String> vatNumber,
    String siren,
    double annualRevenue
) implements Client {}

