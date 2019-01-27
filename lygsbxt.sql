/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : lygsbxt

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 27/01/2019 23:14:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for building
-- ----------------------------
DROP TABLE IF EXISTS `building`;
CREATE TABLE `building`  (
  `buildid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学校设施idUUID',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '设施分类（商业街、宿管。。。）',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `firstpicture` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `allstars` int(32) NOT NULL DEFAULT 0 COMMENT '评论总星级',
  `commentcount` int(32) NOT NULL DEFAULT 0 COMMENT '评论人数',
  `averagestars` float(5, 2) NOT NULL DEFAULT 0.00 COMMENT '平均得分',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  PRIMARY KEY (`buildid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '学校设施表（宿管、食堂等）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of building
-- ----------------------------
INSERT INTO `building` VALUES ('22d40cc2-79be-42dd-8638-a074db9dd535', '2e2456a6-9d4b-411c-ba0c-a6d95c869395', '西苑食堂', '/file/06cb073f-6bef-4392-a65b-e271211ee72e.jpg', 14, 3, 4.67, 0);
INSERT INTO `building` VALUES ('4af875ce-394a-4bc2-95e5-5a68d4c99396', '2e2456a6-9d4b-411c-ba0c-a6d95c869395', '南苑食堂', '/file/6af09091-7e06-4581-ace6-72232a1428a0.jpg', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('abd176ad-3178-4bfb-b91a-fd929a050c3b', '2e2456a6-9d4b-411c-ba0c-a6d95c869395', '东一食堂西门', '/file/26a6de8c-f29d-41b5-b3e0-3e0e8dc37ae5.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '1ff09cdd-8e7e-47f2-b996-c47176a89a62', '图书馆', '/file/a5356cd6-9a79-4123-9813-b6107676b891.jpg', 5, 1, 5.00, 0);
INSERT INTO `building` VALUES ('eb81d4f1-3f61-4866-b70a-e0ca0db4b67d', '1ff09cdd-8e7e-47f2-b996-c47176a89a62', '体育馆', '/file/0d912146-d622-4fa2-ad0d-22131aa78a88.jpg', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('246ca18c-1ef2-4b21-af56-36d617f8d59f', '1ff09cdd-8e7e-47f2-b996-c47176a89a62', '艺术学院', '/file/1a79c49c-d68d-409f-a839-bf77a3e54f9f.jpg', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('a0440f71-bd39-47d3-886b-1f3a772d14bc', 'eb2a26ee-91aa-4499-9d1c-9d0ceacd21e8', '南苑宿舍', '/file/a950df27-0593-4243-9f82-8861bed3bbf3.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('abc4a1b7-0092-4bf6-a8cb-789580468b59', 'eb2a26ee-91aa-4499-9d1c-9d0ceacd21e8', '北苑宿舍', '/file/0b447c6e-fa94-41fb-a237-a2a4bc9aa026.jpg', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', 'eb2a26ee-91aa-4499-9d1c-9d0ceacd21e8', '西苑宿舍', '/file/1ef75289-bc70-40ad-87ac-d8ececb85aa3.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('622690ac-d53c-465a-84ac-b103a8f7def4', '04087c1a-a365-4c71-ac57-ed60ecf70e0d', '南苑商业街', '/file/708dd5c0-b24f-4636-91ae-103162e70841.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('367649cc-8f35-4198-994a-8f1e24aa1191', '04087c1a-a365-4c71-ac57-ed60ecf70e0d', '西苑商业街', '/file/3353f782-1425-47f0-8b83-7a4d37973912.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('7f665586-8b24-4622-abfd-f77e736dc84d', '04087c1a-a365-4c71-ac57-ed60ecf70e0d', '艺术中心', '/file/002e7567-101c-405f-a986-2d969c9b7dd5.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('31afc055-110a-4259-a8a5-10a1fc17cab5', '5d0c40fe-9f35-4fd7-ba68-ee805e4bb1b6', '雅家乐超市', '/file/0e3ffcc4-02eb-46fa-9122-abca7a8a2fdc.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('058f338d-f49b-4721-beb8-434ec84ba270', '5d0c40fe-9f35-4fd7-ba68-ee805e4bb1b6', '金购福超市', '/file/6734714f-57dc-468f-9827-7085ce3add7f.JPG', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('93c2ff65-a451-4733-ab7f-4f10a141ef27', '1ff09cdd-8e7e-47f2-b996-c47176a89a62', '西门保安亭', '/file/6fca7166-1f45-462c-88bc-087c85f8f8a6.jpg', 0, 0, 0.00, 0);
INSERT INTO `building` VALUES ('e594678c-807d-4b72-92e0-1af939806266', 'd33a5b8e-0bb0-43fc-b2c2-45ffc5b12e6d', '维修服务人员', '/file/0a86dd34-f946-4147-a649-bc43260c371e.jpg', 0, 0, 0.00, 1);
INSERT INTO `building` VALUES ('e6c28ab8-7abb-4726-88bf-ee3682bfc86a', '6a6759bc-210c-4021-a751-0aab404df963', '测1234', '/file/73db89ff-22eb-402a-80f7-9f0bf86be8b8.png', 0, 0, 0.00, 1);
INSERT INTO `building` VALUES ('631c6c99-8e20-4688-b476-5633abad0fc0', '96059592-69e2-474a-b896-53641954ca8a', '测试', '/file/65d8432b-103c-4a90-a11f-173cdbf82b5f.jpg', 0, 0, 0.00, 1);

-- ----------------------------
-- Table structure for buildingtype
-- ----------------------------
DROP TABLE IF EXISTS `buildingtype`;
CREATE TABLE `buildingtype`  (
  `typeid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分类id',
  `typename` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(11) NULL DEFAULT 0 COMMENT '排序号，越小越靠前',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  PRIMARY KEY (`typeid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of buildingtype
-- ----------------------------
INSERT INTO `buildingtype` VALUES ('06f037f4-5da8-4a8b-b8ed-e440e12ba41b', '食堂', 1, 1);
INSERT INTO `buildingtype` VALUES ('1ff09cdd-8e7e-47f2-b996-c47176a89a62', '物业管理', 2, 0);
INSERT INTO `buildingtype` VALUES ('2e2456a6-9d4b-411c-ba0c-a6d95c869395', '食堂', 1, 0);
INSERT INTO `buildingtype` VALUES ('eb2a26ee-91aa-4499-9d1c-9d0ceacd21e8', '宿管', 3, 0);
INSERT INTO `buildingtype` VALUES ('04087c1a-a365-4c71-ac57-ed60ecf70e0d', '商业街', 4, 0);
INSERT INTO `buildingtype` VALUES ('5d0c40fe-9f35-4fd7-ba68-ee805e4bb1b6', '店铺', 5, 0);
INSERT INTO `buildingtype` VALUES ('d33a5b8e-0bb0-43fc-b2c2-45ffc5b12e6d', '维修', 6, 1);
INSERT INTO `buildingtype` VALUES ('96059592-69e2-474a-b896-53641954ca8a', '测试㎝', 6, 1);
INSERT INTO `buildingtype` VALUES ('6a6759bc-210c-4021-a751-0aab404df963', '测试cm', 6, 1);
INSERT INTO `buildingtype` VALUES ('3e052fbf-f632-4478-bafe-5fa9c7612dd4', '测试㎝', 7, 1);
INSERT INTO `buildingtype` VALUES ('75fad6e9-ecc4-4e36-b285-1c459ce7c479', '测试㎝', 7, 1);

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`  (
  `commentid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论idUUID',
  `relationid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联id（学校设施、维修人员）',
  `orderid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '工单id（申报工单）',
  `wxuserid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论人id',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评价内容',
  `stars` int(11) NULL DEFAULT 5 COMMENT '评价星级',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  PRIMARY KEY (`commentid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '评价表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('790a6c27-1383-4a9f-85e2-3d610e6ebf50', '3cbf896b-bb15-4ba3-9fdf-6ab731fcf053', NULL, 'oMu6l5BJmRW2DmSIoI3Gbv8G-vok', 'V型刚回家', 5, '2019-01-04 16:07:53', 0);
INSERT INTO `comments` VALUES ('aa215f69-4352-46aa-b856-9746382258e1', '22d40cc2-79be-42dd-8638-a074db9dd535', NULL, 'oMu6l5BJmRW2DmSIoI3Gbv8G-vok', '姑爷节', 5, '2019-01-04 16:08:14', 0);
INSERT INTO `comments` VALUES ('8d51521a-0ec3-484e-aa68-b6df86d406b8', '22d40cc2-79be-42dd-8638-a074db9dd535', NULL, 'oMu6l5DRlirLHTzDCOZfLYZEcST8', '看看', 4, '2019-01-05 23:26:55', 0);
INSERT INTO `comments` VALUES ('da2ad520-a743-4e99-b274-8bf40952eb96', '3cbf896b-bb15-4ba3-9fdf-6ab731fcf053', NULL, 'oMu6l5DRlirLHTzDCOZfLYZEcST8', '啦啦啦', 4, '2019-01-05 23:27:28', 0);
INSERT INTO `comments` VALUES ('40c39ae5-4714-4c82-9518-46bc25d4482a', '22d40cc2-79be-42dd-8638-a074db9dd535', NULL, 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '味道不错，就是用盆吃饭有点那个...', 5, '2019-01-05 23:30:56', 0);
INSERT INTO `comments` VALUES ('e5768322-5a4e-4c8b-a495-54c4852a200f', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190105235930505', 'oMu6l5HuDvwqjYAPeL15C84vEYEQ', '非常满意', 5, '2019-01-06 00:04:42', 0);
INSERT INTO `comments` VALUES ('8b19f01b-5bc3-4cd2-97db-49eae29d89d6', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', NULL, 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '这个必须五分，不解释', 5, '2019-01-06 00:08:53', 0);
INSERT INTO `comments` VALUES ('53837917-e2bb-422d-8acc-941c86884b51', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190105235511660', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '很低调，但服务到位！满意', 4, '2019-01-06 00:09:20', 0);
INSERT INTO `comments` VALUES ('1a240642-7916-4e8b-98aa-d227149085d0', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190103232742928', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '这么久才给出解决方案，给你三分', 3, '2019-01-06 00:09:49', 0);
INSERT INTO `comments` VALUES ('24267b6f-e48a-4e51-b4d2-f016dcd07af2', '3cbf896b-bb15-4ba3-9fdf-6ab731fcf053', NULL, 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '测试的人员，很棒', 4, '2019-01-06 00:13:57', 0);
INSERT INTO `comments` VALUES ('9b93ad66-f862-4082-9457-499a98449ac4', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190106133235491', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '系统测试订单坐评价测试', 5, '2019-01-06 13:40:10', 0);
INSERT INTO `comments` VALUES ('a19038d6-631d-4254-bf42-cd0a9ccd1df6', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190106083031571', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '测试一下，就知道了', 5, '2019-01-06 13:40:32', 0);
INSERT INTO `comments` VALUES ('8a5ab3cd-e72d-460a-a5cf-1eb7d5e54a7b', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190107152040367', 'oMu6l5J6A2HKNJTYRbQKOW85a1OE', '速度?', 5, '2019-01-07 15:30:03', 0);
INSERT INTO `comments` VALUES ('81d2d124-7310-4802-b9c5-66efe9c0838b', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', NULL, 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '图书馆，好', 5, '2019-01-09 13:32:02', 0);
INSERT INTO `comments` VALUES ('b9ef130d-8e80-419e-84b8-3b81c019396f', '33c68dc5-bfd5-42dc-b9fd-f7386233c03c', '20190109164045139', 'oMu6l5PLmy8pi-SPxMnU72yZiGvY', '江师傅很认真，椅子全部弄好，很好，谢谢师傅的帮忙，', 5, '2019-01-09 16:38:28', 0);
INSERT INTO `comments` VALUES ('4fd87fd2-767c-411f-990d-2d63bceb6a49', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190112125923080', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '谢谢您', 5, '2019-01-12 20:08:27', 0);
INSERT INTO `comments` VALUES ('4befbbc9-d498-409c-a067-c674d5e9cb14', 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '20190112200806573', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '好，很好，非常好！', 5, '2019-01-12 20:33:20', 0);

-- ----------------------------
-- Table structure for maintainorder
-- ----------------------------
DROP TABLE IF EXISTS `maintainorder`;
CREATE TABLE `maintainorder`  (
  `orderid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '工单id年月日时分秒random(1)',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '标题',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修地点',
  `content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修内容',
  `wxuserid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '提交人id',
  `createtime` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '维修工单状态（未派单、维修中、待评价、已完成）',
  `officertime` datetime(0) NULL DEFAULT NULL COMMENT '派单时间',
  `maintaintime` datetime(0) NULL DEFAULT NULL COMMENT '维修时间',
  `commenttime` datetime(0) NULL DEFAULT NULL COMMENT '评价时间',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  `maintainid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `officerid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `charge` float(10, 2) NOT NULL DEFAULT 0.00,
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修工单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintainorder
-- ----------------------------
INSERT INTO `maintainorder` VALUES ('20190103232742928', '学校发的苹果包装有问题', '学校东一食堂', '苹果包装坏坏了，让我怎么送人呢？', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-03 23:27:43', '已完成', '2019-01-03 23:30:21', '2019-01-06 00:05:24', '2019-01-06 00:09:49', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190104160639709', '好好的火锅', '胡多喝水', '不会的好的吧', 'oMu6l5BJmRW2DmSIoI3Gbv8G-vok', '2019-01-04 16:06:40', '待评价', '2019-01-05 23:53:57', '2019-01-06 00:05:20', NULL, 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190105232820044', '啦啦啦', '太冷了', '啦啦啦啦', 'oMu6l5DRlirLHTzDCOZfLYZEcST8', '2019-01-05 23:28:20', '维修中', '2019-01-05 23:30:36', NULL, NULL, 0, '43769f46-e005-4f5b-8458-d9645350d276', 'bb4aee3e-d681-4bd7-ae18-da31c6d30250', 0.00);
INSERT INTO `maintainorder` VALUES ('20190105235511660', '评价服务于维修申报系统测试', 'Bug测试模块', '这个是系统测试模块', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-05 23:55:12', '已完成', '2019-01-05 23:59:32', '2019-01-06 00:05:16', '2019-01-06 00:09:20', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190105235930505', '1111', '111', '1111', 'oMu6l5HuDvwqjYAPeL15C84vEYEQ', '2019-01-05 23:59:31', '已完成', '2019-01-05 23:59:46', '2019-01-06 00:03:21', '2019-01-06 00:04:42', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190106083031571', '测试一下看看', '测试一下', '1234567', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-06 08:30:32', '已完成', '2019-01-06 13:35:23', '2019-01-06 13:36:20', '2019-01-06 13:40:32', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190106133235491', '测试问题反馈', '测试部', '系统测试模块反馈', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-06 13:32:35', '已完成', '2019-01-06 13:35:16', '2019-01-06 13:36:16', '2019-01-06 13:40:10', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190107152040367', '漏水', '主教室', '漏水', 'oMu6l5J6A2HKNJTYRbQKOW85a1OE', '2019-01-07 15:20:40', '已完成', '2019-01-07 15:22:11', '2019-01-07 15:24:28', '2019-01-07 15:30:03', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190109164045139', '后勤处椅子坏了', '后勤处201', '后勤处椅子坏了', 'oMu6l5PLmy8pi-SPxMnU72yZiGvY', '2019-01-09 16:40:45', '已完成', '2019-01-09 16:41:20', '2019-01-09 16:41:58', '2019-01-09 16:38:28', 0, '33c68dc5-bfd5-42dc-b9fd-f7386233c03c', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190110102323381', '男生宿舍楼梯口扶手坏了', '男生宿舍2号楼西楼梯口一楼', '楼梯口扶手坏了，太危险了，快来修理下', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-10 10:23:23', '待评价', '2019-01-10 10:25:07', '2019-01-12 20:03:03', NULL, 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 0.00);
INSERT INTO `maintainorder` VALUES ('20190112125923080', '网站测试数据', '后勤管理处网站', '不得不说', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-12 12:59:23', '已完成', '2019-01-12 13:00:17', '2019-01-12 19:56:18', '2019-01-12 20:08:27', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 20.56);
INSERT INTO `maintainorder` VALUES ('20190112200806573', '微信授权登录问题', '微信授权', '微信人员微信上不了怎么办', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-12 20:08:07', '已完成', '2019-01-12 20:12:32', '2019-01-12 20:13:38', '2019-01-12 20:33:20', 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 58.56);
INSERT INTO `maintainorder` VALUES ('20190112203418507', '再发起一个订单', '家里', '墙壁坏了', 'oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', '2019-01-12 20:34:19', '待评价', '2019-01-12 20:36:06', '2019-01-12 20:36:54', NULL, 0, 'fefea7c3-d8d1-4110-93a5-8787bfb2de34', '68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', 9999.99);
INSERT INTO `maintainorder` VALUES ('20190112203644964', '厨房太冷', '厨房', '厨房没有空调', 'oMu6l5J6A2HKNJTYRbQKOW85a1OE', '2019-01-12 20:36:45', '未派单', NULL, NULL, NULL, 0, NULL, NULL, 0.00);

-- ----------------------------
-- Table structure for maintainuser
-- ----------------------------
DROP TABLE IF EXISTS `maintainuser`;
CREATE TABLE `maintainuser`  (
  `maintainid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '维修员idUUID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名(手机号作为登录名)',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `photourl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片地址',
  `allstars` int(32) NOT NULL DEFAULT 0 COMMENT '评论总星级',
  `commentcount` int(32) NOT NULL DEFAULT 0 COMMENT '评论人数',
  `averagestars` float(5, 2) NOT NULL DEFAULT 0.00 COMMENT '平均得分',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`maintainid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '维修人员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of maintainuser
-- ----------------------------
INSERT INTO `maintainuser` VALUES ('3cbf896b-bb15-4ba3-9fdf-6ab731fcf053', '16485465312', 'e10adc3949ba59abbe56e057f20f883e', '张恒', '/file/96d8d609-f92b-4f3c-bd52-3f303d322543.jpg', 13, 3, 4.33, 0, NULL);
INSERT INTO `maintainuser` VALUES ('43769f46-e005-4f5b-8458-d9645350d276', '13333333333', 'e10adc3949ba59abbe56e057f20f883e', '维修员', '/file/823c3f9b-a2e3-49d9-a17d-102cb8fd6cde.jpg', 0, 0, 0.00, 0, NULL);
INSERT INTO `maintainuser` VALUES ('00838719-864f-4542-9ce4-97b9aa444440', '14532110213', 'e10adc3949ba59abbe56e057f20f883e', '王建国', '/file/6daec145-cce5-4536-af0a-5b2861ae5acf.jpg', 0, 0, 0.00, 0, NULL);
INSERT INTO `maintainuser` VALUES ('14f69393-caf4-4fce-a51c-d7ad7d1277f4', '18064554124', 'e10adc3949ba59abbe56e057f20f883e', '李铁柱', '/file/e4655ce4-9e66-418f-8c9f-58148f098928.jpg', 0, 0, 0.00, 0, NULL);
INSERT INTO `maintainuser` VALUES ('0f25f7e6-0aa5-4c76-a099-843ddb01f673', '17305442154', 'e10adc3949ba59abbe56e057f20f883e', '陈力', '/file/f7a855c1-7c57-45ac-851b-fa766be92608.jpg', 0, 0, 0.00, 1, NULL);
INSERT INTO `maintainuser` VALUES ('fefea7c3-d8d1-4110-93a5-8787bfb2de34', '18888133014', 'e10adc3949ba59abbe56e057f20f883e', '王磊', '/file/f17e6e4d-74f6-4e18-b087-f350317160dd.png', 42, 9, 4.67, 0, '系统测试');
INSERT INTO `maintainuser` VALUES ('33c68dc5-bfd5-42dc-b9fd-f7386233c03c', '15861296511', 'e10adc3949ba59abbe56e057f20f883e', '江老板', '/file/c4ab5d94-759b-4b95-ba5a-4e134c8d3333.jpg', 5, 1, 5.00, 0, '测试人员');

-- ----------------------------
-- Table structure for manageuser
-- ----------------------------
DROP TABLE IF EXISTS `manageuser`;
CREATE TABLE `manageuser`  (
  `manageid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  `role` int(11) NOT NULL DEFAULT 1,
  PRIMARY KEY (`manageid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of manageuser
-- ----------------------------
INSERT INTO `manageuser` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 0, 3);

-- ----------------------------
-- Table structure for officeuser
-- ----------------------------
DROP TABLE IF EXISTS `officeuser`;
CREATE TABLE `officeuser`  (
  `officerid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '派单员idUUID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `realname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `photourl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '照片地址',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`officerid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '派单员' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of officeuser
-- ----------------------------
INSERT INTO `officeuser` VALUES ('68867c73-2b4f-4c82-bf9c-562e8af4d2cf', '14325412041', 'e10adc3949ba59abbe56e057f20f883e', '李纳', '/file/36bdff15-1136-45ad-97e6-6192fd621365.jpg', 0, '');
INSERT INTO `officeuser` VALUES ('bb4aee3e-d681-4bd7-ae18-da31c6d30250', '13333333333', 'e10adc3949ba59abbe56e057f20f883e', '派单员', '/file/ab5a3b42-783a-43ff-ab67-a85c797778d5.jpg', 0, NULL);
INSERT INTO `officeuser` VALUES ('41e87071-10d1-4687-8027-7f01432c7398', '18456451244', 'e10adc3949ba59abbe56e057f20f883e', '李春花', '/file/93cf1772-0d01-4c6c-84eb-dd104d85798a.jpg', 0, NULL);
INSERT INTO `officeuser` VALUES ('68a3e0a8-c7db-4eee-8b4e-a57d9b1cc478', '18888133014', 'e10adc3949ba59abbe56e057f20f883e', '王磊', '/file/cde34c52-185f-456a-9938-2b599245ce26.jpg', 0, '系统测试员');
INSERT INTO `officeuser` VALUES ('47bbfa6e-0293-43cc-953c-2db67f5a6313', '15151482464', 'e10adc3949ba59abbe56e057f20f883e', '好名字', '/file/d2407df0-ab56-4296-9521-a995445eb397.jpg', 0, '');
INSERT INTO `officeuser` VALUES ('bb3fefd7-99c1-4c1b-9c2b-7b635423621e', '15861296511', 'e10adc3949ba59abbe56e057f20f883e', '江老师', '/file/ab7cab14-2ebb-47d8-98de-3d7df57f1c44.jpg', 0, '测试人员');

-- ----------------------------
-- Table structure for pictures
-- ----------------------------
DROP TABLE IF EXISTS `pictures`;
CREATE TABLE `pictures`  (
  `pictureid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图片idUUID',
  `relationid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联id',
  `pictureurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片地址',
  `isdelete` int(11) NULL DEFAULT 0 COMMENT '0未删除1已删除',
  PRIMARY KEY (`pictureid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '图片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pictures
-- ----------------------------
INSERT INTO `pictures` VALUES ('d05f1dc9-134b-421e-aa5c-52453bc8a172', '22d40cc2-79be-42dd-8638-a074db9dd535', '/file/64cd1955-194d-4cb5-b5dc-aa7c4668b7e7.jpg', 1);
INSERT INTO `pictures` VALUES ('445f6b13-29d7-4253-b27f-6e7bb0134e90', '22d40cc2-79be-42dd-8638-a074db9dd535', '/file/64cd1955-194d-4cb5-b5dc-aa7c4668b7e7.jpg', 1);
INSERT INTO `pictures` VALUES ('c793efc4-79a3-448b-8e9d-5b6cbfee94a1', '22d40cc2-79be-42dd-8638-a074db9dd535', '/file/64cd1955-194d-4cb5-b5dc-aa7c4668b7e7.jpg', 1);
INSERT INTO `pictures` VALUES ('630c29ef-6945-4238-931f-399d988e85b9', '4af875ce-394a-4bc2-95e5-5a68d4c99396', '/file/cf3c736c-52d5-4b82-8ab6-72c12aabba93.jpg', 1);
INSERT INTO `pictures` VALUES ('2f43be7b-7bef-4545-9fc4-a41fad160f3f', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/684b63ac-4df3-452c-acee-adeb066c67b4.jpg', 1);
INSERT INTO `pictures` VALUES ('5b5721ab-1982-4700-8171-daede0b978a4', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/6243742a-4787-4af1-995f-58c041ef19ac.jpg', 1);
INSERT INTO `pictures` VALUES ('b98bb017-0ed3-4e78-9b53-8565bdf68660', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '/file/3d91f08d-d024-402b-b98a-799c0280e170.jpg', 1);
INSERT INTO `pictures` VALUES ('093fbcb6-2ea8-4fcc-a0b8-66cf5f067992', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '/file/c51da82b-68cf-4473-bf16-c57105b43989.jpg', 1);
INSERT INTO `pictures` VALUES ('5a05c058-c768-49c5-8af5-6c30d9e6ad0c', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '/file/79e21e05-db39-4cb6-a30a-b45da47a067e.jpg', 1);
INSERT INTO `pictures` VALUES ('c1e86b4f-ea1c-484c-a726-8a889089bd05', 'eb81d4f1-3f61-4866-b70a-e0ca0db4b67d', '/file/2b88d47f-7458-4178-9067-3382bcd4ee77.jpg', 1);
INSERT INTO `pictures` VALUES ('35147e61-37b0-44e3-a0c3-04ef60f8274e', 'eb81d4f1-3f61-4866-b70a-e0ca0db4b67d', '/file/f6f2a76c-0d93-4406-b793-2364df51adbd.jpg', 1);
INSERT INTO `pictures` VALUES ('2211dd8a-239b-4dd7-bfbe-0f0b73a48fb8', 'eb81d4f1-3f61-4866-b70a-e0ca0db4b67d', '/file/dc4da6ed-08f4-4894-9bdb-6f3517b657ab.jpg', 1);
INSERT INTO `pictures` VALUES ('319ff2bd-4e70-48a3-8bc6-da89125ab3b7', '246ca18c-1ef2-4b21-af56-36d617f8d59f', '/file/27801196-49d9-44db-9e07-f77e47baa8fa.jpg', 1);
INSERT INTO `pictures` VALUES ('635910aa-b66a-4182-8bf8-b3b2c3babc9d', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/ac60a522-81bb-48c6-bf4a-10e83f600186.jpg', 1);
INSERT INTO `pictures` VALUES ('3501c50d-89cc-449f-8737-e979adb9f77e', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/3b4a8676-bd41-42af-853b-bd3d70593b09.jpg', 1);
INSERT INTO `pictures` VALUES ('b3db90c2-e505-4b57-9e6c-737d19eb61f3', 'abc4a1b7-0092-4bf6-a8cb-789580468b59', '/file/7ca0c5e0-5b6f-43fd-a257-6631de32c7ae.jpg', 1);
INSERT INTO `pictures` VALUES ('1e5756f5-afdc-430c-b013-945ce174f7cc', '2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', '/file/0a781381-866d-41ed-9fe1-6fd6d994f3dc.jpg', 1);
INSERT INTO `pictures` VALUES ('7ec0a575-64b2-4368-a7da-f0aa041de16c', '2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', '/file/5951237f-be5b-4f6d-b75a-fe9ee772559d.jpg', 1);
INSERT INTO `pictures` VALUES ('3e13d28e-1e90-4c21-b421-d9405a1949c6', '622690ac-d53c-465a-84ac-b103a8f7def4', '/file/d66b8981-0a8e-4211-9dae-da265007a41f.jpg', 1);
INSERT INTO `pictures` VALUES ('a314ff30-0463-486f-90d4-4c7b7888a8b9', '367649cc-8f35-4198-994a-8f1e24aa1191', '/file/fcd2eff9-b036-42c7-a1d3-049a8130605d.jpg', 1);
INSERT INTO `pictures` VALUES ('a698b9d1-b545-4ee5-b082-8cfaed4eec23', '7f665586-8b24-4622-abfd-f77e736dc84d', '/file/41a1f506-f1da-4d7e-8a94-6bced3a37a1a.jpg', 1);
INSERT INTO `pictures` VALUES ('3d01140d-8261-4baa-b9a9-2241387b18b1', '31afc055-110a-4259-a8a5-10a1fc17cab5', '/file/bd766679-6e50-4c5e-af4a-fb36b12ec5d0.jpg', 1);
INSERT INTO `pictures` VALUES ('1244f601-6fc7-4e60-8431-2be09a82bbb6', '31afc055-110a-4259-a8a5-10a1fc17cab5', '/file/1003d4e3-5470-434c-805a-f49d3150827e.jpg', 1);
INSERT INTO `pictures` VALUES ('05f3500f-6f0e-41d1-a912-d00c8a9913da', '31afc055-110a-4259-a8a5-10a1fc17cab5', '/file/89fd1ac0-923b-4187-aaf9-2610704b0f92.jpg', 1);
INSERT INTO `pictures` VALUES ('03398057-faa1-42af-bbed-0a3d9e962bbb', '31afc055-110a-4259-a8a5-10a1fc17cab5', '/file/67dd7007-3976-489b-a467-7d33a369f0d1.jpg', 1);
INSERT INTO `pictures` VALUES ('2ee6a3ee-301f-4321-86e9-5f2379876cd4', '058f338d-f49b-4721-beb8-434ec84ba270', '/file/cdb6af0f-d841-4131-ad1f-16c907aaf317.jpg', 1);
INSERT INTO `pictures` VALUES ('11b9e065-36ce-46af-a925-b70e59ed16b3', '058f338d-f49b-4721-beb8-434ec84ba270', '/file/df1a394a-c6d6-4420-b117-245bcc23f7bf.jpg', 1);
INSERT INTO `pictures` VALUES ('1e9fe1b8-bac6-4ec9-8301-4dd6c8048471', '22d40cc2-79be-42dd-8638-a074db9dd535', '/file/06cb073f-6bef-4392-a65b-e271211ee72e.jpg', 0);
INSERT INTO `pictures` VALUES ('563ebe99-299c-4f50-9b80-115972e56763', '4af875ce-394a-4bc2-95e5-5a68d4c99396', '/file/6af09091-7e06-4581-ace6-72232a1428a0.jpg', 0);
INSERT INTO `pictures` VALUES ('79269df0-a0a4-4071-a718-e3151d741824', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/f15ca1f2-5c38-4055-903d-b3a26a5ac7ee.jpg', 1);
INSERT INTO `pictures` VALUES ('92e0cc1b-f339-43b0-b29d-9c28757c1367', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/f15ca1f2-5c38-4055-903d-b3a26a5ac7ee.jpg', 1);
INSERT INTO `pictures` VALUES ('02e5311b-4dc4-4212-89a0-48f25c8ba370', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/37af9cde-5fb6-4b15-b2f9-c43df2a0bed1.jpg', 1);
INSERT INTO `pictures` VALUES ('1e19e95c-1fa6-4665-9b12-a20b959b40e8', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '/file/a5356cd6-9a79-4123-9813-b6107676b891.jpg', 0);
INSERT INTO `pictures` VALUES ('1bae47aa-0249-4ed9-820d-f794413264ac', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '/file/0204e1ac-81fb-494b-bc68-dfd895336d68.jpg', 0);
INSERT INTO `pictures` VALUES ('644899fa-188b-48bf-9a34-10765de5f57c', 'c0bb801f-84ce-4f31-8c8c-6ab22a90da3d', '/file/5ef8b70a-c630-4575-8e54-71db46b0479c.jpg', 0);
INSERT INTO `pictures` VALUES ('bf653645-446a-42f2-9e55-78868946611d', '93c2ff65-a451-4733-ab7f-4f10a141ef27', '/file/6fca7166-1f45-462c-88bc-087c85f8f8a6.jpg', 1);
INSERT INTO `pictures` VALUES ('6893a2dd-a85f-488c-8c96-24abf14f58d6', 'eb81d4f1-3f61-4866-b70a-e0ca0db4b67d', '/file/0d912146-d622-4fa2-ad0d-22131aa78a88.jpg', 0);
INSERT INTO `pictures` VALUES ('d2d813bc-36e3-422a-8214-6f4b034a594a', '246ca18c-1ef2-4b21-af56-36d617f8d59f', '/file/1a79c49c-d68d-409f-a839-bf77a3e54f9f.jpg', 0);
INSERT INTO `pictures` VALUES ('b01348b8-842e-4b81-9a0b-826bb7ee5506', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/caadcfb2-99c9-4f73-bd87-6faf8ab9b134.jpg', 1);
INSERT INTO `pictures` VALUES ('0765ae37-004a-4322-878d-881f21c6b188', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/28021264-c3b3-4c32-b972-cc1f1cc58759.jpg', 1);
INSERT INTO `pictures` VALUES ('f104f401-91b6-450c-b6f9-f5a54b5708a6', 'abc4a1b7-0092-4bf6-a8cb-789580468b59', '/file/0b447c6e-fa94-41fb-a237-a2a4bc9aa026.jpg', 0);
INSERT INTO `pictures` VALUES ('c161e683-7c85-4390-9d3d-298e476a50f8', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/082652f1-ae3c-4900-bf92-cabe8005dc3f.JPG', 1);
INSERT INTO `pictures` VALUES ('bee37474-2adb-4d8b-9d3b-d6f87bfb02a5', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/d1b6b61b-139c-414c-acad-b19ab597c6f3.JPG', 1);
INSERT INTO `pictures` VALUES ('9e69fae1-be76-4f93-8ef6-32523f111480', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/f15ca1f2-5c38-4055-903d-b3a26a5ac7ee.jpg', 1);
INSERT INTO `pictures` VALUES ('645c8244-2869-4b69-9fb3-fbe877fb095e', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/f15ca1f2-5c38-4055-903d-b3a26a5ac7ee.jpg', 1);
INSERT INTO `pictures` VALUES ('0e351749-3271-4c11-b2b1-8b404bd4623e', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/6c60ec4c-24c5-4ae1-a542-4d5fc512bd44.JPG', 1);
INSERT INTO `pictures` VALUES ('cba93058-c635-40da-aee5-942d9ad8d0ce', '31afc055-110a-4259-a8a5-10a1fc17cab5', '/file/c6020009-c690-49b6-ab7a-afa4e13055aa.JPG', 1);
INSERT INTO `pictures` VALUES ('c5eddf9e-dd42-4e38-a086-7df087e245bb', '058f338d-f49b-4721-beb8-434ec84ba270', '/file/093e5c5c-24cd-40d0-8262-81ec98bb1508.JPG', 1);
INSERT INTO `pictures` VALUES ('270a0d72-e6f9-4aed-8c6d-7d841be554b3', '367649cc-8f35-4198-994a-8f1e24aa1191', '/file/8f014a37-a39d-4c08-845a-9e0ba1ff8221.JPG', 1);
INSERT INTO `pictures` VALUES ('a09fea93-544c-4320-b8c3-a48b4dbe7fa0', '622690ac-d53c-465a-84ac-b103a8f7def4', '/file/ced92f1e-2fc9-43a4-83e8-158cde3e1455.JPG', 1);
INSERT INTO `pictures` VALUES ('79c24021-f837-46e4-b736-3c6c6bf8f09d', '2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', '/file/64b867f8-aceb-4ca4-93aa-9af273bfd096.JPG', 1);
INSERT INTO `pictures` VALUES ('ea612329-428b-4ae9-bfe6-8b9e8b59699d', '2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', '/file/25fe987a-2bb6-4830-834d-6e7a920c65e1.JPG', 1);
INSERT INTO `pictures` VALUES ('3b7f5557-42f5-4a9a-8c6a-8e4094f73e19', '7f665586-8b24-4622-abfd-f77e736dc84d', '/file/ee9c9712-700c-4c98-8b97-ef56d6e755b0.JPG', 1);
INSERT INTO `pictures` VALUES ('488de5df-0424-40a3-960d-d393cb62a6e2', 'e594678c-807d-4b72-92e0-1af939806266', '/file/0a86dd34-f946-4147-a649-bc43260c371e.jpg', 0);
INSERT INTO `pictures` VALUES ('6e0ba679-17b4-4bf5-a595-e350f9ba67b4', '20181231195702443', '/file/427faa35-a319-471a-b833-af27e6ecee7d.png', 0);
INSERT INTO `pictures` VALUES ('7d866d93-dfa6-47d0-8f50-26a44c6acbf1', '20181231195702443', '/file/8d79fd78-2c78-44a2-a1ce-893615503189.png', 0);
INSERT INTO `pictures` VALUES ('dae1d286-da67-4438-a610-6be11c542588', '20181231195702443', '/file/92e19189-3a7e-4b96-ac72-356c89602004.png', 0);
INSERT INTO `pictures` VALUES ('0ea06019-8197-4dce-a69e-a97cb9c56643', '20181231195702443', '/file/0da29135-faba-4c09-afe3-11751b28496e.png', 0);
INSERT INTO `pictures` VALUES ('3de278df-6979-4d02-9b05-b08a2e631898', '20181231193252101', '/file/35d7fc6d-44c5-4177-9844-3801b8051428.png', 0);
INSERT INTO `pictures` VALUES ('02c9cd7f-c9e3-44e5-9625-a670cf34762d', '20181231193252101', '/file/c71790dc-581a-4eea-9023-e67ae1814706.png', 0);
INSERT INTO `pictures` VALUES ('92837d2f-f484-4b1f-a16d-420e177fcaa1', '20181231193252101', '/file/9bcb7e3d-2e08-456c-bd9e-b551b0da320c.png', 0);
INSERT INTO `pictures` VALUES ('b97a8c80-6e6a-45d9-8f30-52ff4be55645', '20181231193252101', '/file/3492837a-5b78-4c91-a7b3-c16ed9aeac17.png', 0);
INSERT INTO `pictures` VALUES ('f4e17c6f-0e69-4e2a-a7b4-3117ee2e1fbc', '20181231220407330', '/file/e4ec1f1d-6035-40b7-97d2-e19efa1e8fa5.jpg', 0);
INSERT INTO `pictures` VALUES ('6d2fe721-ea02-4e4e-bf73-92b316ce1a20', '20181231220407330', '/file/5e5203b7-7b4c-407e-ae42-da0d26b2d39d.jpg', 0);
INSERT INTO `pictures` VALUES ('e2eb027f-831b-4476-ac71-c93ad86862b0', '20181231220859905', '/file/98aace9b-72ed-4a72-89df-a217ca8b7254.jpg', 1);
INSERT INTO `pictures` VALUES ('b718701f-585b-4d65-9c18-5f590585544b', '20181231221107605', '/file/3a1c2fd5-ae52-4892-af18-635d2689b032.jpg', 0);
INSERT INTO `pictures` VALUES ('9ca57fc8-8b6d-4f30-ac6f-3ff3fb50b67e', '20181231220859905', '/file/98aace9b-72ed-4a72-89df-a217ca8b7254.jpg', 0);
INSERT INTO `pictures` VALUES ('6b840534-994b-419e-963d-3cdf1aa02c9a', '20181231222436929', '/file/a2708ed6-cb92-4ff9-8f15-f074ac67bfee.jpg', 0);
INSERT INTO `pictures` VALUES ('93779906-2ec2-4dfe-bc93-1377f3f4e6c2', '20181231224414712', '/file/0d5d66ad-48e0-45ac-b590-26c23389a7a4.jpg', 0);
INSERT INTO `pictures` VALUES ('2041e086-9b18-4521-949f-a71347bffa91', '20190101003219741', '/file/748d683c-0bb8-45b0-a928-b59bc4def0fe.png', 1);
INSERT INTO `pictures` VALUES ('186ac7f4-a8c1-4d9b-8639-4a584c9cd8f4', '20190101003219741', '/file/748d683c-0bb8-45b0-a928-b59bc4def0fe.png', 0);
INSERT INTO `pictures` VALUES ('888451be-61bb-41cf-a0ff-c025568dca07', '20190101093427156', '/file/d7103780-a79a-4018-bf60-f677131460d5.jpg', 1);
INSERT INTO `pictures` VALUES ('c3961ff7-c9b4-4454-b8a0-74abfcf7fad7', '20190101093427156', '/file/d7103780-a79a-4018-bf60-f677131460d5.jpg', 0);
INSERT INTO `pictures` VALUES ('0fd35601-c904-48c3-ab2a-ef8d5b362005', '20190101113548963', '/file/e445615a-8b90-433f-8039-8e423b78b225.jpg', 0);
INSERT INTO `pictures` VALUES ('bf294f9d-0300-4b79-adc5-a47d86f8c8e1', '20190101114059458', '/file/3d6687c7-294f-48b6-bcba-3c843060f895.jpg', 0);
INSERT INTO `pictures` VALUES ('9d038a03-6fc2-41f0-a538-a74504e6bca4', '20190101121653337', '/file/f00b1ca3-c538-4f41-ba02-011771e0a5b3.jpg', 0);
INSERT INTO `pictures` VALUES ('4de39e52-2c53-47e5-a425-f5c32900a3c1', '20190101122400324', '/file/18921e63-e7b6-4b63-90c6-a92fb8a4c96b.jpg', 0);
INSERT INTO `pictures` VALUES ('bf87a935-ac9a-4cd2-a712-4f520347676f', '20190101173808631', '/file/d94ef4c7-c6a4-4446-83b2-eccb1a300bbd.jpg', 0);
INSERT INTO `pictures` VALUES ('658c1f7e-e548-48c9-b1ea-7680af9e3cd3', '20190101220650718', '/file/f8a441d3-c4c7-465e-a96c-b42a7b16508e.png', 0);
INSERT INTO `pictures` VALUES ('eb9ca124-9d2e-4f54-827a-f41ed3c849fd', '31afc055-110a-4259-a8a5-10a1fc17cab5', '/file/0e3ffcc4-02eb-46fa-9122-abca7a8a2fdc.JPG', 0);
INSERT INTO `pictures` VALUES ('0efcdbf1-9db6-4aed-8f45-821f582ecd41', '20190102000038478', '/file/3950c106-3bd5-4b30-b652-2466b3e9724f.png', 0);
INSERT INTO `pictures` VALUES ('532b0a68-b9b9-490c-b663-8c26fc36835f', '20190102000038478', '/file/c452c846-161d-4a5a-adc6-c3723e22ba62.png', 0);
INSERT INTO `pictures` VALUES ('22b9c55f-3c07-47b0-8627-9aa75f155f79', '20190102091114044', '/file/2cc66c35-4c80-4170-bec5-19bd3c80d9fd.jpg', 0);
INSERT INTO `pictures` VALUES ('69cf01a2-cfe3-4497-a85e-db93e4cfc096', '20190102091438174', '/file/538ddd09-05a4-4527-a4ae-dcea7de5189d.jpg', 0);
INSERT INTO `pictures` VALUES ('e1868073-f728-4ccc-88af-a6ca709380ff', '20190102092123908', '/file/6cf5119f-4caa-43ac-8861-b2d5274a96eb.jpg', 0);
INSERT INTO `pictures` VALUES ('9952e06b-395e-489a-8704-14aac991e94e', '058f338d-f49b-4721-beb8-434ec84ba270', '/file/6734714f-57dc-468f-9827-7085ce3add7f.JPG', 0);
INSERT INTO `pictures` VALUES ('1fe6774d-1e4d-4900-bf07-e2ea83d88ecb', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/a950df27-0593-4243-9f82-8861bed3bbf3.JPG', 0);
INSERT INTO `pictures` VALUES ('c2ace823-ab0f-406c-ac3a-22def92a3454', 'a0440f71-bd39-47d3-886b-1f3a772d14bc', '/file/2ef11bc0-12fe-4d63-8ea6-52c519dfe87c.JPG', 0);
INSERT INTO `pictures` VALUES ('f0b4547e-db1f-438f-aa53-997a5cbcb4a1', '2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', '/file/1ef75289-bc70-40ad-87ac-d8ececb85aa3.JPG', 0);
INSERT INTO `pictures` VALUES ('2e7fa8c0-1d7e-47e4-a7e4-a1d4e407f1cb', '2a67b0be-92b3-4cbe-98af-f8176b0fd9b4', '/file/f43c11ba-eeb0-4202-bf99-86dfbd538276.JPG', 0);
INSERT INTO `pictures` VALUES ('e156f9d4-4cb7-4f77-9789-77252017a0fc', '622690ac-d53c-465a-84ac-b103a8f7def4', '/file/708dd5c0-b24f-4636-91ae-103162e70841.JPG', 0);
INSERT INTO `pictures` VALUES ('09dd810b-e2b6-4254-8531-ce72f395f879', '367649cc-8f35-4198-994a-8f1e24aa1191', '/file/3353f782-1425-47f0-8b83-7a4d37973912.JPG', 0);
INSERT INTO `pictures` VALUES ('9f8160a2-7810-4268-8c19-4b49a877cbe9', '7f665586-8b24-4622-abfd-f77e736dc84d', '/file/002e7567-101c-405f-a986-2d969c9b7dd5.JPG', 0);
INSERT INTO `pictures` VALUES ('01842077-87f1-4d7b-8db7-ba457c14ec9f', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/26a6de8c-f29d-41b5-b3e0-3e0e8dc37ae5.JPG', 1);
INSERT INTO `pictures` VALUES ('4ad3770a-622a-4e2b-83f2-7096f574f9df', '20190102111631626', '/file/172662e1-e923-4235-a263-bd515f2e4965.jpg', 0);
INSERT INTO `pictures` VALUES ('226bb724-af62-49aa-94a5-92a00f1e6da8', '20190102144241452', '/file/c2b02583-7ea6-468c-a8ca-05cc153949cb.jpg', 0);
INSERT INTO `pictures` VALUES ('2d323ee8-cf6f-4fce-881b-8ba0f7fe35f0', '93c2ff65-a451-4733-ab7f-4f10a141ef27', '/file/6fca7166-1f45-462c-88bc-087c85f8f8a6.jpg', 0);
INSERT INTO `pictures` VALUES ('0dc9cec7-fce1-4cd5-96f8-4e56279ff832', '20190102210000265', '/file/1d2587cc-bedf-4831-bdfa-5df9f0be4928.jpg', 0);
INSERT INTO `pictures` VALUES ('2981e085-bb0b-4a61-a133-f3bc8bc04ddc', '20190102212918965', '/file/68a7c422-18b2-4bb3-b17e-4772a9db2d6d.jpg', 0);
INSERT INTO `pictures` VALUES ('9e87b2c7-ab26-4cc3-b8a9-857e1a93ee53', '20190102212918965', '/file/ead78ce8-530d-4480-892c-c11b16f88d9c.jpg', 0);
INSERT INTO `pictures` VALUES ('06284b05-49ea-42e0-97a3-f67b9ca95e8e', '20190102212918965', '/file/15331506-91dc-4255-8f5b-1e80089d368a.jpg', 0);
INSERT INTO `pictures` VALUES ('e441903f-6dc8-45c1-bec3-a4344745d034', '20190102214402213', '/file/2c8e2d73-fc7b-4501-bdfc-37f07c844afc.png', 0);
INSERT INTO `pictures` VALUES ('7266363a-deec-4c10-84df-f21eca45e21f', '20190102214830549', '/file/6ffa84aa-0e53-4370-8b2b-53e1090fc506.jpg', 0);
INSERT INTO `pictures` VALUES ('577d0e0a-c1e6-47cc-b374-564ce4bcde39', '20190102215008511', '/file/580e5ef4-f322-45ca-829a-e6b23f4f8bdd.jpg', 0);
INSERT INTO `pictures` VALUES ('38e1e694-4ba9-48f6-9c9c-d80b0935cebc', '20190102215008511', '/file/61ea1ea6-ba09-4f90-bbbe-b27b3dbb7a8f.jpg', 0);
INSERT INTO `pictures` VALUES ('05d96d05-4491-4bf6-90cf-259a34adc872', '20190102215008511', '/file/9d391a27-ebe5-4b48-95b9-0efae0062122.jpg', 0);
INSERT INTO `pictures` VALUES ('351901ec-bcf9-4aec-b2f7-9e596d4e9354', '20190102215008511', '/file/95ddc389-e67c-4556-920c-a35ff26e7dc0.jpg', 0);
INSERT INTO `pictures` VALUES ('1b00420b-074c-4a35-a707-f768f6fc5511', '20190102215008511', '/file/8bd2164b-3f80-4e90-adf2-e9f7a90c4100.jpg', 0);
INSERT INTO `pictures` VALUES ('96a684c4-3539-44da-985b-56c9916ffab2', '20190102215039093', '/file/056ab7da-1a20-44cd-b1a4-0c709f83ca5d.jpg', 0);
INSERT INTO `pictures` VALUES ('c219734c-a79c-4525-b2ea-d15983b8f3f7', '20190102215747430', '/file/5f25c1ab-3858-416f-9131-dacd9c2f107c.jpg', 0);
INSERT INTO `pictures` VALUES ('71f94af6-1af1-490d-a812-84c05f58f75d', '20190102215933851', '/file/e9117a9e-5691-41e6-9291-c2b301bfd0ec.jpg', 0);
INSERT INTO `pictures` VALUES ('2ebabfc6-5cee-4000-8274-a59614d1ffc2', '20190102220517191', '/file/dfba332b-bb52-4b0d-9ab3-60b9a108fc28.png', 0);
INSERT INTO `pictures` VALUES ('3d6c5d31-9509-42cf-92f8-139f923c8400', '20190102220626837', '/file/5b8201ce-7d7a-41f1-b9ed-0ea9d9190c3f.jpg', 0);
INSERT INTO `pictures` VALUES ('33a5f0aa-afe5-43e9-92cf-3231f964a784', '20190102223911205', '/file/043ee368-e770-41bd-9e9c-5ddc31d1b8cf.png', 0);
INSERT INTO `pictures` VALUES ('368734ed-6767-4692-91ad-f1d2f5c37053', '20190102225250431', '/file/b535c959-b016-423e-bfd4-b00fb8023b28.jpg', 1);
INSERT INTO `pictures` VALUES ('26a9d50b-8ca3-41c2-80ef-bd0a28264c52', '20190102230016761', '/file/df1a3902-a7fd-43ae-8a4d-f9cd956c8639.jpg', 0);
INSERT INTO `pictures` VALUES ('a17fc863-d80f-47e9-93d8-c46157bfc734', '20190102225250431', '/file/b535c959-b016-423e-bfd4-b00fb8023b28.jpg', 0);
INSERT INTO `pictures` VALUES ('761d341c-f4d0-474c-919e-0e4144cdc6a5', '20190102230607288', '/file/9cd74aea-6e14-444e-8fe2-4ce4d1b885e5.png', 1);
INSERT INTO `pictures` VALUES ('059ad9ec-2fce-4e7b-84a1-77e77388c808', '20190102231530429', '/file/20e75a75-ce46-438b-a5f0-a5ddb8c0533b.jpg', 0);
INSERT INTO `pictures` VALUES ('f70c190d-7bff-41e6-ad6f-85871455ef29', '20190102231530429', '/file/94bfec4d-0575-41ae-ba7d-1788522a6256.png', 0);
INSERT INTO `pictures` VALUES ('7e316be3-b32f-44f6-bcde-6d1b33e2571b', '20190102230607288', '/file/9cd74aea-6e14-444e-8fe2-4ce4d1b885e5.png', 0);
INSERT INTO `pictures` VALUES ('ac559516-e738-47db-aabd-57dbf182b208', '20190102231741370', '/file/d39bf29c-b376-48e2-a147-9e1fe30b3126.jpg', 0);
INSERT INTO `pictures` VALUES ('d445fb1d-2e82-446f-a384-bcf3ea15964e', '20190103074024519', '/file/1f180145-6680-47b6-a65d-4b8bb62551ad.jpg', 0);
INSERT INTO `pictures` VALUES ('5b449272-0141-4ba1-b37b-8684fc541264', '20190103090904049', '/file/f7808ed5-2b0c-4224-9072-27b1072cf057.jpg', 1);
INSERT INTO `pictures` VALUES ('59c91f15-b849-4e2d-8278-0853be1e347e', 'abd176ad-3178-4bfb-b91a-fd929a050c3b', '/file/26a6de8c-f29d-41b5-b3e0-3e0e8dc37ae5.JPG', 0);
INSERT INTO `pictures` VALUES ('8f9b987b-298a-40a4-b526-bbfbb13ea942', '20190103123228892', '/file/7a3c3b0f-bc4f-4ea7-84f9-8f6c7c72e6dc.jpg', 0);
INSERT INTO `pictures` VALUES ('10bc4824-95db-489d-9fa4-67d8b1d05934', '20190103140851509', '/file/3938e6b9-e6a9-4acb-a737-7fce7b353b0d.jpg', 0);
INSERT INTO `pictures` VALUES ('99454b6e-ad69-4eaa-986e-fdc2b5968838', '20190103090904049', '/file/f7808ed5-2b0c-4224-9072-27b1072cf057.jpg', 0);
INSERT INTO `pictures` VALUES ('a4653d7d-626a-4e23-a671-28e2b97c5326', '20190103224311436', '/file/df8b5f63-8e3d-4c81-9a3e-8fb884b9e17b.png', 0);
INSERT INTO `pictures` VALUES ('cb9e3c77-9350-4359-81de-c14e9229276d', '20190103224501154', '/file/a3a2f0ad-f23c-4ead-9c02-e514dece7ebb.png', 0);
INSERT INTO `pictures` VALUES ('87c0cb0b-feea-461b-846d-4a2f840e8f61', '20190103224959094', '/file/ab3d73e8-3eae-4f4c-8769-465061aadc32.png', 0);
INSERT INTO `pictures` VALUES ('240bf28e-034f-49f8-8b6c-e8ab5f9a3edd', '20190103225507135', '/file/03a6c9d6-5673-4dab-9b3c-19af51dc0b36.png', 0);
INSERT INTO `pictures` VALUES ('528b7447-aca2-4aba-9526-47c7fd6b95cd', '20190103225735581', '/file/a55cbd84-2424-4e56-ab3b-8d551066f5db.jpg', 0);
INSERT INTO `pictures` VALUES ('775abacd-761f-407c-ab9b-a0afd4427f44', '20190103230311213', '/file/86fd82b9-53ad-4be5-9f0f-eeef039429bd.png', 0);
INSERT INTO `pictures` VALUES ('c17cf34e-fdef-4476-a610-bf0ddf96f268', '20190103231010292', '/file/c71b614c-5a9f-4c49-944c-9d984b419db2.jpg', 0);
INSERT INTO `pictures` VALUES ('b51beb5c-98e8-4741-93a6-2d6ebb11cbef', '20190103231010292', '/file/615cb9c3-407b-4e43-8bb6-bce234d5586f.jpg', 0);
INSERT INTO `pictures` VALUES ('034fd731-1c5a-4c68-b5fc-bc5a31b3df78', '20190103231010292', '/file/7faefab2-f393-4bfe-a1f6-1db14a2709d7.jpg', 0);
INSERT INTO `pictures` VALUES ('5c9c761d-e631-4c00-8256-03750d866df3', '20190103232742928', '/file/7424ded1-5153-410b-8d53-4447cccbc6ba.jpg', 0);
INSERT INTO `pictures` VALUES ('304c8d5c-84fe-41f1-9a2f-763f4eaf44dd', '631c6c99-8e20-4688-b476-5633abad0fc0', '/file/65d8432b-103c-4a90-a11f-173cdbf82b5f.jpg', 0);
INSERT INTO `pictures` VALUES ('cd6f1bc4-62c8-4256-b25d-49723ce59a1f', 'e6c28ab8-7abb-4726-88bf-ee3682bfc86a', '/file/73db89ff-22eb-402a-80f7-9f0bf86be8b8.png', 0);
INSERT INTO `pictures` VALUES ('004f6555-7234-4e92-949f-85b8cc1ab766', '20190104160639709', '/file/af5068b8-9e66-40eb-a9d7-53e3a7b9506c.jpg', 0);
INSERT INTO `pictures` VALUES ('6f9fd512-a1f8-4ca9-92f9-61d2f99549e5', '20190105232820044', '/file/9fd37379-cc98-4eb4-ad7f-a827d9d7f2a8.jpg', 0);
INSERT INTO `pictures` VALUES ('94761ee2-703b-4aff-bd5f-92d59de60199', '20190105235511660', '/file/b905494e-2082-4292-b7a8-8858e17852fa.jpg', 1);
INSERT INTO `pictures` VALUES ('2c3a9342-d27c-4407-b75b-66d75443e974', '20190105235511660', '/file/b905494e-2082-4292-b7a8-8858e17852fa.jpg', 0);
INSERT INTO `pictures` VALUES ('7591ba4c-01ec-4d65-be5d-558c65083b47', '20190105235930505', '/file/af3fbdcb-5fc9-43d2-bad2-caee4aa3afb9.jpg', 0);
INSERT INTO `pictures` VALUES ('c0f00df1-f541-4d98-bcf2-817e15657a06', '20190106083031571', '/file/3122d289-6cbd-414b-ae41-b00e342307f3.png', 0);
INSERT INTO `pictures` VALUES ('263ab42a-5d46-4fbf-9fae-9a7383acae0a', '20190106133235491', '/file/6e0f2f1e-d5a9-4a34-8539-debc223fe9f2.jpg', 0);
INSERT INTO `pictures` VALUES ('2f3c18fc-8ec0-4c85-af11-43ec5d668fd0', '20190107152040367', '/file/0c20e538-bd3f-409c-8c37-a0a89de2b360.jpg', 0);
INSERT INTO `pictures` VALUES ('d9671b78-d023-4295-8933-f08443f7bfe6', '20190109164045139', '/file/2464d6d8-9162-46b4-83c3-16315037f09b.jpg', 0);
INSERT INTO `pictures` VALUES ('c5fe044b-61dd-4b36-b883-d51dc22f3446', '20190110102323381', '/file/85def796-e170-44a5-b4d8-61607eda6ebf.jpg', 0);
INSERT INTO `pictures` VALUES ('ad112806-1f49-4cf5-af06-0fc21b5ba640', '20190112125923080', '/file/de445d80-c28a-4001-bd7b-ec168a0b8398.jpg', 0);
INSERT INTO `pictures` VALUES ('dbe68a0a-57e9-4b5f-97b6-e8abc73498fd', '20190112200806573', '/file/28e6d7fd-5ed6-4920-90b8-a192f11e5a06.png', 0);
INSERT INTO `pictures` VALUES ('fadec239-5e22-4baa-b61e-cac976536b9e', '20190112200806573', '/file/e3f55be3-773c-4e12-b0f6-a9552fae8ed4.jpg', 0);
INSERT INTO `pictures` VALUES ('6599622c-748a-417b-9f6b-8dc3300594c1', '20190112203418507', '/file/a7b0d8f5-a2c3-4b50-8ab5-9c68cf4aafb7.jpg', 0);
INSERT INTO `pictures` VALUES ('27a589a5-3268-465e-bca1-0bec35df8d02', '20190112203644964', '/file/9ffe7a35-360a-4a56-97bd-9237987cfe6e.jpg', 0);

-- ----------------------------
-- Table structure for wxuser
-- ----------------------------
DROP TABLE IF EXISTS `wxuser`;
CREATE TABLE `wxuser`  (
  `openid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '小程序用户标识OPENID',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `headurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像地址',
  PRIMARY KEY (`openid`) USING BTREE
) ENGINE = MyISAM CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wxuser
-- ----------------------------
INSERT INTO `wxuser` VALUES ('oMu6l5L_MruoevjS1t0FHHOSjcBY', '秦小哲', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJd2cyZKN5UlOriakdllX4VrudhSuT6SkwjzEXYGavP6qXlF9ZtwF1IO9ibhKvVomiaqwg5SVNnjBDPw/132');
INSERT INTO `wxuser` VALUES ('oMu6l5MQFYPgAXBibHng8dwuT3d8', '好名字', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKFS4QloEN7q1GIvib2PrHHSAmlicP7icibxT7lXIvtrDXHGqqQsLzeUwxSryItibw8wcSf2iaYueruLJUw/132');
INSERT INTO `wxuser` VALUES ('oMu6l5DQ2ZfvOSSu8t1l31IA_wGY', 'ShanLonly-.PSD', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ7xuia6jQE4gF1x07fE8hFNq4kQxfKIjtOmTesXWdMBCAmeLfiaaMYTC77dbEfvP6uukBI5ad1jsKQ/132');
INSERT INTO `wxuser` VALUES ('oMu6l5Hw2QzxP0kwRo41GhnWezto', '徐桂清爱神！', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epRK5v3IkvSfgibQEDnau1MeUOBxQYYOPoI4dOjW0ETibhjtIF74ANP0DWo48kx0NMV6V8OZKnvA1UA/132');
INSERT INTO `wxuser` VALUES ('oMu6l5PLmy8pi-SPxMnU72yZiGvY', 'jimmy-江', 'https://wx.qlogo.cn/mmopen/vi_32/ZxfdlQiaa4E9RvX89ndBzmZhsXV4r4Owpb3ibkMGAWgKliaGsQTBIPu6zrjibiahxHMbU1C7EXy90szq0aLqhTh43qw/132');
INSERT INTO `wxuser` VALUES ('undefined', '王佑诚', 'https://wx.qlogo.cn/mmhead/2K78s7d8KV30engiadM0MFNibRRcLiaVVYZgPmhlNRbYQE/132');
INSERT INTO `wxuser` VALUES ('oMu6l5PSPqyEoMXdJYQLeI5UjqmE', '陈佑南', 'https://wx.qlogo.cn/mmhead/nibib01HNmKTRm9GgtHYymIlGDC7XIiaQH2Y450pKVy6D0/132');
INSERT INTO `wxuser` VALUES ('oMu6l5PvuGaKfwqbsWN3RfqTOs9E', '林政辛', 'https://wx.qlogo.cn/mmhead/7g6mYrOYveJWLdFZ1RyvkBCeo7MEs0Yermajf1lcGOQ/132');
INSERT INTO `wxuser` VALUES ('oMu6l5FjiHO5NHtEC3WXAyeTVN3A', '王佑诚', 'https://wx.qlogo.cn/mmhead/2K78s7d8KV30engiadM0MFNibRRcLiaVVYZgPmhlNRbYQE/132');
INSERT INTO `wxuser` VALUES ('oMu6l5J6A2HKNJTYRbQKOW85a1OE', '品牌搬运工?小乔（请设星标?', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83erBenDlsd1a5Eicvga4HDd3Eb8SePZ9r0qNFz7N7MsvyoGN0wNPmbskXQGengplsH2mpEeJdE0rbvA/132');
INSERT INTO `wxuser` VALUES ('oMu6l5Ps2LuMIfeSjEqbSfKUAAuc', '道法自然', 'https://wx.qlogo.cn/mmopen/vi_32/2FIyVS2z8xRwwYEIJNJbHtZQXRrfNdzkSkBConDfkia4nY2fArH8zLQoVTR2QBXBqSU37WjWT8RZgQuN9j4HqTw/132');
INSERT INTO `wxuser` VALUES ('oMu6l5BJmRW2DmSIoI3Gbv8G-vok', '陈斌', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJzO301Zz3ic4SWmJw9A3BOTu4NevWWBiabDwVF0Ex9YBtooRUmCTMPMHUmZgN7PU0jaS7iaUdCsuibPg/132');
INSERT INTO `wxuser` VALUES ('oMu6l5D8svacVUrZ0RCzBKIngkb4', '钟杰', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eolVzdrhxyY34IeFgIgBnGibwO6OFpCcMmuQOjZeYxjM3oWXM8x2Vve27F5SRicziaWQZpsoLVX5kyxQ/132');
INSERT INTO `wxuser` VALUES ('oMu6l5HMujK3ttqZjbdtxP6KM9xk', '黄志佩', 'https://wx.qlogo.cn/mmhead/MtrIUm5QL1B0CcLyDn5yPC76Wgbgl4jJ5ORV3nYvpfA/132');
INSERT INTO `wxuser` VALUES ('oMu6l5HuDvwqjYAPeL15C84vEYEQ', '鲸鱼不是鱼?', 'https://wx.qlogo.cn/mmopen/vi_32/PMxC3hj0bIyVv2XfzPPicZyppulcpWoqUnsiaicoCO3zHibbockSPRjJyfW666DV3eglQezz69g4yKYibVEhZOTf62A/132');
INSERT INTO `wxuser` VALUES ('oMu6l5DRlirLHTzDCOZfLYZEcST8', '颜吉房', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLcT62xfK1U6uUqsoIDvp75Hu4bZaLmPmMgf04T69sq4fRlWNJezBao7VBbiamA2ZRiaI8s0tgWxvsA/132');
INSERT INTO `wxuser` VALUES ('oMu6l5DobS56W9fa2ZNuvXVgHCEo', '小山儿', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKjjYxXvRa7NDU4p5NCn6p93XibslOpGbnFAdF1dPv3K0rIAPWtqd8uRsWugNfjiaXlCfIbgp9DZyxQ/132');
INSERT INTO `wxuser` VALUES ('oMu6l5BpXQyCaPhefm6aGkSEjpmc', '℃               小丑皇?', 'https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83epiaGndmVyhJDRnNjibDfBs9GnNQAjPPHA0h0JPXOq09QbtdsFMCxKGCMtm8eLktP9z1gK9gjl8ezhw/132');
INSERT INTO `wxuser` VALUES ('oMu6l5Owrjl_myrB0QqqejGmahWc', 'emmm...', 'https://wx.qlogo.cn/mmopen/vi_32/Za4Pewc9Ek7TK3YIOMNTrOwKZsa2GXODFfzEOsbqWRRFzfic0sU9lhM1CObeyt5KOMeVpw32KAz0dmYqj8SXvCA/132');
INSERT INTO `wxuser` VALUES ('oMu6l5P1hAkjW3dAoLbFIKXzBXpI', 'V', 'https://wx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKDUmlesL4mC4WcD68ichRYphOpcic4BjZje1AZVdwicWm4v9hC33J6p660GfWGDjpQ2JWKAeibStO5Cg/132');

SET FOREIGN_KEY_CHECKS = 1;
