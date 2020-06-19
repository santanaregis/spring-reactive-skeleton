package com.regissantana.spring.skeleton.api.v1;

import com.regissantana.spring.skeleton.domain.dto.AddressDto;
import com.regissantana.spring.skeleton.infrastructure.service.AddressService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/viacep")
public class ViaCepController {

    private static final Logger logger = LoggerFactory.getLogger(ViaCepController.class);
    private final AddressService addressService;

    @Autowired
    public ViaCepController(AddressService addressService, AddressService addressService1) {
        this.addressService = addressService1;
    }

    @ApiOperation(value = "Save address by viacep", response = AddressDto.class)
    @PostMapping("/{zipCode}")
    public ResponseEntity<?> save(@PathVariable(name = "zipCode") String zipCode) {
        try {
            return ResponseEntity.ok(this.addressService.saveFromViaCep(zipCode));
        } catch (RuntimeException ex) {
            logger.error("Error saving address by viacep: {}", ex);
            return ResponseEntity.status(500).build();
        }
    }
}
