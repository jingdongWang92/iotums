package com.jcnetwork.iotums.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 定义抽象的 entity父类, 将公共属性放于些
 *
 * @author Jingdong Wang
 */
public abstract class AbstractEntity implements Serializable {

    private static final long serialVersionUID = 6569365774429340632L;
    protected Integer id;

    /**用于实现逻辑删除*/
    protected boolean archived;
    /**
     * Domain business guid.
     * 业务Id
     */
    protected String guid;

    /**
     * The domain create time.
     */
    protected Date createTime;

    public AbstractEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean getArchived() {
        return archived;
    }
    
    public AbstractEntity setArchived(boolean archived) {
        this.archived = archived;
        return this;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Date getCreateTime() {
        return createTime;
    }
    
    public void setCreateTime(Date createTime) {
    	this.createTime = createTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AbstractEntity)) {
            return false;
        }
        AbstractEntity that = (AbstractEntity) o;
        return guid.equals(that.guid);
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }

    //For subclass override it
    public void saveOrUpdate() {
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{id=").append(id);
        sb.append(", archived=").append(archived);
        sb.append(", guid='").append(guid).append('\'');
        sb.append(", createTime=").append(createTime);
        sb.append('}');
        return sb.toString();
    }
}