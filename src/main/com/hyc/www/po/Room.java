

package com.hyc.www.po;

import com.hyc.www.po.abs.Product;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @program XHotel
 * @description 用来存储房间的信息，对应数据库的房间表
 */
public class Room extends Product {
    private String photo;
    private BigDecimal area;
    private BigDecimal bedWidth;
    private String bookStatus;
    private String remark;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getBedWidth() {
        return bedWidth;
    }

    public void setBedWidth(BigDecimal bedWidth) {
        this.bedWidth = bedWidth;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
