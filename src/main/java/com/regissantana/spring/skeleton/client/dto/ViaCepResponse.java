package com.regissantana.spring.skeleton.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ViaCepResponse {

    @JsonProperty(value = "logradouro")
    private String street;
    @JsonProperty(value = "complemento")
    private String complement;
    @JsonProperty(value = "bairro")
    private String neighborhood;
    @JsonProperty(value = "localidade")
    private String city;
    @JsonProperty(value = "uf")
    private String state;
    @JsonProperty(value = "ibge")
    private String censusId;
    @JsonProperty(value = "cep")
    private String zipCode;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCensusId() {
        return censusId;
    }

    public void setCensusId(String censusId) {
        this.censusId = censusId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
