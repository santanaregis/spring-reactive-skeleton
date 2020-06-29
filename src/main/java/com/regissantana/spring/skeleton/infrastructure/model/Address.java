package com.regissantana.spring.skeleton.infrastructure.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

import static com.regissantana.spring.skeleton.infrastructure.error.Exceptions.asBusinessException;
import static com.regissantana.spring.skeleton.infrastructure.error.SkeletonErrorDefinition.ZIP_CODE_MANDATORY;
import static org.apache.commons.lang3.StringUtils.isBlank;

@Document
public class Address {

    @Id
    private String id;
    @Indexed
    private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String censusId;
    private ZonedDateTime createDate;
    private ZonedDateTime updateDate;

    public Address(String id, String zipCode, String street, String complement, String neighborhood, String city, String state, String censusId, ZonedDateTime createDate, ZonedDateTime updateDate) {
        this.id = id;
        this.zipCode = zipCode;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
        this.censusId = censusId;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public String getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCensusId() {
        return censusId;
    }

    public String getZipCode() {
        return zipCode;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ZonedDateTime getUpdateDate() {
        return updateDate;
    }

    public static Builder create() {
        return new Builder();
    }

    public static Builder from(final Address oldAddress) {
        return create()
                .id(oldAddress.getId())
                .zipCode(oldAddress.getZipCode())
                .street(oldAddress.getStreet())
                .complement(oldAddress.getComplement())
                .neighborhood(oldAddress.getNeighborhood())
                .city(oldAddress.getCity())
                .state(oldAddress.getState())
                .censusId(oldAddress.getCensusId())
                .createDate(oldAddress.getCreateDate())
                .updateDate(oldAddress.getUpdateDate())
                ;
    }

    public static class Builder {
        private String id;
        private String zipCode;
        private String street;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;
        private String censusId;
        private ZonedDateTime createDate;
        private ZonedDateTime updateDate;

        public Builder id(final String id) {
            this.id = id;
            return this;
        }

        public Builder zipCode(final String zipCode) {
            this.zipCode = zipCode;
            return this;
        }

        public Builder street(final String street) {
            this.street = street;
            return this;
        }

        public Builder complement(final String complement) {
            this.complement = complement;
            return this;
        }

        public Builder neighborhood(final String neighborhood) {
            this.neighborhood = neighborhood;
            return this;
        }

        public Builder city(final String city) {
            this.city = city;
            return this;
        }

        public Builder state(final String state) {
            this.state = state;
            return this;
        }

        public Builder censusId(final String censusId) {
            this.censusId = censusId;
            return this;
        }

        public Builder createDate(final ZonedDateTime createDate) {
            this.createDate = createDate;
            return this;
        }

        public Builder updateDate(final ZonedDateTime updateDate) {
            this.updateDate = updateDate;
            return this;
        }

        public Address build() {

            if (isBlank(zipCode)) {
                asBusinessException(ZIP_CODE_MANDATORY);
            }

            return new Address(id, zipCode, street, complement, neighborhood, city, state, censusId, createDate, updateDate);
        }
    }

}
