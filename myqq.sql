/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : myqq

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-05-10 15:15:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `msg`
-- ----------------------------
DROP TABLE IF EXISTS `msg`;
CREATE TABLE `msg` (
  `msg_id` int(11) NOT NULL AUTO_INCREMENT,
  `msg_content` varchar(2000) DEFAULT NULL,
  `msg_sendfrom` int(11) DEFAULT NULL,
  `msg_sendto` int(11) DEFAULT NULL,
  `msg_sendtime` datetime DEFAULT NULL,
  `msg_remark` varchar(3000) DEFAULT NULL,
  `msg_type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`msg_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg
-- ----------------------------

-- ----------------------------
-- Table structure for `users`
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `u_id` int(11) NOT NULL AUTO_INCREMENT,
  `u_name` varchar(50) DEFAULT NULL,
  `u_pwd` varchar(50) DEFAULT NULL,
  `u_ip` varchar(50) DEFAULT NULL,
  `u_state` varchar(50) DEFAULT NULL,
  `u_gender` varchar(50) DEFAULT NULL,
  `u_email` varchar(50) DEFAULT NULL,
  `u_last_login` datetime DEFAULT NULL,
  `u_last_exit` datetime DEFAULT NULL,
  `u_remarke` varchar(3000) DEFAULT NULL,
  `u_signature` varchar(100) DEFAULT NULL,
  `u_head_img` varchar(100) DEFAULT NULL,
  `u_type` varchar(50) DEFAULT NULL,
  `u_birthday` date DEFAULT NULL,
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', '小茗同学', '123', '127.0.0.1', '-1', '男', 'jfs@qq.com', '2017-05-10 14:49:23', '2017-05-10 14:52:22', null, '大家好，我是小茗同学！', '0', null, '1992-07-28');
INSERT INTO `users` VALUES ('2', '马化腾', '123', '127.0.0.1', '-1', '男', 'ss@qq.com', '2017-05-10 14:49:09', '2017-05-10 14:52:25', null, '哈哈，我是马化腾！', '3', null, '1992-07-28');
INSERT INTO `users` VALUES ('3', '吴阳阳', '123', '172.16.4.97', '0', '男', 'fjs@qq.com ', '2015-04-21 18:01:57', null, null, '我与徐婷婷不得不说的事儿', '4', null, '1992-07-28');
