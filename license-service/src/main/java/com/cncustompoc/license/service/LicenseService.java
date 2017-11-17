package com.cncustompoc.license.service;


import com.cncustompoc.license.domain.*;
import com.cncustompoc.license.repository.LicenseRepository;
import com.cncustompoc.license.repository.LicenseTraceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class LicenseService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private LicenseRepository licenseRepository;
    @Autowired
    private LicenseTraceRepository licenseTraceRepository;
    @Transactional(rollbackFor = Throwable.class)
    public List<LicenseEntity> getLicenses() { return licenseRepository.selectLicenses();}
    @Transactional(rollbackFor = Throwable.class)
    public LicenseEntity getLicenseByID(String ID) {
        return licenseRepository.selectLicenseByID(ID);
    }
    @Transactional(rollbackFor = Throwable.class)
    public void saveLicense(LicenseEntity entity) {
        licenseRepository.updateLicenseByID(entity);
    }
    @Transactional(rollbackFor = Throwable.class)
    public void createNewLicense(LicenseEntity entity) {
        licenseRepository.insertLicense(entity);
    }
    @Transactional(rollbackFor = Throwable.class)
    public boolean checkLicense(DeclarationAndGoodsEntity entity) {
        DeclarationEntity declarationEntity = entity.getDeclarationEntity();
        GoodsInfoEntity[] goodsInfoEntity = entity.getGoodsInfoEntity();
        boolean r = true;
        for(int i=0;i<goodsInfoEntity.length;i++)
        {
            List<LicenseEntity> licenseEntity = licenseRepository
                    .selectLicensesByEnterpriseIDAndGoodsCode(
                            declarationEntity.getEnterpriseID(),
                            goodsInfoEntity[i].getGoodsCode()
                    );
            log.info(">>>>>>>>>>>>>checkLicense>>>>>>>>>>>>EnterpriseID:"+declarationEntity.getEnterpriseID()
                    +",GoodsCode:"+goodsInfoEntity[i].getGoodsCode()+",licenseEntity.size:"+licenseEntity.size());

            if(licenseEntity.size()==0)
            {
                continue;
            }
            LicenseEntity le = licenseEntity.get(0);
            log.info(">>>>>>>>>>>>>checkLicense>>>>>>>>>>>>EnterpriseAvailableNumber:"
                    +le.getEnterpriseAvailableNumber()+",GoodsQuantity:"+goodsInfoEntity[i].getGoodsQuantity()+"");
            if(le.getEnterpriseAvailableNumber()<goodsInfoEntity[i].getGoodsQuantity())
            {
                log.info(">>>>>>>>>>>>>checkLicense>>>>>>>>>>>>r=false");
                r=false;
                break;
            }
        }
        return r;
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean deductLicense(DeclarationAndGoodsEntity entity) {
        DeclarationEntity declarationEntity = entity.getDeclarationEntity();
        GoodsInfoEntity[] goodsInfoEntity = entity.getGoodsInfoEntity();
        System.out.println("++goodsInfoEntity length : "+goodsInfoEntity.length);
        for(int i=0;i<goodsInfoEntity.length;i++)
        {
            System.out.println("++i : "+i);

            List<LicenseEntity> licenseEntity = licenseRepository
                    .selectLicensesByEnterpriseIDAndGoodsCode(
                            declarationEntity.getEnterpriseID(),
                            goodsInfoEntity[i].getGoodsCode()
                    );
            System.out.println("++licenseEntity size : "+licenseEntity.size());
            if(licenseEntity.size()==0)
            {
                continue;
            }
            LicenseEntity le = licenseEntity.get(0);
            int oldAvailableNumber = le.getEnterpriseAvailableNumber();
            int newAvailableNumber = le.getEnterpriseAvailableNumber()-goodsInfoEntity[i].getGoodsQuantity();
            if(newAvailableNumber<0)
            {
                return false;
            }
            System.out.println("++LicenseEntity getEnterpriseAvailableNumber : "+le.getEnterpriseAvailableNumber());
            System.out.println("++goodsInfoEntity[i] getGoodsQuantity : "+goodsInfoEntity[i].getGoodsQuantity());
            le.setEnterpriseAvailableNumber(newAvailableNumber);
            licenseRepository.updateLicenseByID(le);
            //记录轨迹
            LicenseTraceEntity lte = new LicenseTraceEntity();
            lte.setDeclarationID(declarationEntity.getID());
            lte.setLicenseID(le.getLicenseID());
            lte.setEnterpriseAvailableNumberNew(newAvailableNumber);
            lte.setEnterpriseAvailableNumberOld(oldAvailableNumber);
            lte.setEnterpriseID(le.getEnterpriseID());
            lte.setEnterpriseName(le.getEnterpriseName());
            lte.setGoodsCode(le.getGoodsCode());
            lte.setGoodsID(le.getGoodsID());
            licenseTraceRepository.insertLicenseTrace(lte);
        }
        return true;
    }

    @Transactional(rollbackFor = Throwable.class)
    public boolean reDeductLicense(DeclarationAndGoodsEntity entity) {
        DeclarationEntity declarationEntity = entity.getDeclarationEntity();
        List<LicenseTraceEntity> lte = licenseTraceRepository.seLicenseTraceByDeclarationID(declarationEntity.getID());
        System.out.println("+++declarationEntity.getID() : "+declarationEntity.getID());
        System.out.println("+++List<LicenseTraceEntity> size : "+lte.size());
        for(int i=0;i<lte.size();i++)
        {
            System.out.println("+++i : "+i);
            LicenseTraceEntity ltei = lte.get(i);
            List<LicenseEntity> le = licenseRepository
                    .selectLicensesByEnterpriseIDAndGoodsCode(ltei.getEnterpriseID(),ltei.getGoodsCode());
            System.out.println("+++List<LicenseEntity> size : "+le.size());
            for(int j=0;j<le.size();j++)
            {
                System.out.println("+++j : "+j);
                LicenseEntity lej = le.get(j);
                lej.setEnterpriseAvailableNumber(
                        lej.getEnterpriseAvailableNumber()
                                +ltei.getEnterpriseAvailableNumberOld()
                                -ltei.getEnterpriseAvailableNumberNew()
                );
                licenseRepository.updateLicenseByID(lej);
            }
            licenseTraceRepository.deleteLicenseTraceByID(ltei.getID());
        }
        return true;
    }

}
