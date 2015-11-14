package com.pharmacy.repository.search;

import com.pharmacy.domain.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by Alexander on 14.11.2015.
 */
public interface ArticleSearchRepository extends ElasticsearchRepository<Article, Long> {
}
