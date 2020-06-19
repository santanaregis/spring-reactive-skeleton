package com.regissantana.spring.skeleton.domain.service;

import com.regissantana.spring.skeleton.domain.dto.AddressDto;

import java.util.List;
import java.util.Optional;

public interface IAddressService {
    List<AddressDto> findAll();
    AddressDto findById(Long id);
    AddressDto save(AddressDto addressDto);
    AddressDto update(AddressDto addressDto, Long id);
    void delete(Long id);
    Optional<AddressDto> findByZipCode(String zipCode);

    AddressDto saveFromViaCep(String zipCode);
}
