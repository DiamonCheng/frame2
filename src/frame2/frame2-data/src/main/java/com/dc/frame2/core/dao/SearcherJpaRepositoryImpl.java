package com.dc.frame2.core.dao;

import com.dc.frame2.core.dto.PageSearcher;
import com.dc.frame2.core.dto.Searcher;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * <p>searcher based jpa query impl...
 *
 * @author DC
 * @date 2018/4/14.
 */
@Repository("searcherJpaRepository")
public class SearcherJpaRepositoryImpl<T> extends SimpleJpaRepository<T,Serializable> implements SearcherJpaRepository<T> {
    private Class<T> domainClass;
    private EntityManager entityManager;
    public SearcherJpaRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass,em);
        this.domainClass=domainClass;
        this.entityManager=em;
    }
    @Override
    public Page<T> searchPage(PageSearcher pageSearcher) {
        return null;
    }
    
    @Override
    public List<T> search(Searcher searcher) {
        return null;
    }
}
