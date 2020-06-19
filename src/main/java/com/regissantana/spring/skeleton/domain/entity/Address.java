package com.regissantana.spring.skeleton.domain.entity;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "COMPLEMENT")
    private String complement;

    @Column(name = "NEIGHBORHOOD")
    private String neighborhood;

    @Column(name = "CITY")
    private String city;

    @Column(name = "STATE")
    private String state;

    @Column(name = "CENSUS_ID")
    private String censusId;

    @Column(name = "ZIP_CODE")
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
        Address address = (Address) o;
        return Objects.equal(id, address.id) &&
                Objects.equal(street, address.street) &&
                Objects.equal(complement, address.complement) &&
                Objects.equal(neighborhood, address.neighborhood) &&
                Objects.equal(city, address.city) &&
                Objects.equal(state, address.state) &&
                Objects.equal(censusId, address.censusId) &&
                Objects.equal(zipCode, address.zipCode);
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
