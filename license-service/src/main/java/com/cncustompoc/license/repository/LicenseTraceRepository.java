package com.cncustompoc.license.repository;

import com.cncustompoc.license.domain.LicenseEntity;
import com.cncustompoc.license.domain.LicenseTraceEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LicenseTraceRepository {
    @Select("select * from LicenseTrace where ID =#{ID}")
    public LicenseTraceEntity selectLicenseTraceByID(@Param("ID") String ID);
    @Select("select * from LicenseTrace")
    public List<LicenseTraceEntity> selectLicenseTrace();
    @Select("select * from LicenseTrace where DeclarationID =#{DeclarationID}")
    public List<LicenseTraceEntity> seLicenseTraceByDeclarationID(@Param("DeclarationID") long DeclarationID);
    @Insert("insert into LicenseTrace(LicenseID,GoodsID,GoodsCode,EnterpriseID,EnterpriseName,DeclarationID,EnterpriseAvailableNumberOld,EnterpriseAvailableNumberNew) " +
            "values (#{LicenseID},#{GoodsID},#{GoodsCode},#{EnterpriseID},#{EnterpriseName},#{DeclarationID},#{EnterpriseAvailableNumberOld},#{EnterpriseAvailableNumberNew})")
    public void insertLicenseTrace(LicenseTraceEntity licenseTraceEntity);
    @Update("update LicenseTrace set LicenseID=#{LicenseID},GoodsID=#{GoodsID},GoodsCode=#{GoodsCode},EnterpriseID=#{EnterpriseID}," +
            "EnterpriseName=#{EnterpriseName},DeclarationID=#{DeclarationID},EnterpriseAvailableNumberOld=#{EnterpriseAvailableNumberOld},EnterpriseAvailableNumberNew=#{EnterpriseAvailableNumberNew} where ID=#{ID}")
    public void updateLicenseTraceByID(LicenseTraceEntity licenseTraceEntity);
    @Delete("Delete from LicenseTrace where ID =#{ID}")
    public void deleteLicenseTraceByID(@Param("ID") long ID);
}
