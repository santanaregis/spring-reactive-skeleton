package com.regissantana.spring.skeleton.domain.repository;

import com.regissantana.spring.skeleton.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAddressRepository extends JpaRepository<Address, Long> {

    Address findByZipCode(String zipCode);

}
