package com.regissantana.spring.skeleton.infrastructure.converter;

import com.regissantana.spring.skeleton.client.dto.ViaCepResponse;
import com.regissantana.spring.skeleton.infrastructure.model.Address;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class ViaCepResponseToAddressConverter extends CustomConverter<ViaCepResponse, Address> {

    @Override
    public Address convert(ViaCepResponse source, Type<? extends Address> destinationType, MappingContext mappingContext) {
        return Address.create()
                .zipCode(source.getZipCode())
                .street(source.getStreet())
                .complement(source.getComplement())
                .neighborhood(source.getNeighborhood())
                .city(source.getCity())
                .state(source.getState())
                .censusId(source.getCensusId())
                .build();
    }

}
