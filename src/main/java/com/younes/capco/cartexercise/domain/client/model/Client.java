package com.younes.capco.cartexercise.domain.client.model;

public sealed interface Client permits IndividualClient, CorporateClient {
    String id();
}

