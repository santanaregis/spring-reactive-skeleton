package com.regissantana.spring.skeleton.infrastructure.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.regissantana.spring.skeleton.client.IViaCepClient;
import com.regissantana.spring.skeleton.domain.dto.AddressDto;
import com.regissantana.spring.skeleton.domain.dto.ViaCepResponse;
import com.regissantana.spring.skeleton.domain.dto.converter.AddressConverter;
import com.regissantana.spring.skeleton.domain.entity.Address;
import com.regissantana.spring.skeleton.domain.repository.IAddressRepository;
import com.regissantana.spring.skeleton.domain.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements IAddressService {

    private ObjectMapper objectMapper;
    private final IAddressRepository addressRepository;
    private final IViaCepClient viaCepClient;

    @Autowired
    public AddressService(ObjectMapper objectMapper, IAddressRepository addressRepository, IViaCepClient viaCepClient) {
        this.objectMapper = objectMapper;
        this.addressRepository = addressRepository;
        this.viaCepClient = viaCepClient;
    }

    @Override
    public List<AddressDto> findAll() {
        List<AddressDto> addresses = new ArrayList<>();
        this.addressRepository.findAll()
                .forEach(address -> {
                    addresses.add(this.objectMapper.convertValue(address, AddressDto.class));
                });
        return addresses;
    }

    @Override
    public AddressDto findById(Long id) {
        return this.objectMapper.convertValue(
                this.addressRepository.findById(id), AddressDto.class
        );
    }

    @Override
    public AddressDto save(AddressDto addressDto) {
        Address address = this.objectMapper.convertValue(addressDto, Address.class);

        return this.objectMapper.convertValue(
                this.addressRepository.save(address), AddressDto.class
        );
    }

    @Override
    public AddressDto update(AddressDto addressDto, Long id) {
        Address address = this.addressRepository.findById(id).get();
        final Address addressUpdate = objectMapper.convertValue(addressDto, Address.class);
        addressUpdate.setId(address.getId());
        address = this.addressRepository.save(addressUpdate);
        return this.objectMapper.convertValue(address, AddressDto.class);
    }

    @Override
    public void delete(Long id) {
        Address address = this.addressRepository.findById(id).get();
        this.addressRepository.delete(address);
    }

    @Override
    public Optional<AddressDto> findByZipCode(String zipCode) {
        Address byZipCode = addressRepository.findByZipCode(zipCode);
        if (byZipCode != null) {
            AddressDto addressDto = this.objectMapper.convertValue(byZipCode, AddressDto.class);
            return Optional.of(addressDto);
        }
        return Optional.empty();
    }

    @Override
    public AddressDto saveFromViaCep(String zipCode) {
        ViaCepResponse body = viaCepClient.findZipCode(zipCode).getBody();
        Address address = AddressConverter.fromViaCepResponse(body);
        return this.objectMapper.convertValue(
                this.addressRepository.save(address), AddressDto.class
        );
    }

}
