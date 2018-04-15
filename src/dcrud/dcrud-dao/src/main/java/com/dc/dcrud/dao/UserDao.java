package com.dc.dcrud.dao;

import com.dc.dcrud.domain.UserEntity;
import com.dc.frame2.core.dao.SearcherJpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/13.
 */
public interface UserDao extends JpaRepository<UserEntity, Long> ,SearcherJpaRepository<UserEntity, Long>{
}
