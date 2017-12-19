--------------------------------------------------------
--  文件已创建 - 星期二-十二月-19-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table LICENSE
--------------------------------------------------------

  CREATE TABLE "CUSTOMS"."LICENSE" 
   (	"ID" NUMBER(*,0), 
	"LICENSEID" VARCHAR2(100 BYTE), 
	"GOODSID" NUMBER(*,0), 
	"ENTERPRISEID" VARCHAR2(100 BYTE), 
	"ENTERPRISENAME" VARCHAR2(100 BYTE), 
	"ENTERPRISEVALIDNUMBER" NUMBER(*,0), 
	"ENTERPRISEAVAILABLENUMBER" NUMBER(*,0), 
	"GOODSTAX" NUMBER(10,2), 
	"VALIDDATE" DATE, 
	"GOODSCODE" VARCHAR2(100 BYTE)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Index SYS_C0019492
--------------------------------------------------------

  CREATE UNIQUE INDEX "CUSTOMS"."SYS_C0019492" ON "CUSTOMS"."LICENSE" ("ID") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS" ;
--------------------------------------------------------
--  DDL for Trigger LICENSE_ID
--------------------------------------------------------

  CREATE OR REPLACE TRIGGER "CUSTOMS"."LICENSE_ID" 
  before insert on license 
  for each row
declare
  nextid number;
begin
  IF :new.id IS NULL or :new.id=0 THEN 
    select seq_license.nextval 
    into nextid
    from sys.dual;
    :new.id:=nextid;
  end if;
end license_id;
/
ALTER TRIGGER "CUSTOMS"."LICENSE_ID" ENABLE;
--------------------------------------------------------
--  Constraints for Table LICENSE
--------------------------------------------------------

  ALTER TABLE "CUSTOMS"."LICENSE" ADD PRIMARY KEY ("ID")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
  BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "USERS"  ENABLE;
