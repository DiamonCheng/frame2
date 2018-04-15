package com.dc.frame2.core.dao;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.core.dto.Searcher;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * <p> Provide searcher based conditional query support.
 * @param <T> The domain class
 * @author DC
 * @date 2018/4/14.
 */
@NoRepositoryBean
public interface SearcherJpaRepository<T,PK extends Serializable> extends Repository<T,PK> {
    /**
     * Query entity pageable and sortable. The condition is defined in searcher
     * @param pageSearcher page, sort and query condition definition. @see PageSearcher
     * @return A page object with total count and list of query results
     */
    Page<T> searchPage(PageSearcher<T> pageSearcher);
    
    /**
     * Query entity with condition defined in searcher.
     * @param searcher search condition. @see Searcher
     * @return query result list
     */
    List<T> search(Searcher<T> searcher);
}
