/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.1.200
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : 192.168.1.200:3306
 Source Schema         : lion

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 15/04/2019 15:12:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jdbc
-- ----------------------------
DROP TABLE IF EXISTS `jdbc`;
CREATE TABLE `jdbc` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of jdbc
-- ----------------------------
BEGIN;
INSERT INTO `jdbc` VALUES (1);
INSERT INTO `jdbc` VALUES (1);
INSERT INTO `jdbc` VALUES (1);
COMMIT;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id` varchar(48) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
BEGIN;
INSERT INTO `oauth_client_details` VALUES ('lion-zuul-server', NULL, '{bcrypt}$2a$10$iq0/gR20ZXaSPkxyQAWlleRHZsl/8cfmpQ4JXqqccjiNSKh88y4LG', 'server', 'password,authorization_code,refresh_token,implicit', NULL, '', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL COMMENT '菜单编码',
  `p_code` varchar(255) DEFAULT NULL COMMENT '菜单父编码',
  `p_id` varchar(255) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `url` varchar(255) DEFAULT NULL COMMENT '请求地址',
  `is_menu` int(11) DEFAULT NULL COMMENT '是否是菜单(1.菜单。2.按钮)',
  `level` int(11) DEFAULT NULL COMMENT '菜单层级',
  `sort` int(11) DEFAULT NULL COMMENT '菜单排序',
  `status` int(11) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `FK_CODE` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_menu` VALUES (1, '1', NULL, NULL, '首页', '/index', 1, 1, 1, 1, NULL, '2019-04-15 11:16:02', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `value` varchar(255) DEFAULT NULL,
  `tips` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`name`),
  UNIQUE KEY `unique_role_value` (`value`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'super', NULL, '2017-06-20 15:08:45', NULL, 1);
INSERT INTO `sys_role` VALUES (2, '管理员', 'admin', NULL, '2017-06-20 15:07:13', '2017-06-26 12:46:09', 1);
INSERT INTO `sys_role` VALUES (3, '用户', 'user', NULL, '2017-06-28 18:50:39', '2017-07-21 09:41:28', 1);
COMMIT;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` int(11) NOT NULL,
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_menu` VALUES (1, 1, 1, '2019-04-15 11:18:26', 'DBA');
COMMIT;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `avatar` varchar(255) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(96) DEFAULT NULL,
  `salt` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_user_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES (1, NULL, 'super', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', NULL, '超级管理员', '2017-06-22 14:26:09', 1, NULL, NULL, 1, '2017-06-20 15:12:16', '2017-09-12 14:39:48');
INSERT INTO `sys_user` VALUES (2, NULL, 'admin', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', NULL, '管理员', NULL, 1, NULL, NULL, 1, '2017-06-26 17:31:41', NULL);
INSERT INTO `sys_user` VALUES (3, NULL, 'test1', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', NULL, 'test1', NULL, 1, NULL, NULL, 1, '2017-09-18 16:11:15', NULL);
INSERT INTO `sys_user` VALUES (4, NULL, 'test2', '{bcrypt}$2a$10$zt3xDTDmnFFZdzaTZSPUhu.ZhvQYijtGpj4y5BrkBn/6lKi/SQQZ2', NULL, 'test2', NULL, 1, NULL, NULL, 1, '2017-09-21 17:09:51', NULL);
COMMIT;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `create_by` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES (1, 1, 1, '2019-04-10 11:57:40', 'DBA');
INSERT INTO `sys_user_role` VALUES (2, 2, 2, '2019-04-10 11:58:02', 'DBA');
INSERT INTO `sys_user_role` VALUES (3, 2, 3, '2019-04-10 11:58:19', 'DBA');
INSERT INTO `sys_user_role` VALUES (4, 3, 3, '2019-04-10 11:58:44', 'DBA');
COMMIT;

-- ----------------------------
-- Table structure for temp_jpa
-- ----------------------------
DROP TABLE IF EXISTS `temp_jpa`;
CREATE TABLE `temp_jpa` (
  `id` varchar(32) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for temp_mybatis
-- ----------------------------
DROP TABLE IF EXISTS `temp_mybatis`;
CREATE TABLE `temp_mybatis` (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
