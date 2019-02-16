package com.rishiqing.qywx.dao.model.corp;

import java.util.Date;

/**
 * 公司充值状态信息。该表中记录了公司的当前状态，公司的所有充值状态信息均以该表为准
 * @author Wallace Mao
 * Date: 2018-10-18 16:38
 */
public class CorpChargeStatusDO {
    private Long id;
    private Date dateCreated;
    private Date lastUpdated;
    private String suiteKey;

    //  购买该套件企业的corpid
    private String corpId;
    //  企业总人数
    private Long totalQuantity;
    //  当前生效订单id
    private String currentOrderId;
    //  当前商品代码
    private String currentGoodsCode;
    //  当前规格代码
    private String currentItemCode;
    //  当前生效订单订购的具体人数
    private Long currentSubQuantity;
    //  当前生效订单购买的商品规格能服务的最多企业人数
    private Long currentMaxOfPeople;
    //  当前生效订单购买的商品规格能服务的最少企业人数
    private Long currentMinOfPeople;
    //  当前生效订单该企业的服务到期时间
    private Long currentServiceStopTime;
    //  公司的状态
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getSuiteKey() {
        return suiteKey;
    }

    public void setSuiteKey(String suiteKey) {
        this.suiteKey = suiteKey;
    }

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Long getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Long totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getCurrentOrderId() {
        return currentOrderId;
    }

    public void setCurrentOrderId(String currentOrderId) {
        this.currentOrderId = currentOrderId;
    }

    public Long getCurrentSubQuantity() {
        return currentSubQuantity;
    }

    public String getCurrentGoodsCode() {
        return currentGoodsCode;
    }

    public void setCurrentGoodsCode(String currentGoodsCode) {
        this.currentGoodsCode = currentGoodsCode;
    }

    public String getCurrentItemCode() {
        return currentItemCode;
    }

    public void setCurrentItemCode(String currentItemCode) {
        this.currentItemCode = currentItemCode;
    }

    public void setCurrentSubQuantity(Long currentSubQuantity) {
        this.currentSubQuantity = currentSubQuantity;
    }

    public Long getCurrentMaxOfPeople() {
        return currentMaxOfPeople;
    }

    public void setCurrentMaxOfPeople(Long currentMaxOfPeople) {
        this.currentMaxOfPeople = currentMaxOfPeople;
    }

    public Long getCurrentMinOfPeople() {
        return currentMinOfPeople;
    }

    public void setCurrentMinOfPeople(Long currentMinOfPeople) {
        this.currentMinOfPeople = currentMinOfPeople;
    }

    public Long getCurrentServiceStopTime() {
        return currentServiceStopTime;
    }

    public void setCurrentServiceStopTime(Long currentServiceStopTime) {
        this.currentServiceStopTime = currentServiceStopTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CorpChargeStatusDO{" +
                "id=" + id +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", suiteKey='" + suiteKey + '\'' +
                ", corpId='" + corpId + '\'' +
                ", totalQuantity=" + totalQuantity +
                ", currentOrderId='" + currentOrderId + '\'' +
                ", currentGoodsCode='" + currentGoodsCode + '\'' +
                ", currentItemCode='" + currentItemCode + '\'' +
                ", currentSubQuantity=" + currentSubQuantity +
                ", currentMaxOfPeople=" + currentMaxOfPeople +
                ", currentMinOfPeople=" + currentMinOfPeople +
                ", currentServiceStopTime=" + currentServiceStopTime +
                ", status='" + status + '\'' +
                '}';
    }
}
