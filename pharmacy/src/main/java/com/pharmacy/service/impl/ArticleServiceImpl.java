package com.pharmacy.service.impl;

import com.pharmacy.domain.Article;
import com.pharmacy.repository.search.ArticleSearchRepository;
import com.pharmacy.service.api.ArticleService;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.facet.terms.TermsFacet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.stereotype.Service;
import javax.inject.Inject;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders.*;
import org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.node.NodeBuilder.*;

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
    public Page<Article> findArticlesByParameter(String parameter, Pageable pageable) {

        QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "Tetesept Sinnensalze Dreamtime");

        FacetedPage<Article> test = articleSearchRepository.search(queryBuilder, pageable);

        return articleSearchRepository.findAll(pageable);
    }
}
