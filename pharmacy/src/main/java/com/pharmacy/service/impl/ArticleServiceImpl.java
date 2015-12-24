package com.pharmacy.service.impl;

import com.pharmacy.domain.Article;
import com.pharmacy.repository.search.ArticleSearchRepository;
import com.pharmacy.service.api.ArticleService;
import org.elasticsearch.common.collect.Lists;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Created by Alexander on 14.11.2015.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleSearchRepository articleSearchRepository;

    @Override
    public Page<Article> findArticlesByBestPrice(Pageable pageable) {
        Page<Article> page = articleSearchRepository.findAll(pageable);
        return page;
    }

    @Override
    public List<Article> findArticlesByParameter(String parameter) {
        return Lists.newArrayList(articleSearchRepository.findAll());
    }
}
