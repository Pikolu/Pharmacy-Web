package com.pharmacy.repository;

import com.pharmacy.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Created by Alexander on 14.11.2015.
 */
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

    @Query("SELECT a FROM Pharmacy a WHERE a.name = :name")
    Optional<Pharmacy> findPharmacyByName(@Param(value = "name") String name);

}
