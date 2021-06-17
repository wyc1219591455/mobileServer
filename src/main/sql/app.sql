-- ----------------------------
-- create procedure start
-- ----------------------------



-- ----------------------------
-- create procedure end
-- ----------------------------

-- ----------------------------
-- modify table application start
-- ----------------------------

alter table application add column need_title varchar(8) DEFAULT 'yes'  ;

alter table application add column need_backBtn varchar(8) DEFAULT 'no'  ;

alter table application add column sso_token varchar(16) DEFAULT NULL ;

alter table application add column is_beta varchar(16) DEFAULT 'no' ;

-- ----------------------------
-- modify table application end
-- ----------------------------

-- ----------------------------
-- Table structure for message start
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_type` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '设备类型(ios,android)',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '主题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
	`push_scope` varchar(64)  CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '推送范围(all,part)' ,
  `push_object` text  DEFAULT NULL COMMENT '推送对象',
  `jump_way` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT 'inside' COMMENT '跳转方式(inside:内部;outside:外部)',
	`jump_url` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '跳转链接',
  `jump_params` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '跳转链接请求参数',
  `status` varchar(8) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL,
  `push_time` datetime DEFAULT NULL,
  `creater` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for message end
-- ----------------------------

ALTER TABLE message DROP COLUMN jump_way;
ALTER TABLE message ADD COLUMN need_push varchar(20) not null;
COMMIT;
