package com.cncustompoc.license.domain;



public class LicenseCheckEntity {

    public DeclarationEntity getDeclarationEntity() {
        return declarationEntity;
    }

    public void setDeclarationEntity(DeclarationEntity declarationEntity) {
        this.declarationEntity = declarationEntity;
    }


    public DeclarationEntity declarationEntity;

    public GoodsInfoEntity[] getGoodsInfoEntity() {
        return GoodsInfoEntity;
    }

    public void setGoodsInfoEntity(GoodsInfoEntity[] goodsInfoEntity) {
        GoodsInfoEntity = goodsInfoEntity;
    }

    public GoodsInfoEntity[] GoodsInfoEntity;

}
