package com.web.Blog.repository.es;

import com.web.Blog.domain.es.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsBlogRespository extends ElasticsearchRepository<EsBlog, String>{
    Page<EsBlog> findDistinctEsBlogByTitleContainingOrSummaryContainingOrContentContaining(String title,String summary, String content, Pageable pageable);
    Page<EsBlog> findDistinctEsBlogByTitleOrSummaryOrContent(String title,String summary, String content, Pageable pageable);
}
