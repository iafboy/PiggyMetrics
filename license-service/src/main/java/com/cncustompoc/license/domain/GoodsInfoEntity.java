package com.cncustompoc.license.domain;

public class GoodsInfoEntity {
    private long ID;
    private long DecID;
    private long GoodsID;

    private String GoodsCode="";
    private String GoodsName="";
    private int GoodsQuantity;
    private double GoodsPrice;
    private String GoodsFrom="";
    public String listPropertiesValue(){
        return ID+","+DecID+","+GoodsID+","+GoodsCode+","+GoodsName+","+GoodsQuantity+","+GoodsPrice+","+GoodsFrom;
    };
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public long getDecID() {
        return DecID;
    }

    public void setDecID(long decID) {
        DecID = decID;
    }

    public long getGoodsID() {
        return GoodsID;
    }

    public void setGoodsID(long goodsID) {
        GoodsID = goodsID;
    }

    public String getGoodsCode() {
        return GoodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        GoodsCode = goodsCode;
    }

    public String getGoodsName() {
        return GoodsName;
    }

    public void setGoodsName(String goodsName) {
        GoodsName = goodsName;
    }

    public int getGoodsQuantity() {
        return GoodsQuantity;
    }

    public void setGoodsQuantity(int goodsQuantity) {
        GoodsQuantity = goodsQuantity;
    }

    public double getGoodsPrice() {
        return GoodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        GoodsPrice = goodsPrice;
    }

    public String getGoodsFrom() {
        return GoodsFrom;
    }

    public void setGoodsFrom(String goodsFrom) {
        GoodsFrom = goodsFrom;
    }
}
