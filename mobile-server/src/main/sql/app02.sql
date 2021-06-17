

-- ----------------------------
-- Table structure for account start
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empno` varchar(32) DEFAULT NULL COMMENT '工号',
  `username` varchar(64)  DEFAULT NULL COMMENT '用户名称',
  `longnum` varchar(32) DEFAULT NULL COMMENT '长号',
  `shortnum` varchar(32)  DEFAULT NULL COMMENT '短号',
  `sex` varchar(8) DEFAULT NULL COMMENT '0 ：男, 1：女',
  `company` varchar(32) DEFAULT NULL COMMENT '公司名称',
  `dept` varchar(64)  DEFAULT NULL COMMENT '部门',
  `job` varchar(32) DEFAULT NULL COMMENT '岗位',
  `manager` varchar(32) DEFAULT NULL COMMENT '上级主管',
  `assitant` varchar(32) DEFAULT NULL COMMENT '部门助理' ,
 `employtime` varchar(32) DEFAULT NULL COMMENT '入职时间',
  `address` varchar(20) DEFAULT NULL COMMENT '家庭地址',
  `emergencycontact` varchar(20) DEFAULT NULL COMMENT '紧急联系人',
  `emergencynum` varchar(20) DEFAULT NULL COMMENT '紧急联系人号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
-- ----------------------------
-- Table structure for account end
-- ----------------------------


CREATE TABLE `thirdparty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `empno` varchar(32) DEFAULT NULL COMMENT '工号',
  `nick` varchar(64)  DEFAULT NULL COMMENT '用户名称',
  `openid` varchar(32) DEFAULT NULL COMMENT '唯一标识',
  `type` varchar(16) DEFAULT NULL COMMENT '绑定类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;


