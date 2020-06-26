package com.regissantana.spring.skeleton.infrastructure.config;

import com.regissantana.spring.skeleton.infrastructure.converter.ViaCepResponseToAddressConverter;
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

        return mapperFactory;
    }

    @Bean
    public MapperFacade mapperFacade() {
        return getMapperFactory().getMapperFacade();
    }
}
