package com.pharmacy.service.impl;

import com.pharmacy.domain.Article;
import com.pharmacy.domain.Price;
import com.pharmacy.repository.ArticleRepository;
import com.pharmacy.repository.search.ArticleSearchRepository;
import com.pharmacy.repository.search.PriceSearchRepository;
import com.pharmacy.service.api.ArticleService;
import org.apache.commons.lang.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.facet.FacetBuilders;
import org.elasticsearch.search.facet.range.RangeFacetBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.facet.FacetRequest;
import org.springframework.data.elasticsearch.core.facet.request.NativeFacetRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.inject.Inject;


/**
 * Created by Alexander on 14.11.2015.
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Inject
    private ArticleSearchRepository articleSearchRepository;
    @Inject
    private ArticleRepository articleRepository;
    @Inject
    private PriceSearchRepository priceSearchRepository;

    @Override
    public Page<Article> findArticlesByBestPrice(Pageable pageable) {
        Page<Article> page = articleSearchRepository.findAll(pageable);
        return page;
    }

    @Override
    public FacetedPage<Article> findArticlesByParameter(String parameter, Pageable pageable) {

        RangeFacetBuilder test = FacetBuilders.rangeFacet("f")
                .field("prices.price")         // Field to compute on
                .addUnboundedFrom(10)    // from -infinity to 3 (excluded)
                .addRange(11, 20)         // from 3 to 6 (excluded)
                .addUnboundedTo(20);     // from 6 to +infinity

        FacetRequest facetRequest = new NativeFacetRequest(test);

        QueryBuilder queryBuilder;
        if (StringUtils.isBlank(parameter)) {
            queryBuilder = QueryBuilders.matchAllQuery();
        } else {
            queryBuilder = QueryBuilders.matchQuery("name", parameter);
        }

        SortBuilder sortBuilder = new FieldSortBuilder("prices.price");

        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(queryBuilder).withFacet(facetRequest).withPageable(pageable).withSort(sortBuilder).build();

        FacetedPage<Article> articles = articleSearchRepository.search(searchQuery);

        return articles;
    }

    @Override
    public Article findArticleByArticleNumber(String articelNumber) {
        Assert.hasText(articelNumber);
        return articleRepository.findArticleByArticleNumber(Integer.valueOf(articelNumber));
    }
}
