

package com.hyc.www.po;

import com.hyc.www.po.abs.BaseEntity;

/**
 * @program XHotel
 * @description 评论的实体类
 */
public class Remark extends BaseEntity {
    String userName;
    String remark;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
