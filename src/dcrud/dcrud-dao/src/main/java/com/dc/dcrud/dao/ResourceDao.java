package com.dc.dcrud.dao;

import com.dc.dcrud.domain.ResourceEntity;
import com.dc.frame2.core.dao.SearcherJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/5.
 */
public interface ResourceDao extends JpaRepository<ResourceEntity, Long>, SearcherJpaRepository<ResourceEntity, Long> {
    @Query("from ResourceEntity where type=:#{T(com.dc.dcrud.domain.ResourceEntity.Type).MENU.getValue()} and parent is null order by sortOrder")
    List<ResourceEntity> findRootMenu();
}
