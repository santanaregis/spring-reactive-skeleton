package com.regissantana.spring.skeleton.domain.dto.converter;

import com.regissantana.spring.skeleton.domain.dto.ViaCepResponse;
import com.regissantana.spring.skeleton.domain.entity.Address;

public class AddressConverter {

    public static Address fromViaCepResponse(final ViaCepResponse viaCepResponse) {
        Address address = new Address();
        address.setCensusId(viaCepResponse.getCensusId());
        address.setCity(viaCepResponse.getCity());
        address.setComplement(viaCepResponse.getComplement());
        address.setNeighborhood(viaCepResponse.getNeighborhood());
        address.setState(viaCepResponse.getState());
        address.setStreet(viaCepResponse.getStreet());
        address.setZipCode(viaCepResponse.getZipCode());

        return address;
    }

}
