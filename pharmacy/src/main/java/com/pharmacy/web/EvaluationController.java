package com.pharmacy.web;

import com.pharmacy.domain.Evaluation;
import com.pharmacy.domain.Pharmacy;
import com.pharmacy.exceptions.ServiceException;
import com.pharmacy.service.api.PharmacyService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by Alexander on 09.01.2016.
 */
@Controller
public class EvaluationController extends AbstractController {

    private final static Logger LOG = LoggerFactory.getLogger(EvaluationController.class);

    @Inject
    private PharmacyService pharmacyService;

    @RequestMapping(value = "/bewertungen", method = RequestMethod.GET)
    public ModelAndView initEvaluations(@RequestParam(required = false) String pharmacyName) {

        ModelAndView model = new ModelAndView();

        // check if user is login
        if (getCustomUserDetails() != null) {
            model.setViewName("evaluations");
            if (StringUtils.isNotBlank(pharmacyName)) {
                List<Pharmacy> pharmacies = null;
                try {
                    pharmacies = pharmacyService.findPharmaciesByName(pharmacyName);
                } catch (ServiceException ex) {
                    LOG.error("Test");
                }
                model.addObject("pharmacyName", pharmacyName);
                model.addObject("pharmacies", pharmacies);
            }

        } else {
            model.setViewName("login");
        }

        return model;
    }

    @RequestMapping(value = "/bewerten", method = RequestMethod.GET)
    public ModelAndView displayPharmacy(@RequestParam String pharm, HttpServletRequest request, HttpSession session, Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("evaluations");
        Page<Pharmacy> pharmacy = null;
        try {
            pharmacy = pharmacyService.getPharmacyByName(pharm, pageable);
        } catch (ServiceException ex) {
            LOG.error("");
        }
        modelAndView.addObject("evaluations", new Evaluation());
        modelAndView.addObject("pharm", pharm);
        modelAndView.addObject("pharmacy", pharmacy);
        return modelAndView;
    }

    @RequestMapping(value = "/{pharmId}/bewerten", method = RequestMethod.POST)
    public ModelAndView evaluate(@ModelAttribute("evaluation") Evaluation evaluation, @PathVariable String pharmId, BindingResult result) {
        ModelAndView modelAndView = null;
        try {
//            evaluationValidator.validate(evaluation, result);
            if (result.hasErrors()) {
                modelAndView = new ModelAndView("evaluate");
                Pharmacy pharmacy = pharmacyService.getPharmacyById(pharmId);
                modelAndView.addObject("pharmacy", pharmacy);
            } else {
                pharmacyService.saveEvaluation(pharmId, evaluation);
            }
        } catch (ServiceException ex) {

        }
        return modelAndView;

    }
}
