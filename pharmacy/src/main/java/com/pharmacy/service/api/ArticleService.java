package com.pharmacy.service.api;

import com.pharmacy.domain.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Alexander on 14.11.2015.
 */
public interface ArticleService {

    Page<Article> findArticlesByBestPrice(Pageable pageable);

    List<Article> findArticlesByParameter(String parameter);
}
