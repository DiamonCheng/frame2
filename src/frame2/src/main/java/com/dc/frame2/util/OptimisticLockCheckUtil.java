package com.dc.frame2.util;

import com.dc.frame2.core.domain.BaseConfigEntity;

import javax.persistence.OptimisticLockException;
import java.util.Objects;

/**
 * <p>Descriptions...
 *
 * @author Diamon.Cheng
 * @date 2018/12/5.
 */
public class OptimisticLockCheckUtil {
    public static void checkOptimisticLock(BaseConfigEntity dbEntity, BaseConfigEntity incomeEntity) {
        if (!Objects.equals(dbEntity.getVersion(), incomeEntity.getVersion())) {
            throw new OptimisticLockException("You have an old entity version to update.");
        }
    }
}
