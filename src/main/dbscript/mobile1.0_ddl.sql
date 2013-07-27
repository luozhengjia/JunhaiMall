#新增品牌图片表里手机客户端图片字段
ALTER TABLE tbl_commodity_brand_pic ADD mobile_pic varchar(100);

#新增商品图片里手机客户端图片字段
ALTER TABLE tbl_commodity_prodpic ADD mobile_pic varchar(100);

#新增品牌表里手机客户端图片字段
ALTER TABLE tbl_commodity_brand ADD mobile_pic VARCHAR(100);

#################################################################
CREATE TABLE `yitian_b2c_db`.`tbl_moblie_activated_record`( 
   `id` VARCHAR(32) NOT NULL COMMENT 'ID', 
   `appkey` VARCHAR(100) COMMENT '软件身份key', 
   `os_name` VARCHAR(50) COMMENT '操作系统名称', 
   `os_version` VARCHAR(50) COMMENT '操作系统版本', 
   `app_version` VARCHAR(50) COMMENT 'APP版本', 
   `source_id` VARCHAR(50) COMMENT '推广ID', 
   `tx_version` VARCHAR(10) COMMENT '通讯协议版本', 
   `unique_code` VARCHAR(50) COMMENT '激活后得到的设备唯一性标识', 
   `create_time` DATETIME COMMENT '设备激活时间', 
   PRIMARY KEY (`id`)
 )ENGINE=INNODB DEFAULT CHARSET=utf8;

create table `yitian_b2c_db`.`tbl_moblie_app_version` ( 
   `id` varchar(32) NOT NULL COMMENT 'ID', 
   `version_no` varchar(20) NOT NULL COMMENT '当前最新APP版本号', 
   `force_version_no` varchar(20) COMMENT '此版本之前的版本需要强制更新', 
   `down_url` varchar(200) NOT NULL COMMENT '当前最新APP下载链接', 
   `status` int(1) NOT NULL COMMENT '状态 0=历史版本 ，1=当前版本', 
   `create_time` datetime COMMENT '创建时间', 
   `update_time` datetime COMMENT '修改时间', 
   PRIMARY KEY (`id`)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
