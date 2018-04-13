package com.dc.frame2.core.domain;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * <p>Descriptions...
 *
 * @author DC
 * @date 2018/4/13.
 */
@MappedSuperclass
public class BaseConfigEntity {
    private static final long serialVersionUID = 1L;
    
    /**
     * 主键ID自动生成策略
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long id;
    
    /**
     * 版本号
     */
    @Version
    @Column(name = "version")
    protected Integer version;
    
    /**
     * 创建时间
     */
    @Column(name = "create_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date createDateTime;
    
    /**
     * 最后修改时间
     */
    @Column(name = "update_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Date updateDateTime;
    
    public Long getId() {
        return id;
    }
    
    public BaseConfigEntity setId(Long id) {
        this.id = id;
        return this;
    }
    
    public Integer getVersion() {
        return version;
    }
    
    public BaseConfigEntity setVersion(Integer version) {
        this.version = version;
        return this;
    }
    
    public Date getCreateDateTime() {
        return createDateTime;
    }
    
    public BaseConfigEntity setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
        return this;
    }
    
    public Date getUpdateDateTime() {
        return updateDateTime;
    }
    
    public BaseConfigEntity setUpdateDateTime(Date updateDateTime) {
        this.updateDateTime = updateDateTime;
        return this;
    }
    
    /**
     * toString
     */
    @Override
    public String toString() {
        return "BaseConfigEntity{" +
                       "id=" + id +
                       ", version=" + version +
                       ", createDateTime=" + createDateTime +
                       ", updateDateTime=" + updateDateTime +
                       '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        return obj != null && obj.getClass().equals(getClass()) && Objects.equals(id, ((BaseConfigEntity) obj).id);
    }
    
    @Override
    public int hashCode() {
        return id==null?0:id.hashCode();
    }
}
