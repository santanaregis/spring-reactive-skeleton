package com.regissantana.spring.skeleton.api;

import com.regissantana.spring.skeleton.api.dto.AddressDTO;
import com.regissantana.spring.skeleton.infrastructure.model.Address;
import com.regissantana.spring.skeleton.service.AddressService;
import io.swagger.annotations.*;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final MapperFacade mapperFacade;
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService, MapperFacade mapperFacade, AddressService addressService1) {
        this.mapperFacade = mapperFacade;
        this.addressService = addressService1;
    }

    @ApiOperation(value = "Get address list", response = AddressDTO.class)
    @GetMapping
    public Flux<AddressDTO> findAll() {
        return addressService.findAll()
                .map(address -> mapperFacade.map(address, AddressDTO.class));
    }

    @ApiOperation(value = "Get address by id", response = AddressDTO.class)
    @GetMapping("/{id}")
    public Mono<AddressDTO> findById(@PathVariable(name = "id") String id) {
        return this.addressService.findById(id)
                .map(address -> mapperFacade.map(address, AddressDTO.class));
    }

    @ApiOperation(value = "Save address", response = AddressDTO.class)
    @PostMapping
    public Mono<AddressDTO> save(@RequestBody AddressDTO addressDTO) {
        return Mono.just(addressDTO)
                .map(dto -> mapperFacade.map(dto, Address.class))
                .flatMap(this.addressService::save)
                .map(address -> mapperFacade.map(address, AddressDTO.class));
    }

    @ApiOperation(value = "Update id address", response = AddressDTO.class)
    @PutMapping("/{id}")
    public Mono<AddressDTO> updateById(@PathVariable(name = "id") String id, @RequestBody AddressDTO addressDto) {
        return addressService.update(addressDto, id)
                .map(address -> mapperFacade.map(address, AddressDTO.class));
    }

    @ApiOperation(value = "Delete address")
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable(name = "id") String id) {
        return this.addressService.delete(id);
    }

}
