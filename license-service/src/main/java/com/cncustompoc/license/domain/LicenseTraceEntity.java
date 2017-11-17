package com.cncustompoc.license.domain;

import java.sql.Date;

public class LicenseTraceEntity {
    private long ID;
    private String LicenseID;
    private String GoodsID;
    private String GoodsCode;
    private String EnterpriseID;
    private String EnterpriseName;
    private long DeclarationID ;

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

    public String getGoodsCode() {
        return GoodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        GoodsCode = goodsCode;
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

    public long getDeclarationID() {
        return DeclarationID;
    }

    public void setDeclarationID(long declarationID) {
        DeclarationID = declarationID;
    }

    public int getEnterpriseAvailableNumberOld() {
        return EnterpriseAvailableNumberOld;
    }

    public void setEnterpriseAvailableNumberOld(int enterpriseAvailableNumberOld) {
        EnterpriseAvailableNumberOld = enterpriseAvailableNumberOld;
    }

    public int getEnterpriseAvailableNumberNew() {
        return EnterpriseAvailableNumberNew;
    }

    public void setEnterpriseAvailableNumberNew(int enterpriseAvailableNumberNew) {
        EnterpriseAvailableNumberNew = enterpriseAvailableNumberNew;
    }

    private int EnterpriseAvailableNumberOld;
    private int EnterpriseAvailableNumberNew;

    public long getID() {
        return ID;
    }
    public void setID(long ID) {
        this.ID = ID;
    }

}
