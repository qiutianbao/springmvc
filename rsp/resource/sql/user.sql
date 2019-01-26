CREATE TABLE sysuser
(
USERID VARCHAR2(7) NOT NULL,
USERNAME VARCHAR2(100) NOT NULL,
NICKNAME VARCHAR2(100) NOT NULL,
PWD VARCHAR2(100) NOT NULL,
STATUS NUMBER(2) NOT NULL,
USERTYPE VARCHAR2(7)
)

ALTER TABLE sysuser ADD CONSTRAINT PK_INFOS PRIMARY KEY(USERID)


create or replace view VW_ALLOW_RPT_SYSUSER as
  SELECT
         USERID,
         USERNAME,
         NICKNAME,
         PWD,
         STATUS,
         USERTYPE
    from sysuser

