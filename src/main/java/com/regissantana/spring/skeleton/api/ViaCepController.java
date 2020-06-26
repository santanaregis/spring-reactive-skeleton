package com.regissantana.spring.skeleton.api;

import com.regissantana.spring.skeleton.api.dto.AddressDTO;
import com.regissantana.spring.skeleton.service.AddressService;
import io.swagger.annotations.ApiOperation;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/viacep")
public class ViaCepController {

    private static final Logger logger = LoggerFactory.getLogger(ViaCepController.class);
    private final MapperFacade mapperFacade;
    private final AddressService addressService;

    public ViaCepController(AddressService addressService, MapperFacade mapperFacade, AddressService addressService1) {
        this.mapperFacade = mapperFacade;
        this.addressService = addressService1;
    }

    @ApiOperation(value = "Save address by viacep", response = AddressDTO.class)
    @PostMapping("/{zipCode}")
    public Mono<AddressDTO> save(@PathVariable(name = "zipCode") String zipCode) {
        return this.addressService.saveFromViaCep(zipCode)
                .map(address -> mapperFacade.map(address, AddressDTO.class));
    }

}
