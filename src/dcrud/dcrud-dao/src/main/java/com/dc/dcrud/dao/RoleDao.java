package com.dc.dcrud.dao;

import com.dc.dcrud.domain.RoleEntity;
import com.dc.frame2.core.dao.SearcherJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/4.
 */
public interface RoleDao extends JpaRepository<RoleEntity, Long>, SearcherJpaRepository<RoleEntity, Long> {
}
