package com.pharmacy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Alexander on 12.11.2015.
 */
@Controller
public class SearchController {

    /**
     * This method searched the articles for the result list in search field.
     *
     * @param parameter for search the articles
     * @param page selected or current page.
     * @return
     */
    @RequestMapping(value = "produkte", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView search(@RequestParam String parameter, @RequestParam(required = false) String page) {
        ModelAndView resultView = new ModelAndView("search");
        resultView.addObject("parameter", parameter);
        return resultView;
    }
}
