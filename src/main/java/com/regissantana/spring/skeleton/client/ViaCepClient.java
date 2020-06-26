package com.regissantana.spring.skeleton.client;

import com.regissantana.spring.skeleton.client.dto.ViaCepResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class ViaCepClient {

    @Value("${via-cep.client.url}")
    private String url;

    @Autowired
    WebClient webClient;

    public Mono<ViaCepResponse> findByZipCode(String zipCode) {
        return webClient.get()
                .uri(url + "/ws/{zipCode}/json", zipCode)
                .retrieve()
                .bodyToMono(ViaCepResponse.class);
    }

}
