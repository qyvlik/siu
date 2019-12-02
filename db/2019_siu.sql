/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50637
 Source Host           : localhost
 Source Database       : 2019_siu

 Target Server Type    : MySQL
 Target Server Version : 50637
 File Encoding         : utf-8

 Date: 12/02/2019 17:14:50 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `tb_gen_id`
-- ----------------------------
DROP TABLE IF EXISTS `tb_gen_id`;
CREATE TABLE `tb_gen_id` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `segment_begin` bigint(20) NOT NULL COMMENT 'id 段起始',
  `segment_size` int(11) NOT NULL COMMENT 'id 段大小',
  `machine_id` bigint(20) NOT NULL COMMENT '机器ID',
  `state` tinyint(1) NOT NULL COMMENT '状态',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  KEY `state_time` (`state`,`create_time`),
  KEY `segmentbegin` (`segment_begin`)
) ENGINE=InnoDB AUTO_INCREMENT=149 DEFAULT CHARSET=utf8mb4 COMMENT='id 派发器';

-- ----------------------------
--  Table structure for `tb_url_block`
-- ----------------------------
DROP TABLE IF EXISTS `tb_url_block`;
CREATE TABLE `tb_url_block` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `url` varchar(1024) NOT NULL COMMENT 'url',
  `type` varchar(16) NOT NULL COMMENT 'full, domain, path, contains',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `type` (`type`,`url`(128)) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COMMENT='Url 黑名单';

-- ----------------------------
--  Table structure for `tb_url_origin`
-- ----------------------------
DROP TABLE IF EXISTS `tb_url_origin`;
CREATE TABLE `tb_url_origin` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `origin_url` varchar(1024) NOT NULL COMMENT '源站',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='源站url';

-- ----------------------------
--  Table structure for `tb_url_short`
-- ----------------------------
DROP TABLE IF EXISTS `tb_url_short`;
CREATE TABLE `tb_url_short` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `short_url` varchar(12) NOT NULL COMMENT '短链接，id 的 base62',
  `origin_url_id` bigint(20) NOT NULL COMMENT '源站链接',
  `state` tinyint(1) NOT NULL COMMENT '状态',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  `version` int(11) NOT NULL COMMENT '乐观锁',
  PRIMARY KEY (`id`),
  KEY `state_time` (`state`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接';

-- ----------------------------
--  Table structure for `tb_view_logs`
-- ----------------------------
DROP TABLE IF EXISTS `tb_view_logs`;
CREATE TABLE `tb_view_logs` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `short_url_id` bigint(20) NOT NULL COMMENT '短网址',
  `origin_url_id` bigint(20) NOT NULL COMMENT '源网址',
  `referrer_url_id` bigint(20) NOT NULL COMMENT '网页来源',
  `user_agent_id` bigint(20) NOT NULL COMMENT '用户代理',
  `ip_address` varbinary(16) NOT NULL COMMENT 'ip 所在地',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `shorturl_time` (`short_url_id`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短链接统计';

-- ----------------------------
--  Table structure for `tb_view_useragent`
-- ----------------------------
DROP TABLE IF EXISTS `tb_view_useragent`;
CREATE TABLE `tb_view_useragent` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `user_agent_hash` varbinary(32) NOT NULL COMMENT 'user-agent sha256 hash',
  `user_agent` varchar(1024) NOT NULL COMMENT 'user-agent',
  `browser` varchar(64) NOT NULL COMMENT '浏览器',
  `browser_version` varchar(32) NOT NULL COMMENT '浏览器版本',
  `operating_system` varchar(64) NOT NULL COMMENT '操作系统',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `useragenthash` (`user_agent_hash`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户浏览器环境';

SET FOREIGN_KEY_CHECKS = 1;
