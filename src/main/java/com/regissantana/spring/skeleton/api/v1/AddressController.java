package com.regissantana.spring.skeleton.api.v1;

import com.regissantana.spring.skeleton.domain.dto.AddressDto;
import com.regissantana.spring.skeleton.infrastructure.service.AddressService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService, AddressService addressService1) {
        this.addressService = addressService1;
    }

    @ApiOperation(value = "Get address list", response = AddressDto.class)
    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(this.addressService.findAll());
    }

    @ApiOperation(value = "Get address by id", response = AddressDto.class)
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") Long id) {
        try {
            return ResponseEntity.ok(this.addressService.findById(id));
        } catch (RuntimeException ex) {
            logger.error("Error finding address: {}", ex);
            return ResponseEntity.status(500).build();
        }
    }

    @ApiOperation(value = "Save address", response = AddressDto.class)
    @PostMapping
    public ResponseEntity<?> save(@RequestBody AddressDto addressDto) {
        try {
            return ResponseEntity.ok(this.addressService.save(addressDto));
        } catch (RuntimeException ex) {
            logger.error("Error saving address: {}", ex);
            return ResponseEntity.status(500).build();
        }
    }

    @ApiOperation(value = "Update id address", response = AddressDto.class)
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@PathVariable(name = "id") Long id, @RequestBody AddressDto addressDto) {
        try {
            return ResponseEntity.ok(this.addressService.update(addressDto, id));
        } catch (RuntimeException ex) {
            logger.error("Error updating address: {}", ex);
            return ResponseEntity.status(500).build();
        }
    }

    @ApiOperation(value = "Delete address", response = AddressDto.class)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long id) {
        try {
            this.addressService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException ex) {
            logger.error("Error deleting address: {}", ex);
            return ResponseEntity.status(500).build();
        }
    }

}
