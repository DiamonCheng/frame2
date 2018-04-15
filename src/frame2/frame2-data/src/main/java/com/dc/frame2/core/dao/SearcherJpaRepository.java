package com.dc.frame2.core.dao;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.core.dto.Searcher;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * <p> Provide searcher based conditional query support.
 * @param <T> The domain class
 * @author DC
 * @date 2018/4/14.
 */
public interface SeacherJpaRepository<T> {
    /**
     * Query entity pageable and sortable. The condition is defined in searcher
     * @param pageSearcher page, sort and query condition definition. @see PageSearcher
     * @return A page object with total count and list of query results
     */
    Page<T> searchPage(PageSearcher pageSearcher);
    
    /**
     * Query entity with condition defined in searcher.
     * @param searcher search condition. @see Searcher
     * @return query result list
     */
    List<T> search(Searcher searcher);
}
