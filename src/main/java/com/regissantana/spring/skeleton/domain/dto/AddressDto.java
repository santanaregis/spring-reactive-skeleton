package com.regissantana.spring.skeleton.domain.dto;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;

public class AddressDto implements Serializable {

    private Long id;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String censusId;
    private String zipCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressDto that = (AddressDto) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(street, that.street) &&
                Objects.equal(complement, that.complement) &&
                Objects.equal(neighborhood, that.neighborhood) &&
                Objects.equal(city, that.city) &&
                Objects.equal(state, that.state) &&
                Objects.equal(censusId, that.censusId) &&
                Objects.equal(zipCode, that.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, street, complement, neighborhood, city, state, censusId, zipCode);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("street", street)
                .add("complement", complement)
                .add("neighborhood", neighborhood)
                .add("city", city)
                .add("state", state)
                .add("censusId", censusId)
                .add("zipCode", zipCode)
                .toString();
    }
}
