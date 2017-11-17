package com.cncustompoc.license.domain;

public class DeclarationEntity {
    private long ID;
    private String EnterpriseID="";
    private String EnterpriseName="";
    private String EnterpriseInfo="";
    private String AttachedInfo="";
    private double FreightCost;
    private double InsuranceCost;
    private double OtherCost;
    private String status="0";

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getEnterpriseInfo() {
        return EnterpriseInfo;
    }

    public void setEnterpriseInfo(String enterpriseInfo) {
        EnterpriseInfo = enterpriseInfo;
    }

    public String getAttachedInfo() {
        return AttachedInfo;
    }

    public void setAttachedInfo(String attachedInfo) {
        AttachedInfo = attachedInfo;
    }

    public double getFreightCost() {
        return FreightCost;
    }

    public void setFreightCost(double freightCost) {
        FreightCost = freightCost;
    }

    public double getInsuranceCost() {
        return InsuranceCost;
    }

    public void setInsuranceCost(double insuranceCost) {
        InsuranceCost = insuranceCost;
    }

    public double getOtherCost() {
        return OtherCost;
    }

    public void setOtherCost(double otherCost) {
        OtherCost = otherCost;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
