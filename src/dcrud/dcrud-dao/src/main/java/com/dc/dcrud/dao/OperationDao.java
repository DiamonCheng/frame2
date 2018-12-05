package com.dc.dcrud.dao;

import com.dc.dcrud.domain.OperationEntity;
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
public interface OperationDao extends JpaRepository<OperationEntity, Long>, SearcherJpaRepository<OperationEntity, Long> {
    @Query("from OperationEntity where type=:#{T(com.dc.dcrud.domain.OperationEntity.Type).MENU} and parent is null order by sortOrder")
    List<OperationEntity> findRootMenu();
}
