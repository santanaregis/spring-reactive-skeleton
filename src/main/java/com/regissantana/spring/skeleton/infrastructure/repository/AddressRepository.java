package com.regissantana.spring.skeleton.infrastructure.repository;

import com.regissantana.spring.skeleton.infrastructure.model.Address;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface AddressRepository extends ReactiveMongoRepository<Address, String> {
    Mono<Address> findByZipCode(String zipCode);
}
