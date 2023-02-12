/*
 Navicat Premium Data Transfer

 Source Server         : lanhaierp
 Source Server Type    : MySQL
 Source Server Version : 80031
 Source Host           : 192.168.10.10:30325
 Source Schema         : lanhe_cms

 Target Server Type    : MySQL
 Target Server Version : 80031
 File Encoding         : 65001

 Date: 12/02/2023 14:55:11
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for cms_help
-- ----------------------------
DROP TABLE IF EXISTS `cms_help`;
CREATE TABLE `cms_help`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '分类id',
  `icon` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '图标',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `read_count` int(0) NULL DEFAULT NULL COMMENT '阅读次数',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '帮助表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_help
-- ----------------------------

-- ----------------------------
-- Table structure for cms_help_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_help_category`;
CREATE TABLE `cms_help_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '分类id',
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `icon` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类图标',
  `help_count` int(0) NULL DEFAULT NULL COMMENT '专题数量',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '帮助分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_help_category
-- ----------------------------

-- ----------------------------
-- Table structure for cms_member_report
-- ----------------------------
DROP TABLE IF EXISTS `cms_member_report`;
CREATE TABLE `cms_member_report`  (
  `id` bigint(0) NULL DEFAULT NULL,
  `report_type` int(0) NULL DEFAULT NULL COMMENT '举报类型：0->商品评价；1->话题内容；2->用户评论',
  `report_member_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '举报人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `report_object` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '举报对象',
  `report_status` int(0) NULL DEFAULT NULL COMMENT '举报状态：0->未处理；1->已处理',
  `handle_status` int(0) NULL DEFAULT NULL COMMENT '处理结果：0->无效；1->有效；2->恶意',
  `note` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '备注'
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '用户举报表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_member_report
-- ----------------------------

-- ----------------------------
-- Table structure for cms_prefrence_area
-- ----------------------------
DROP TABLE IF EXISTS `cms_prefrence_area`;
CREATE TABLE `cms_prefrence_area`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '优选专区id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '优选专区名称',
  `sub_title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '子标题',
  `pic` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '展示图片',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '优选专区' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_prefrence_area
-- ----------------------------
INSERT INTO `cms_prefrence_area` VALUES (1, '让音质更出众', '音质不打折 完美现场感', NULL, NULL, 1);
INSERT INTO `cms_prefrence_area` VALUES (2, '让音质更出众22', '让音质更出众22', NULL, NULL, NULL);
INSERT INTO `cms_prefrence_area` VALUES (3, '让音质更出众33', NULL, NULL, NULL, NULL);
INSERT INTO `cms_prefrence_area` VALUES (4, '让音质更出众44', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cms_prefrence_area_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `cms_prefrence_area_product_relation`;
CREATE TABLE `cms_prefrence_area_product_relation`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `prefrence_area_id` bigint(0) NULL DEFAULT NULL COMMENT '优选专区id',
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 177 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '优选专区和产品关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_prefrence_area_product_relation
-- ----------------------------
INSERT INTO `cms_prefrence_area_product_relation` VALUES (1, 1, 12);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (2, 1, 13);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (3, 1, 14);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (4, 1, 18);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (5, 1, 7);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (6, 2, 7);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (7, 1, 22);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (24, 1, 23);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (161, 1, 59);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (162, 2, 59);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (163, 3, 59);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (164, 4, 59);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (165, 1, 58);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (166, 2, 58);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (167, 3, 58);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (168, 4, 58);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (169, 1, 57);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (170, 2, 57);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (171, 3, 57);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (172, 4, 57);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (173, 1, 55);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (174, 2, 55);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (175, 3, 55);
INSERT INTO `cms_prefrence_area_product_relation` VALUES (176, 4, 55);

-- ----------------------------
-- Table structure for cms_subject
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject`;
CREATE TABLE `cms_subject`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '专题id',
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '分类id',
  `title` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `pic` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '专题主图',
  `product_count` int(0) NULL DEFAULT NULL COMMENT '关联产品数量',
  `recommend_status` int(0) NULL DEFAULT NULL COMMENT '推荐状态',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `collect_count` int(0) NULL DEFAULT NULL COMMENT '收藏数',
  `read_count` int(0) NULL DEFAULT NULL COMMENT '阅读数',
  `comment_count` int(0) NULL DEFAULT NULL COMMENT '评论数',
  `album_pics` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '画册图片用逗号分割',
  `description` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '描述',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态：0->不显示；1->显示',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '内容',
  `forward_count` int(0) NULL DEFAULT NULL COMMENT '转发数',
  `category_name` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '专题分类名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '专题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_subject
-- ----------------------------
INSERT INTO `cms_subject` VALUES (1, 1, 'polo衬衫的也时尚', NULL, NULL, NULL, '2018-11-11 13:26:55', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '服装专题');
INSERT INTO `cms_subject` VALUES (2, 2, '大牌手机低价秒', NULL, NULL, NULL, '2018-11-12 13:27:00', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '手机专题');
INSERT INTO `cms_subject` VALUES (3, 2, '晓龙845新品上市', NULL, NULL, NULL, '2018-11-13 13:27:05', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '手机专题');
INSERT INTO `cms_subject` VALUES (4, 1, '夏天应该穿什么', NULL, NULL, NULL, '2018-11-01 13:27:09', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '服装专题');
INSERT INTO `cms_subject` VALUES (5, 1, '夏季精选', NULL, NULL, NULL, '2018-11-06 13:27:18', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '服装专题');
INSERT INTO `cms_subject` VALUES (6, 2, '品牌手机降价', NULL, NULL, NULL, '2018-11-07 13:27:21', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '手机专题');

-- ----------------------------
-- Table structure for cms_subject_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject_category`;
CREATE TABLE `cms_subject_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '专题分类名称',
  `icon` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类图标',
  `subject_count` int(0) NULL DEFAULT NULL COMMENT '专题数量',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '专题分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_subject_category
-- ----------------------------
INSERT INTO `cms_subject_category` VALUES (1, '服装专题', NULL, NULL, NULL, NULL);
INSERT INTO `cms_subject_category` VALUES (2, '手机专题', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for cms_subject_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject_comment`;
CREATE TABLE `cms_subject_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(0) NULL DEFAULT NULL COMMENT '专题id',
  `member_nick_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `member_icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '专题评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_subject_comment
-- ----------------------------

-- ----------------------------
-- Table structure for cms_subject_product_relation
-- ----------------------------
DROP TABLE IF EXISTS `cms_subject_product_relation`;
CREATE TABLE `cms_subject_product_relation`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `subject_id` bigint(0) NULL DEFAULT NULL COMMENT '专题id',
  `product_id` bigint(0) NULL DEFAULT NULL COMMENT '产品id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 317 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '专题商品关系表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_subject_product_relation
-- ----------------------------
INSERT INTO `cms_subject_product_relation` VALUES (1, 1, 12);
INSERT INTO `cms_subject_product_relation` VALUES (2, 1, 13);
INSERT INTO `cms_subject_product_relation` VALUES (3, 1, 14);
INSERT INTO `cms_subject_product_relation` VALUES (4, 1, 18);
INSERT INTO `cms_subject_product_relation` VALUES (5, 1, 7);
INSERT INTO `cms_subject_product_relation` VALUES (6, 2, 7);
INSERT INTO `cms_subject_product_relation` VALUES (7, 1, 22);
INSERT INTO `cms_subject_product_relation` VALUES (29, 1, 23);
INSERT INTO `cms_subject_product_relation` VALUES (30, 4, 23);
INSERT INTO `cms_subject_product_relation` VALUES (31, 5, 23);
INSERT INTO `cms_subject_product_relation` VALUES (41, 2, 26);
INSERT INTO `cms_subject_product_relation` VALUES (42, 3, 26);
INSERT INTO `cms_subject_product_relation` VALUES (43, 6, 26);
INSERT INTO `cms_subject_product_relation` VALUES (44, 1, 53);
INSERT INTO `cms_subject_product_relation` VALUES (45, 2, 53);
INSERT INTO `cms_subject_product_relation` VALUES (46, 3, 53);
INSERT INTO `cms_subject_product_relation` VALUES (47, 4, 53);
INSERT INTO `cms_subject_product_relation` VALUES (48, 5, 53);
INSERT INTO `cms_subject_product_relation` VALUES (49, 6, 53);
INSERT INTO `cms_subject_product_relation` VALUES (50, 1, 54);
INSERT INTO `cms_subject_product_relation` VALUES (51, 2, 54);
INSERT INTO `cms_subject_product_relation` VALUES (52, 3, 54);
INSERT INTO `cms_subject_product_relation` VALUES (53, 4, 54);
INSERT INTO `cms_subject_product_relation` VALUES (54, 5, 54);
INSERT INTO `cms_subject_product_relation` VALUES (55, 6, 54);
INSERT INTO `cms_subject_product_relation` VALUES (62, 1, 56);
INSERT INTO `cms_subject_product_relation` VALUES (63, 2, 56);
INSERT INTO `cms_subject_product_relation` VALUES (64, 3, 56);
INSERT INTO `cms_subject_product_relation` VALUES (65, 4, 56);
INSERT INTO `cms_subject_product_relation` VALUES (66, 5, 56);
INSERT INTO `cms_subject_product_relation` VALUES (67, 6, 56);
INSERT INTO `cms_subject_product_relation` VALUES (293, 1, 59);
INSERT INTO `cms_subject_product_relation` VALUES (294, 2, 59);
INSERT INTO `cms_subject_product_relation` VALUES (295, 3, 59);
INSERT INTO `cms_subject_product_relation` VALUES (296, 4, 59);
INSERT INTO `cms_subject_product_relation` VALUES (297, 5, 59);
INSERT INTO `cms_subject_product_relation` VALUES (298, 6, 59);
INSERT INTO `cms_subject_product_relation` VALUES (299, 1, 58);
INSERT INTO `cms_subject_product_relation` VALUES (300, 2, 58);
INSERT INTO `cms_subject_product_relation` VALUES (301, 3, 58);
INSERT INTO `cms_subject_product_relation` VALUES (302, 4, 58);
INSERT INTO `cms_subject_product_relation` VALUES (303, 5, 58);
INSERT INTO `cms_subject_product_relation` VALUES (304, 6, 58);
INSERT INTO `cms_subject_product_relation` VALUES (305, 1, 57);
INSERT INTO `cms_subject_product_relation` VALUES (306, 2, 57);
INSERT INTO `cms_subject_product_relation` VALUES (307, 3, 57);
INSERT INTO `cms_subject_product_relation` VALUES (308, 4, 57);
INSERT INTO `cms_subject_product_relation` VALUES (309, 5, 57);
INSERT INTO `cms_subject_product_relation` VALUES (310, 6, 57);
INSERT INTO `cms_subject_product_relation` VALUES (311, 1, 55);
INSERT INTO `cms_subject_product_relation` VALUES (312, 2, 55);
INSERT INTO `cms_subject_product_relation` VALUES (313, 3, 55);
INSERT INTO `cms_subject_product_relation` VALUES (314, 4, 55);
INSERT INTO `cms_subject_product_relation` VALUES (315, 5, 55);
INSERT INTO `cms_subject_product_relation` VALUES (316, 6, 55);

-- ----------------------------
-- Table structure for cms_topic
-- ----------------------------
DROP TABLE IF EXISTS `cms_topic`;
CREATE TABLE `cms_topic`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(0) NULL DEFAULT NULL COMMENT '话题分类id',
  `name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '话题名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `start_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `attend_count` int(0) NULL DEFAULT NULL COMMENT '参与人数',
  `attention_count` int(0) NULL DEFAULT NULL COMMENT '关注人数',
  `read_count` int(0) NULL DEFAULT NULL COMMENT '阅读数',
  `award_name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '奖品名称',
  `attend_type` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '参与方式',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '话题内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '话题表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_topic
-- ----------------------------

-- ----------------------------
-- Table structure for cms_topic_category
-- ----------------------------
DROP TABLE IF EXISTS `cms_topic_category`;
CREATE TABLE `cms_topic_category`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '话题分类id',
  `icon` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '分类图标',
  `subject_count` int(0) NULL DEFAULT NULL COMMENT '专题数量',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  `sort` int(0) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '话题分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_topic_category
-- ----------------------------

-- ----------------------------
-- Table structure for cms_topic_comment
-- ----------------------------
DROP TABLE IF EXISTS `cms_topic_comment`;
CREATE TABLE `cms_topic_comment`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '专题评论id',
  `member_nick_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会员昵称',
  `topic_id` bigint(0) NULL DEFAULT NULL COMMENT '话题id',
  `member_icon` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '会员头像',
  `content` varchar(1000) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `show_status` int(0) NULL DEFAULT NULL COMMENT '显示状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci COMMENT = '专题评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cms_topic_comment
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
