package com.regissantana.spring.skeleton.infrastructure.config;

import com.regissantana.spring.skeleton.api.dto.AddressDTO;
import com.regissantana.spring.skeleton.infrastructure.converter.AddressToAddressDTOConverter;
import com.regissantana.spring.skeleton.infrastructure.converter.ViaCepResponseToAddressConverter;
import com.regissantana.spring.skeleton.infrastructure.model.Address;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfiguration {

    private MapperFactory getMapperFactory() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.getConverterFactory().registerConverter(new ViaCepResponseToAddressConverter());
        mapperFactory.getConverterFactory().registerConverter(new AddressToAddressDTOConverter());

        mapperFactory
                .classMap(AddressDTO.class, Address.class)
                .mapNulls(false)
                .mapNullsInReverse(false)
                .byDefault()
                .register();

        return mapperFactory;
    }

    @Bean
    public MapperFacade mapperFacade() {
        return getMapperFactory().getMapperFacade();
    }
}
