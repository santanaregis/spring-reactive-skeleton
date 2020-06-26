package com.regissantana.spring.skeleton.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.regissantana.spring.skeleton.client.ViaCepClient;
import com.regissantana.spring.skeleton.api.dto.AddressDTO;
import com.regissantana.spring.skeleton.client.dto.ViaCepResponse;
import com.regissantana.spring.skeleton.infrastructure.model.Address;
import com.regissantana.spring.skeleton.infrastructure.repository.AddressRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.ZonedDateTime;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ViaCepClient viaCepClient;
    private final MapperFacade mapperFacade;

    public AddressService(AddressRepository addressRepository, ViaCepClient viaCepClient, MapperFacade mapperFacade) {
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
        this.mapperFacade = mapperFacade;
    }

    public Flux<Address> findAll() {
        return addressRepository.findAll();
    }

    public Mono<Address> findById(String id) {
        return addressRepository.findById(id);
    }

    public Mono<Address> save(Address address) {
        return addressRepository.save(address);
    }

    public Mono<Address> update(AddressDTO addressDTO, String id) {
        return addressRepository.findById(id)
                .map(address ->
                        Address.from(address)
                                .complement(addressDTO.getComplement())
                                .updateDate(ZonedDateTime.now())
                                .build())
                .flatMap(this::save);
    }

    public Mono<Void> delete(String id) {
        return this.addressRepository.deleteById(id);
    }

    public Mono<Address> findByZipCode(String zipCode) {
        return Mono.just(zipCode)
                .flatMap(this.addressRepository::findByZipCode);
    }

    public Mono<Address> saveFromViaCep(String zipCode) {
        return viaCepClient
                .findByZipCode(zipCode)
                .map(this::mapToAddress)
                .map(address -> Address.from(address)
                        .createDate(ZonedDateTime.now())
                        .build()
                )
                .flatMap(address -> addressRepository.save(address))
                ;
    }

    private Address mapToAddress(final ViaCepResponse viaCepResponse) {
        return mapperFacade.map(viaCepResponse, Address.class);
    }

}
