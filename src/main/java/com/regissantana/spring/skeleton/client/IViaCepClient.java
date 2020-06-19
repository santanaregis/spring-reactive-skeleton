package com.regissantana.spring.skeleton.client;

import com.regissantana.spring.skeleton.domain.dto.ViaCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "viaCepClient", url = "${via-cep.client.url}")
public interface IViaCepClient {

    @GetMapping(value = "ws/{zipCode}/json")
    ResponseEntity<ViaCepResponse> findZipCode(@PathVariable(value = "zipCode") String zipCode);

}
