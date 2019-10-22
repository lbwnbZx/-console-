/*
 Navicat Premium Data Transfer

 Source Server         : test
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : localhost:3306
 Source Schema         : test02

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 22/10/2019 16:29:43
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `c_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `c_teacher` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_introduce` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `c_credit` int(255) NULL DEFAULT NULL,
  INDEX `c_id`(`c_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('c001', '计算机导论', '冯诺依曼', '计算机基础课程', 1);
INSERT INTO `course` VALUES ('c002', 'c语言入门', '谭浩强', '面向过程', 2);
INSERT INTO `course` VALUES ('c003', '数据结构', '严蔚敏', '掉头发', 3);
INSERT INTO `course` VALUES ('c004', 'java语言入门', '洛必达', '面向对象', 4);
INSERT INTO `course` VALUES ('c005', '数据库基础', '泰勒', 'SQL语句学习', 5);
INSERT INTO `course` VALUES ('c006', '操作系统', '卢本伟', 'nice马飞', 6);

-- ----------------------------
-- Table structure for g_select_cou
-- ----------------------------
DROP TABLE IF EXISTS `g_select_cou`;
CREATE TABLE `g_select_cou`  (
  `gsc_stu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gsc_cou` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  INDEX `gsc_stu`(`gsc_stu`) USING BTREE,
  INDEX `gsc_cou`(`gsc_cou`) USING BTREE,
  CONSTRAINT `g_select_cou_ibfk_1` FOREIGN KEY (`gsc_stu`) REFERENCES `graduate` (`g_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `g_select_cou_ibfk_2` FOREIGN KEY (`gsc_cou`) REFERENCES `course` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of g_select_cou
-- ----------------------------
INSERT INTO `g_select_cou` VALUES ('g001', 'c001');
INSERT INTO `g_select_cou` VALUES ('g002', 'c001');
INSERT INTO `g_select_cou` VALUES ('g001', 'c003');
INSERT INTO `g_select_cou` VALUES ('g001', 'c002');
INSERT INTO `g_select_cou` VALUES ('g004', 'c001');
INSERT INTO `g_select_cou` VALUES ('g004', 'c002');

-- ----------------------------
-- Table structure for graduate
-- ----------------------------
DROP TABLE IF EXISTS `graduate`;
CREATE TABLE `graduate`  (
  `g_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `g_birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_professional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_mentor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_direction` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `g_credit` int(255) NULL DEFAULT NULL,
  INDEX `g_id`(`g_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of graduate
-- ----------------------------
INSERT INTO `graduate` VALUES ('秋大', 'g001', '男', '2019-10-19', '研三', '母猪产后护理', '武老大', '产后健身', 15);
INSERT INTO `graduate` VALUES ('秋二', 'g002', '女', '2019-10-19', '研一', '母猪产后护理', '武老大', '产后健身', 5);
INSERT INTO `graduate` VALUES ('秋思', 'g004', '女', '2019-10-19', '研二', '大母猪产后护理', '武老二', '产后美白', 0);

-- ----------------------------
-- Table structure for ug_select_cou
-- ----------------------------
DROP TABLE IF EXISTS `ug_select_cou`;
CREATE TABLE `ug_select_cou`  (
  `ugsc_stu` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '选课学生',
  `ugsc_cou` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生选的课程',
  INDEX `ugsc_stu`(`ugsc_stu`) USING BTREE,
  INDEX `ugsc_cou`(`ugsc_cou`) USING BTREE,
  CONSTRAINT `ug_select_cou_ibfk_1` FOREIGN KEY (`ugsc_stu`) REFERENCES `undergraduate` (`ug_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ug_select_cou_ibfk_2` FOREIGN KEY (`ugsc_cou`) REFERENCES `course` (`c_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ug_select_cou
-- ----------------------------
INSERT INTO `ug_select_cou` VALUES ('ug003', 'c003');
INSERT INTO `ug_select_cou` VALUES ('ug005', 'c002');
INSERT INTO `ug_select_cou` VALUES ('ug005', 'c001');
INSERT INTO `ug_select_cou` VALUES ('ug004', 'c002');
INSERT INTO `ug_select_cou` VALUES ('ug004', 'c004');
INSERT INTO `ug_select_cou` VALUES ('ug004', 'c001');

-- ----------------------------
-- Table structure for undergraduate
-- ----------------------------
DROP TABLE IF EXISTS `undergraduate`;
CREATE TABLE `undergraduate`  (
  `ug_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ug_gender` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_birth` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_profressional` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_class` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ug_credit` int(255) NULL DEFAULT NULL,
  INDEX `ug_id`(`ug_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of undergraduate
-- ----------------------------
INSERT INTO `undergraduate` VALUES ('赵大', 'ug001', '男', '2019-10-19', '大一', '母猪产后护理', '二班', '托尼老师', 5);
INSERT INTO `undergraduate` VALUES ('赵二', 'uh002', '男', '2019-10-19', '大四', '母猪产后护理', '二班', '托尼老师', 0);
INSERT INTO `undergraduate` VALUES ('赵三', 'ug003', '女', '2019-10-12', '大一', '母猪产后保健', '四班', '马老C', 15);
INSERT INTO `undergraduate` VALUES ('赵五', 'ug005', '男', '2019-11-19', '大一', '计算机技术', '三班', '小白老师', 3);
INSERT INTO `undergraduate` VALUES ('赵四', 'ug004', '女', '2000-6-3', '大三', '软件工程', '太仓班', '小黑老师', 8);
INSERT INTO `undergraduate` VALUES ('赵六', 'ug006', '女', '1996-10-19', '大一', '国际金融', '二班', '马云', 0);
INSERT INTO `undergraduate` VALUES ('赵崎', 'ug007', '男', '2019-10-19', '大四', '翻译', '二班', '大山老师', 0);

SET FOREIGN_KEY_CHECKS = 1;
