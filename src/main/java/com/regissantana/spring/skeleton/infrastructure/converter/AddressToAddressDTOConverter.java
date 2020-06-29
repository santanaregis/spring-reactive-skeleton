package com.regissantana.spring.skeleton.infrastructure.converter;

import com.regissantana.spring.skeleton.api.dto.AddressDTO;
import com.regissantana.spring.skeleton.client.dto.ViaCepResponse;
import com.regissantana.spring.skeleton.infrastructure.model.Address;
import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class AddressToAddressDTOConverter extends CustomConverter<Address, AddressDTO> {

    @Override
    public AddressDTO convert(Address source, Type<? extends AddressDTO> destinationType, MappingContext mappingContext) {
        final AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(source.getId());
        addressDTO.setZipCode(source.getZipCode());
        addressDTO.setStreet(source.getStreet());
        addressDTO.setComplement(source.getComplement());
        addressDTO.setNeighborhood(source.getNeighborhood());
        addressDTO.setCity(source.getCity());
        addressDTO.setState(source.getState());
        addressDTO.setCensusId(source.getCensusId());
        addressDTO.setCreateDate(source.getCreateDate());
        addressDTO.setUpdateDate(source.getUpdateDate());

        return addressDTO;
    }

}
