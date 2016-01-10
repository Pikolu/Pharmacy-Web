package com.pharmacy.service.impl;

import com.pharmacy.domain.Evaluation;
import com.pharmacy.domain.Pharmacy;
import com.pharmacy.exceptions.PersistenceException;
import com.pharmacy.exceptions.ServiceException;
import com.pharmacy.repository.PharmacyRepository;
import com.pharmacy.service.api.PharmacyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alexander on 09.01.2016.
 */
@Service
public class PharmacyServiceImpl implements PharmacyService {

    private static final Logger LOG = LoggerFactory.getLogger(PharmacyServiceImpl.class);

    @Inject
    private PharmacyRepository pharmacyRepository;

    @Override
    public Page<Pharmacy> getPharmacyByName(String name, Pageable pageable) throws ServiceException {
        return pharmacyRepository.findPharmacyByName(name, pageable);
    }

    @Override
    public List<Pharmacy> findBestPharmacies() throws ServiceException {
        List<Pharmacy> pharmacies = null;
//        try {
//            pharmacies = pharmacyRepository.findBestPharmacies();
//        } catch (PersistenceException ex) {
//            ex.writeLog(LOG);
//            throw ex;
//        }
        return pharmacies;
    }

    @Override
    public List<Pharmacy> findPharmaciesByName(String pharmacyName) throws ServiceException {
        List<Pharmacy> pharmacies = null;
//        try {
//            pharmacies = pharmacyRepository.findPharmaciesByName(pharmacyName);
//        } catch (PersistenceException ex) {
//            ex.writeLog(LOG);
//            throw ex;
//        }
        return pharmacies;
    }

    @Override
    public void saveEvaluation(String pharmId, Evaluation evaluation) throws ServiceException {
//        try {
//            calculateTotalEvaluation(evaluation);
////            pharmacyRepository.saveEvaluation(pharmId, evaluation);
//        } catch (PersistenceException ex) {
//            ex.writeLog(LOG);
//            throw ex;
//        }
    }

    @Override
    public Pharmacy getPharmacyById(String pharmId) throws ServiceException {
        Assert.notNull(pharmId);
        return pharmacyRepository.getOne(Long.valueOf(pharmId));
    }


    private void calculateTotalEvaluation(Evaluation evaluation) {
        evaluation.setPoints((float)(evaluation.getDescriptionPoints() + evaluation.getShippingPoints() + evaluation.getShippingPricePoints()) / 3);
    }

}
