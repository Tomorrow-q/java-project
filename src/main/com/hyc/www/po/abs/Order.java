
package com.hyc.www.po.abs;

import java.math.BigDecimal;

/**
 * @program XHotel
 * @description 所有订单的抽象父类，包括房间订单，服务订单都是其子类
 */
public abstract class Order extends BaseEntity {
    private String number;
    private String userId;
    private String startTime;
    private String endTime;
    private BigDecimal amount;
    private String remark;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


}
