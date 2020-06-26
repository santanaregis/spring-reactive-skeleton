package com.regissantana.spring.skeleton.infrastructure.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class AddressRepositoryImpl {

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressRepositoryImpl.class);

    public AddressRepositoryImpl(final ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }


}
