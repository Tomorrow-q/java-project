
package com.hyc.www.po.abs;

import java.util.Date;

/**
 * @program XHotel
 * @description 所有数据库记录的父类
 */
public abstract class BaseEntity {
    private String id;
    private Integer status;
    private Date gmtCreate;//创建时间
    private Date gmtModified;//修改时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


}
