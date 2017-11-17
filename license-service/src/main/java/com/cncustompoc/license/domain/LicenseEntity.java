package com.cncustompoc.license.domain;

import java.sql.Date;

public class LicenseEntity {
    private long ID;

    public String getLicenseID() {
        return LicenseID;
    }

    public void setLicenseID(String licenseID) {
        LicenseID = licenseID;
    }

    public String getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(String goodsID) {
        GoodsID = goodsID;
    }

    public String getEnterpriseID() {
        return EnterpriseID;
    }

    public void setEnterpriseID(String enterpriseID) {
        EnterpriseID = enterpriseID;
    }

    public String getEnterpriseName() {
        return EnterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        EnterpriseName = enterpriseName;
    }

    public int getEnterpriseValidNumber() {
        return EnterpriseValidNumber;
    }

    public void setEnterpriseValidNumber(int enterpriseValidNumber) {
        EnterpriseValidNumber = enterpriseValidNumber;
    }

    public int getEnterpriseAvailableNumber() {
        return EnterpriseAvailableNumber;
    }

    public void setEnterpriseAvailableNumber(int enterpriseAvailableNumber) {
        EnterpriseAvailableNumber = enterpriseAvailableNumber;
    }

    public double getGoodsTax() {
        return GoodsTax;
    }

    public void setGoodsTax(double goodsTax) {
        GoodsTax = goodsTax;
    }

    public Date getValidDate() {
        return ValidDate;
    }

    public void setValidDate(Date validDate) {
        ValidDate = validDate;
    }

    private String LicenseID;
    private String GoodsID;

    public String getGoodsCode() {
        return GoodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        GoodsCode = goodsCode;
    }

    private String GoodsCode;
    private String EnterpriseID;
    private String EnterpriseName;
    private int EnterpriseValidNumber;
    private int EnterpriseAvailableNumber;
    private double GoodsTax;
    private Date ValidDate ;

    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }

}
