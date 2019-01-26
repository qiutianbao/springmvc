/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50709
Source Host           : localhost:3306
Source Database       : rep

Target Server Type    : MYSQL
Target Server Version : 50709
File Encoding         : 65001

Date: 2016-08-02 20:43:03
*/
create database bigdata;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `rptinfo`
-- ----------------------------
DROP TABLE IF EXISTS `rptinfo`;
CREATE TABLE `rptinfo` (
  `ID` varchar(7) COLLATE utf8_bin NOT NULL,
  `requestid` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `rpt_title` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `rpt_subtit` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `rpt_date` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `rpt_type` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `secu_sht` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `trad_cod` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `rpt_indu` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `rpt_authors` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `rpt_summary` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `attach_id` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `attach_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `attach_dir` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `counter_see` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  `counter_download` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of rptinfo
-- ----------------------------

-- ----------------------------
-- Table structure for `sysuser`
-- ----------------------------
DROP TABLE IF EXISTS `sysuser`;
CREATE TABLE `sysuser` (
  `USERID` varchar(7) COLLATE utf8_bin NOT NULL,
  `USERNAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `NICKNAME` varchar(100) COLLATE utf8_bin NOT NULL,
  `PWD` varchar(100) COLLATE utf8_bin NOT NULL,
  `STATUS` int(11) NOT NULL,
  `USERTYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of sysuser
-- ----------------------------
INSERT INTO `sysuser` VALUES ('1', 'admin', '管理员', '21232f297a57a5a743894a0e4a801fc3', '1', '1');

-- ----------------------------
-- Table structure for `uf_report_contact_type`
-- ----------------------------
DROP TABLE IF EXISTS `uf_report_contact_type`;
CREATE TABLE `uf_report_contact_type` (
  `report_type_id` varchar(7) COLLATE utf8_bin NOT NULL,
  `rpt_typ_id` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `contact_id` varchar(10) COLLATE utf8_bin DEFAULT NULL,
  `report_type_name` varchar(100) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`report_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of uf_report_contact_type
-- ----------------------------

-- ----------------------------
-- View structure for `vw_allow_rpt_sysuser`
-- ----------------------------
DROP VIEW IF EXISTS `vw_allow_rpt_sysuser`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_allow_rpt_sysuser` AS select `sysuser`.`USERID` AS `USERID`,`sysuser`.`USERNAME` AS `USERNAME`,`sysuser`.`NICKNAME` AS `NICKNAME`,`sysuser`.`PWD` AS `PWD`,`sysuser`.`STATUS` AS `STATUS`,`sysuser`.`USERTYPE` AS `USERTYPE` from `sysuser` ;

-- ----------------------------
-- View structure for `vw_rptinfo`
-- ----------------------------
DROP VIEW IF EXISTS `vw_rptinfo`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vw_rptinfo` AS select `rptinfo`.`ID` AS `ID`,`rptinfo`.`requestid` AS `REQUESTID`,`rptinfo`.`rpt_title` AS `RPT_TITLE`,`rptinfo`.`rpt_authors` AS `RPT_AUTHORS`,`rptinfo`.`rpt_date` AS `RPT_DATE`,`rptinfo`.`secu_sht` AS `SECU_SHT`,`rptinfo`.`rpt_type` AS `RPT_TYPE`,`rptinfo`.`rpt_indu` AS `RPT_INDU`,`rptinfo`.`counter_see` AS `COUNTER_SEE`,`rptinfo`.`counter_download` AS `COUNTER_DOWNLOAD`,`rptinfo`.`attach_dir` AS `attach_dir` from `rptinfo` ;
