package com.cncustompoc.license.domain;


public class DeclarationAndGoodsEntity {

    public DeclarationEntity getDeclarationEntity() {
        return declarationEntity;
    }

    public void setDeclarationEntity(DeclarationEntity declarationEntity) {
        this.declarationEntity = declarationEntity;
    }

    private DeclarationEntity declarationEntity;

    public GoodsInfoEntity[] getGoodsInfoEntity() {
        return GoodsInfoEntity;
    }

    public void setGoodsInfoEntity(GoodsInfoEntity[] goodsInfoEntity) {
        GoodsInfoEntity = goodsInfoEntity;
    }

    private GoodsInfoEntity[] GoodsInfoEntity;

}
