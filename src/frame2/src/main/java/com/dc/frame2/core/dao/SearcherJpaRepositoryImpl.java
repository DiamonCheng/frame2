package com.dc.frame2.core.dao;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.core.dto.Searcher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * <p>searcher based jpa query impl...
 *
 * @author DC
 * @date 2018/4/14.
 */

public class SearcherJpaRepositoryImpl<T,PK extends Serializable> extends SimpleJpaRepository<T,PK> implements SearcherJpaRepository<T,PK> {
    public SearcherJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation,entityManager);
    }
    @Override
    public void searchPage(PageSearcher<T> pageSearcher) {
        Page<T> page = findAll(
                (root, query, builder) -> ConditionResolver.getInstance().resolve(pageSearcher,root,query,builder),
                pageSearcher
        );
        pageSearcher.setResultList(page.getContent());
        pageSearcher.setTotalCount(page.getTotalElements());
    }
    
    @Override
    public void search(Searcher<T> searcher) {
        List<T> resultList = findAll(
                (root, query, builder) ->
                        ConditionResolver.getInstance().resolve(searcher,root,query,builder)
        );
        searcher.setResultList(resultList);
    }
}
