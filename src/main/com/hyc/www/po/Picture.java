

package com.hyc.www.po;

import com.hyc.www.po.abs.BaseEntity;

/**
 * @program XHotel
 * @description 图片
 */
public class Picture extends BaseEntity {
    String authorId;
    String pictrue;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getPictrue() {
        return pictrue;
    }

    public void setPictrue(String pictrue) {
        this.pictrue = pictrue;
    }
}
