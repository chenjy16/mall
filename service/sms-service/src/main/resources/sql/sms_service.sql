/*
Navicat MySQL Data Transfer

Source Server         : 192.168.4.195
Source Server Version : 50629
Source Host           : 192.168.4.195:3306
Source Database       : sms_service

Target Server Type    : MYSQL
Target Server Version : 50629
File Encoding         : 65001

Date: 2017-01-13 14:23:57
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for msg_message_channel
-- ----------------------------
DROP TABLE IF EXISTS `msg_message_channel`;
CREATE TABLE `msg_message_channel` (
  `id` varchar(36) NOT NULL,
  `channel_key` varchar(64) NOT NULL COMMENT '渠道唯一键值',
  `channel_name` varchar(64) DEFAULT NULL COMMENT '渠道名称',
  `requst_url` varchar(255) DEFAULT NULL COMMENT '请求短信服务商的地址',
  `user_name` varchar(64) DEFAULT NULL COMMENT '访问短信渠道的用户名',
  `pass_word` varchar(64) DEFAULT NULL COMMENT '访问短信渠道的密码',
  `effective_time` varchar(32) DEFAULT NULL COMMENT '短信验证码时长',
  `is_enabled` char(2) DEFAULT NULL COMMENT '是否启用(1启用，0禁用)',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `creater` varchar(64) DEFAULT NULL COMMENT '创建人',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `sort_no` int(10) NOT NULL COMMENT '排序号'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_message_channel
-- ----------------------------
INSERT INTO `msg_message_channel` VALUES ('1', 'message_channel_ali_key', '阿里大于', 'http://gw.api.taobao.com/router/rest', '23438487', '17042e86f2ad304b016d4ed1578f26e7', '600000', '1', '阿里大于短信渠道', 'system', '2017-01-11 15:40:39', '1');
INSERT INTO `msg_message_channel` VALUES ('2', 'message_channel_mandao_key', '漫道短信', 'http://sdk.entinfo.cn:8060/webservice.asmx', 'SDK-WSS-010-09798', 'a-9[60-[', '600000', '1', '漫道短信渠道', 'system', '2017-01-11 15:40:39', '2');

-- ----------------------------
-- Table structure for msg_send_sms_history
-- ----------------------------
DROP TABLE IF EXISTS `msg_send_sms_history`;
CREATE TABLE `msg_send_sms_history` (
  `id` varchar(36) NOT NULL,
  `client_id` varchar(36) DEFAULT NULL,
  `channel_id` varchar(36) DEFAULT NULL,
  `template_key` varchar(64) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `request_params` varchar(1024) DEFAULT NULL,
  `result_msg` varchar(1024) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `creater` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_send_sms_history
-- ----------------------------

-- ----------------------------
-- Table structure for msg_template_info
-- ----------------------------
DROP TABLE IF EXISTS `msg_template_info`;
CREATE TABLE `msg_template_info` (
  `id` varchar(36) NOT NULL,
  `channel_id` varchar(36) NOT NULL,
  `template_key` varchar(64) DEFAULT NULL,
  `external_template_no` varchar(64) DEFAULT NULL,
  `template_name` varchar(64) DEFAULT NULL,
  `template_content` varchar(500) DEFAULT NULL,
  `group_key` varchar(32) DEFAULT NULL,
  `remark` varchar(100) DEFAULT NULL,
  `creater` varchar(64) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `effective_time` varchar(32) DEFAULT NULL COMMENT '短信验证码时长'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of msg_template_info
-- ----------------------------
INSERT INTO `msg_template_info` VALUES ('1', '1', 'O2O_1001', 'SMS_42685129', '订单支付成功商家短信', '【美兑壹购物】您的客户订单{0}付款成功，付款金额{1}元，进入商家后台可查看订单详情，感谢您的支持！', 'O2O', '{0}:订单号\r\n{1}:订单金额', 'system', '2017-01-11 15:38:22', '1mn');
INSERT INTO `msg_template_info` VALUES ('10', '1', 'MEM_1003', '', '从凯富同步美兑积分到美兑', '恭喜!您凯富帐号中的美兑购物积分已同步至您的美兑帐号，帐号：{0}，密码：｛1｝。下载登录美兑壹购物APP可在商城中使用美兑购物积分，感谢您的支持！【美兑壹购物】', 'MEMBER_SMS', '{0}:登录帐号\r\n{1}:登录密码', 'system', '2017-01-13 10:27:34', '1mn');
INSERT INTO `msg_template_info` VALUES ('11', '1', 'MEM_1004', '', '推荐会员赠送积分', '【美兑壹购物】亲爱的{0}，您推荐会员所赠送的积分已经到账，请登录商城查询：www.meiduimall.com;', 'MEMBER_SMS', '{0}:分享人的手机号码', 'system', '2017-01-13 10:27:34', '1mn');
INSERT INTO `msg_template_info` VALUES ('12', '1', 'MEM_1005', '', '扫二维码注册成功', '恭喜您注册成美兑会员，获赠100美兑积分，积分可在美兑壹购物商城消费使用，您可以用手机号登录App，密码为{0}，美兑App下载地址：http://t.cn/RI9q3wg【美兑壹购物】', 'MEMBER_SMS', '{0}:登录密码', 'system', '2017-01-13 10:27:34', '1mn');
INSERT INTO `msg_template_info` VALUES ('13', '1', '1GW_1001', '', '订单发货提醒', '【美兑壹购物】订单发货提醒，{0}您的订单商家已发货。订单编号:{1}', 'MEMBER_SMS', '{0}:登录帐号\r\n{1}:订单号', 'system', '2017-01-13 10:27:34', '1mn');
INSERT INTO `msg_template_info` VALUES ('14', '1', '1GW_1002', '', '新订单提醒', '【美兑壹购物】新订单提醒，{0}您有订单需要处理，订单编号：{1}', 'MEMBER_SMS', '{0}:登录帐号\r\n{1}:订单号', 'system', '2017-01-13 10:27:34', '1mn');
INSERT INTO `msg_template_info` VALUES ('2', '1', 'O2O_1002', 'SMS_43005015', '短信验证码', '【美兑壹购物】您的验证码为 {VerificationCode}，10分钟内有效，请尽快完成验证。（如非本人操作，请忽略本短信）', 'O2O', '{VerificationCode}:验证码', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('3', '1', 'O2O_1003', '', '个代账户开通', '【美兑壹购物】您好！恭喜您成功开通个代账户，登陆网址：daili.meiduimall.com ，帐号：{0}，密码：{1} ，请您登录后台确认信息并修改初始密码，感谢您的支持！', 'O2O', '{0}:登录帐号\r\n{1}:登录密码', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('4', '1', 'O2O_1004', '', '提现申请', '【美兑壹购物】您于{0}发起的{1}元提现申请已提交，平台运营团队正在处理中，登录后台可查看最新进度，感谢您的支持！', 'O2O', '{0}:提现时间，格式为“XX年XX月XX日 XX:XX:XX”\r\n{1}:提现金额', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('5', '1', 'O2O_1005', '', '区代账户开通', '【美兑壹购物】您好！恭喜您已成功开通区代账户，登陆网址：daili.meiduimall.com，帐号：{0} ，密码：{1}，请您登录后台确认信息并修改初始密码，感谢您的支持！', 'O2O', '{0}:登录帐号\r\n{1}:登录密码', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('6', '1', 'O2O_1006', '', '商家账户开通', '【美兑壹购物】{0}:恭喜您的店铺账户开通成功！商家中心网址:shangjia.meiduimall.com, 帐号:{1}，密码：{2}，请您登录后台确认信息并修改初始密码，祝您生意兴隆！', 'O2O', '{0}:店铺名称\r\n{1}:登录帐号\r\n{2}:登录密码', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('7', '1', 'O2O_1007', '', '线下消费提示', '【美兑壹购物】您本次在【{0}】消费现金{1}元，可获赠{2}积分，1积分等同1元人民币使用，请在商城购物使用！', 'O2O', '{0}:商家店名\r\n{1}:支付金额\r\n{2}:积分数', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('8', '1', 'MEM_1001', '', '注册成功', '【美兑壹购物】亲爱的{0}，您已成功注册并获赠100积分，推荐好友注册也可获赠100积分！客服热线：4006226555。', 'MEMBER_SMS', '{0}:用户注册的手机号', 'system', '2017-01-11 15:39:14', '1mn');
INSERT INTO `msg_template_info` VALUES ('9', '1', 'MEM_1002', null, '发送验证码', '【美兑壹购物】您的验证码是{VerificationCode}，10分钟内有效，请尽快完成验证。（如非本人操作，请忽略本短信）', 'MEMBER_SMS', '{VerificationCode}:验证码', 'system', '2017-01-13 10:27:34', '1mn');
INSERT INTO `msg_template_info` VALUES ('15', '1', '1GW_1004', 'SMS_63360445', '商城赠送会员优惠券通知', '【美兑】您本次消费{0}元，已获赠{1}，可在美兑商城购物抵扣现金，戳http://t.cn/R664xco', '1GW', '{0}:消费金额\r\n{1}:获赠内容', 'system', '2017-04-26 10:27:34', '1mn');
