package com.pharmacy.web;

import com.pharmacy.domain.Article;
import com.pharmacy.service.api.ArticleService;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Alexander on 12.11.2015.
 */
@Controller
public class SearchController {

    private static final Logger LOG = LoggerFactory.getLogger(IndexController.class);

    @Inject
    private ArticleService articleService;

    /**
     * This method searched the articles for the result list in search field.
     *
     * @param parameter for search the articles
     * @param page selected or current page.
     * @return
     */
    @RequestMapping(value = "produkte", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView search(@RequestParam String parameter, Pageable pageable) {
        ModelAndView resultView = new ModelAndView("search");
        resultView.addObject("parameter", parameter);
        return resultView;
    }

    @RequestMapping(value = "/suche", method = RequestMethod.GET)
    public @ResponseBody
    List<Article> search(HttpServletRequest request, @RequestParam String parameter) {
        List<Article> articles = null;
        try {
            LOG.info("SEARCH_REQUEST: {}", parameter);
            articles = articleService.findArticlesByParameter(parameter);
        } catch (ServiceException ex) {
            ex.fillInStackTrace();
        }
        return articles;
    }
}
