/*
Navicat MySQL Data Transfer

 Source Server         : Mooc
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : 119.23.63.134:3306
 Source Schema         : mooc

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

Date: 2020-03-27 00:15:55
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `device_logic`
-- ----------------------------
DROP TABLE IF EXISTS `device_logic`;
CREATE TABLE `device_logic` (
  `number` int(11) NOT NULL AUTO_INCREMENT,
  `Device_id` int(11) NOT NULL,
  `Device_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `Device_type` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `Device_source` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Device_status` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `Device_ip` varchar(100) DEFAULT NULL,
  `Device_eqlocation` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `Contact_name` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `Contact_phone` varchar(15) DEFAULT NULL,
  `Registrant_name` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `Registrant_date` date DEFAULT NULL,
  `photo` longblob,
  PRIMARY KEY (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of device_logic
-- ----------------------------
INSERT INTO `device_logic` VALUES ('1', '1222', 'Mobile', '手机', '购买', '在用', '123.11.11.3', '成都', '小方', '17740982311', '小方', '2020-03-18', null);
INSERT INTO `device_logic` VALUES ('2', '1223', 'PC', '电脑', '购买', '在用', '123.11.11.4', '成都', '小明', '17740982312', '小方', '2020-03-09', null);
INSERT INTO `device_logic` VALUES ('3', '1224', 'PC', '电脑', '购买', '停用', '123.11.11.5', '成都', '小李', '17740982313', '小方', '2020-03-06', null);
INSERT INTO `device_logic` VALUES ('4', '1225', 'Mobile', '手机', '购买', '停用', '123.11.11.6', '重庆', '小李', '17740982313', '小明', '2020-03-03', null);
