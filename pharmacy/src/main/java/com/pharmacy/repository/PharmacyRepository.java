package com.pharmacy.repository;

import com.pharmacy.domain.Article;
import com.pharmacy.domain.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Alexander on 14.11.2015.
 */
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {


}
