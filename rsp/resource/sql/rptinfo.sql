CREATE TABLE rptinfo
(
ID VARCHAR(7) NOT NULL ,
requestid VARCHAR(100),
rpt_title VARCHAR(100) ,
rpt_subtit VARCHAR(100) ,
rpt_date VARCHAR(100) ,
finish_time datetime ,
rpt_type VARCHAR(10) ,
secu_sht VARCHAR(100),
trad_cod VARCHAR(100) ,
rpt_indu VARCHAR(100),
rpt_authors VARCHAR(100),
rpt_summary VARCHAR(100),
attach_id VARCHAR(100) ,
attach_name VARCHAR(100),
attach_dir VARCHAR(100) ,
counter_see VARCHAR(100) ,
counter_download VARCHAR(100)
,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;




                      create or replace view VW_RPTINFO as
  SELECT
                       ID,
                       REQUESTID,
                       RPT_TITLE,
                       RPT_AUTHORS,
                       RPT_DATE,
                       SECU_SHT,
                       RPT_TYPE,
                       RPT_INDU,
                       COUNTER_SEE,
                       COUNTER_DOWNLOAD,
                       attach_dir
    from rptinfo
 CREATE TABLE uf_report_contact_type
(
report_type_id VARCHAR(7) NOT NULL,
rpt_typ_id VARCHAR(10) ,
contact_id VARCHAR(10) ,
report_type_name VARCHAR(100)

,
  PRIMARY KEY (`report_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
