/*
 Navicat Premium Data Transfer

 Source Server         : tencent_mysql
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 118.25.236.128:3306
 Source Schema         : Java_L2_lagouEdu

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 08/07/2020 15:49:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
                        `id` int(11) NOT NULL,
                        `username` varchar(50) DEFAULT NULL,
                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'lucy');
INSERT INTO `user` VALUES (2, 'tom');
INSERT INTO `user` VALUES (3, '张三');
INSERT INTO `user` VALUES (4, '李四');
INSERT INTO `user` VALUES (5, '王五');
INSERT INTO `user` VALUES (6, '赵六');
INSERT INTO `user` VALUES (7, 'zhangsan');
INSERT INTO `user` VALUES (8, 'lisi');
INSERT INTO `user` VALUES (9, 'wangwu');
INSERT INTO `user` VALUES (10, 'zhaoliu');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
