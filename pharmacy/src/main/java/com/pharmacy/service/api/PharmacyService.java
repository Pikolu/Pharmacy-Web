package com.pharmacy.service.api;

import com.pharmacy.domain.Evaluation;
import com.pharmacy.domain.Pharmacy;
import com.pharmacy.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Alexander on 09.01.2016.
 */
public interface PharmacyService {

    Optional<Pharmacy> getPharmacyByName(String name) throws ServiceException;

    List<Pharmacy> findBestPharmacies() throws ServiceException;

    List<Pharmacy> findPharmaciesByName(String pharmacyName) throws ServiceException;

    void saveEvaluation(String pharmId, Evaluation evaluation) throws ServiceException;

    Pharmacy getPharmacyById(String pharmId) throws ServiceException;
}
