### 一、项目准备

#### 1. 数据库初始化

###### 用户表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_user`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_user`;
CREATE TABLE `l7mall_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '用户密码，MD5加密',
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `question` varchar(100) DEFAULT NULL COMMENT '找回密码问题',
  `answer` varchar(100) DEFAULT NULL COMMENT '找回密码答案',
  `role` int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '最后一次更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_unique` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;
```

###### 分类表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_category`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_category`;
CREATE TABLE `l7mall_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类别Id',
  `parent_id` int(11) DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
  `name` varchar(50) DEFAULT NULL COMMENT '类别名称',
  `status` tinyint(1) DEFAULT '1' COMMENT '类别状态1-正常,2-已废弃',
  `sort_order` int(4) DEFAULT NULL COMMENT '排序编号,同类展示顺序,数值相等则自然排序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100032 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `l7mall_category`
-- ----------------------------
BEGIN;
INSERT INTO `l7mall_category` VALUES ('100001', '0', '家用电器', '1', null, '2017-03-25 16:46:00', '2017-03-25 16:46:00'), ('100002', '0', '数码3C', '1', null, '2017-03-25 16:46:21', '2017-03-25 16:46:21'), ('100003', '0', '服装箱包', '1', null, '2017-03-25 16:49:53', '2017-03-25 16:49:53'), ('100004', '0', '食品生鲜', '1', null, '2017-03-25 16:50:19', '2017-03-25 16:50:19'), ('100005', '0', '酒水饮料', '1', null, '2017-03-25 16:50:29', '2017-03-25 16:50:29'), ('100006', '100001', '冰箱', '1', null, '2017-03-25 16:52:15', '2017-03-25 16:52:15'), ('100007', '100001', '电视', '1', null, '2017-03-25 16:52:26', '2017-03-25 16:52:26'), ('100008', '100001', '洗衣机', '1', null, '2017-03-25 16:52:39', '2017-03-25 16:52:39'), ('100009', '100001', '空调', '1', null, '2017-03-25 16:52:45', '2017-03-25 16:52:45'), ('100010', '100001', '电热水器', '1', null, '2017-03-25 16:52:54', '2017-03-25 16:52:54'), ('100011', '100002', '电脑', '1', null, '2017-03-25 16:53:18', '2017-03-25 16:53:18'), ('100012', '100002', '手机', '1', null, '2017-03-25 16:53:27', '2017-03-25 16:53:27'), ('100013', '100002', '平板电脑', '1', null, '2017-03-25 16:53:35', '2017-03-25 16:53:35'), ('100014', '100002', '数码相机', '1', null, '2017-03-25 16:53:56', '2017-03-25 16:53:56'), ('100015', '100002', '3C配件', '1', null, '2017-03-25 16:54:07', '2017-03-25 16:54:07'), ('100016', '100003', '女装', '1', null, '2017-03-25 16:54:44', '2017-03-25 16:54:44'), ('100017', '100003', '帽子', '1', null, '2017-03-25 16:54:51', '2017-03-25 16:54:51'), ('100018', '100003', '旅行箱', '1', null, '2017-03-25 16:55:02', '2017-03-25 16:55:02'), ('100019', '100003', '手提包', '1', null, '2017-03-25 16:55:09', '2017-03-25 16:55:09'), ('100020', '100003', '保暖内衣', '1', null, '2017-03-25 16:55:18', '2017-03-25 16:55:18'), ('100021', '100004', '零食', '1', null, '2017-03-25 16:55:30', '2017-03-25 16:55:30'), ('100022', '100004', '生鲜', '1', null, '2017-03-25 16:55:37', '2017-03-25 16:55:37'), ('100023', '100004', '半成品菜', '1', null, '2017-03-25 16:55:47', '2017-03-25 16:55:47'), ('100024', '100004', '速冻食品', '1', null, '2017-03-25 16:55:56', '2017-03-25 16:55:56'), ('100025', '100004', '进口食品', '1', null, '2017-03-25 16:56:06', '2017-03-25 16:56:06'), ('100026', '100005', '白酒', '1', null, '2017-03-25 16:56:22', '2017-03-25 16:56:22'), ('100027', '100005', '红酒', '1', null, '2017-03-25 16:56:30', '2017-03-25 16:56:30'), ('100028', '100005', '饮料', '1', null, '2017-03-25 16:56:37', '2017-03-25 16:56:37'), ('100029', '100005', '调制鸡尾酒', '1', null, '2017-03-25 16:56:45', '2017-03-25 16:56:45'), ('100030', '100005', '进口洋酒', '1', null, '2017-03-25 16:57:05', '2017-03-25 16:57:05');
COMMIT;
```

###### 产品表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_product`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_product`;
CREATE TABLE `l7mall_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int(11) NOT NULL COMMENT '分类id,对应l7mall_category表的主键',
  `name` varchar(100) NOT NULL COMMENT '商品名称',
  `subtitle` varchar(200) DEFAULT NULL COMMENT '商品副标题',
  `main_image` varchar(500) DEFAULT NULL COMMENT '产品主图,url相对地址',
  `sub_images` text COMMENT '图片地址,json格式,扩展用',
  `detail` text COMMENT '商品详情',
  `price` decimal(20,2) NOT NULL COMMENT '价格,单位-元保留两位小数',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  `status` int(6) DEFAULT '1' COMMENT '商品状态.1-在售 2-下架 3-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Records of `l7mall_product`
-- ----------------------------
BEGIN;
INSERT INTO `l7mall_product` VALUES ('26', '100002', 'Apple iPhone 7 Plus (A1661) 128G 玫瑰金色 移动联通电信4G手机', 'iPhone 7，现更以红色呈现。', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg', '241997c4-9e62-4824-b7f0-7425c3c28917.jpeg,b6c56eb0-1748-49a9-98dc-bcc4b9788a54.jpeg,92f17532-1527-4563-aa1d-ed01baa0f7b2.jpeg,3adbe4f7-e374-4533-aa79-cc4a98c529bf.jpeg', '<p><img alt=\"10000.jpg\" src=\"http://img.happyl7mall.com/00bce8d4-e9af-4c8d-b205-e6c75c7e252b.jpg\" width=\"790\" height=\"553\"><br></p><p><img alt=\"20000.jpg\" src=\"http://img.happyl7mall.com/4a70b4b4-01ee-46af-9468-31e67d0995b8.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"30000.jpg\" src=\"http://img.happyl7mall.com/0570e033-12d7-49b2-88f3-7a5d84157223.jpg\" width=\"790\" height=\"365\"><br></p><p><img alt=\"40000.jpg\" src=\"http://img.happyl7mall.com/50515c02-3255-44b9-a829-9e141a28c08a.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"50000.jpg\" src=\"http://img.happyl7mall.com/c138fc56-5843-4287-a029-91cf3732d034.jpg\" width=\"790\" height=\"525\"><br></p><p><img alt=\"60000.jpg\" src=\"http://img.happyl7mall.com/c92d1f8a-9827-453f-9d37-b10a3287e894.jpg\" width=\"790\" height=\"525\"><br></p><p><br></p><p><img alt=\"TB24p51hgFkpuFjSspnXXb4qFXa-1776456424.jpg\" src=\"http://img.happyl7mall.com/bb1511fc-3483-471f-80e5-c7c81fa5e1dd.jpg\" width=\"790\" height=\"375\"><br></p><p><br></p><p><img alt=\"shouhou.jpg\" src=\"http://img.happyl7mall.com/698e6fbe-97ea-478b-8170-008ad24030f7.jpg\" width=\"750\" height=\"150\"><br></p><p><img alt=\"999.jpg\" src=\"http://img.happyl7mall.com/ee276fe6-5d79-45aa-8393-ba1d210f9c89.jpg\" width=\"790\" height=\"351\"><br></p>', '6999.00', '9991', '1', null, '2017-04-13 21:45:41'), ('27', '100006', 'Midea/美的 BCD-535WKZM(E)冰箱双开门对开门风冷无霜智能电家用', '送品牌烤箱，五一大促', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg', 'ac3e571d-13ce-4fad-89e8-c92c2eccf536.jpeg,4bb02f1c-62d5-48cc-b358-97b05af5740d.jpeg,36bdb49c-72ae-4185-9297-78829b54b566.jpeg', '<p><img alt=\"miaoshu.jpg\" src=\"http://img.happyl7mall.com/9c5c74e6-6615-4aa0-b1fc-c17a1eff6027.jpg\" width=\"790\" height=\"444\"><br></p><p><img alt=\"miaoshu2.jpg\" src=\"http://img.happyl7mall.com/31dc1a94-f354-48b8-a170-1a1a6de8751b.jpg\" width=\"790\" height=\"1441\"><img alt=\"miaoshu3.jpg\" src=\"http://img.happyl7mall.com/7862594b-3063-4b52-b7d4-cea980c604e0.jpg\" width=\"790\" height=\"1442\"><img alt=\"miaoshu4.jpg\" src=\"http://img.happyl7mall.com/9a650563-dc85-44d6-b174-d6960cfb1d6a.jpg\" width=\"790\" height=\"1441\"><br></p>', '3299.00', '8876', '1', '2017-04-13 18:51:54', '2017-04-13 21:45:41'), ('28', '100012', '4+64G送手环/Huawei/华为 nova 手机P9/P10plus青春', 'NOVA青春版1999元', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg', '0093f5d3-bdb4-4fb0-bec5-5465dfd26363.jpeg,13da2172-4445-4eb5-a13f-c5d4ede8458c.jpeg,58d5d4b7-58d4-4948-81b6-2bae4f79bf02.jpeg', '<p><img alt=\"11TB2fKK3cl0kpuFjSsziXXa.oVXa_!!1777180618.jpg\" src=\"http://img.happyl7mall.com/5c2d1c6d-9e09-48ce-bbdb-e833b42ff664.jpg\" width=\"790\" height=\"966\"><img alt=\"22TB2YP3AkEhnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happyl7mall.com/9a10b877-818f-4a27-b6f7-62887f3fb39d.jpg\" width=\"790\" height=\"1344\"><img alt=\"33TB2Yyshk.hnpuFjSZFpXXcpuXXa_!!1777180618.jpg\" src=\"http://img.happyl7mall.com/7d7fbd69-a3cb-4efe-8765-423bf8276e3e.jpg\" width=\"790\" height=\"700\"><img alt=\"TB2diyziB8kpuFjSspeXXc7IpXa_!!1777180618.jpg\" src=\"http://img.happyl7mall.com/1d7160d2-9dba-422f-b2a0-e92847ba6ce9.jpg\" width=\"790\" height=\"393\"><br></p>', '1999.00', '9994', '1', '2017-04-13 18:57:18', '2017-04-13 21:45:41'), ('29', '100008', 'Haier/海尔HJ100-1HU1 10公斤滚筒洗衣机全自动带烘干家用大容量 洗烘一体', '门店机型 德邦送货', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg', '173335a4-5dce-4afd-9f18-a10623724c4e.jpeg,42b1b8bc-27c7-4ee1-80ab-753d216a1d49.jpeg,2f1b3de1-1eb1-4c18-8ca2-518934931bec.jpeg', '<p><img alt=\"1TB2WLZrcIaK.eBjSspjXXXL.XXa_!!2114960396.jpg\" src=\"http://img.happyl7mall.com/ffcce953-81bd-463c-acd1-d690b263d6df.jpg\" width=\"790\" height=\"920\"><img alt=\"2TB2zhOFbZCO.eBjSZFzXXaRiVXa_!!2114960396.jpg\" src=\"http://img.happyl7mall.com/58a7bd25-c3e7-4248-9dba-158ef2a90e70.jpg\" width=\"790\" height=\"1052\"><img alt=\"3TB27mCtb7WM.eBjSZFhXXbdWpXa_!!2114960396.jpg\" src=\"http://img.happyl7mall.com/2edbe9b3-28be-4a8b-a9c3-82e40703f22f.jpg\" width=\"790\" height=\"820\"><br></p>', '4299.00', '9993', '1', '2017-04-13 19:07:47', '2017-04-13 21:45:41');
COMMIT;
```

###### 购物车表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_cart`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_cart`;
CREATE TABLE `l7mall_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `checked` int(11) DEFAULT NULL COMMENT '是否选择,1=已勾选,0=未勾选',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `user_id_index` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=146 DEFAULT CHARSET=utf8;
```

###### 收货地址表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_shipping`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_shipping`;
CREATE TABLE `l7mall_shipping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `receiver_name` varchar(20) DEFAULT NULL COMMENT '收货姓名',
  `receiver_phone` varchar(20) DEFAULT NULL COMMENT '收货固定电话',
  `receiver_mobile` varchar(20) DEFAULT NULL COMMENT '收货移动电话',
  `receiver_province` varchar(20) DEFAULT NULL COMMENT '省份',
  `receiver_city` varchar(20) DEFAULT NULL COMMENT '城市',
  `receiver_district` varchar(20) DEFAULT NULL COMMENT '区/县',
  `receiver_address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `receiver_zip` varchar(6) DEFAULT NULL COMMENT '邮编',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
```

###### 订单表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_order`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_order`;
CREATE TABLE `l7mall_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `shipping_id` int(11) DEFAULT NULL,
  `payment` decimal(20,2) DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
  `payment_type` int(4) DEFAULT NULL COMMENT '支付类型,1-在线支付',
  `postage` int(10) DEFAULT NULL COMMENT '运费,单位是元',
  `status` int(10) DEFAULT NULL COMMENT '订单状态:0-已取消-10-未付款，20-已付款，40-已发货，50-交易成功，60-交易关闭',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `send_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易关闭时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_no_index` (`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;
```

###### 订单明细表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_order_item`;
CREATE TABLE `l7mall_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
  `user_id` int(11) DEFAULT NULL,
  `order_no` bigint(20) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(100) DEFAULT NULL COMMENT '商品名称',
  `product_image` varchar(500) DEFAULT NULL COMMENT '商品图片地址',
  `current_unit_price` decimal(20,2) DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
  `quantity` int(10) DEFAULT NULL COMMENT '商品数量',
  `total_price` decimal(20,2) DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_no_index` (`order_no`) USING BTREE,
  KEY `order_no_user_id_index` (`user_id`,`order_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=135 DEFAULT CHARSET=utf8;
```

###### 支付信息表：

```mysql
-- ----------------------------
--  Table structure for `l7mall_pay_info`
-- ----------------------------
DROP TABLE IF EXISTS `l7mall_pay_info`;
CREATE TABLE `l7mall_pay_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `order_no` bigint(20) DEFAULT NULL COMMENT '订单号',
  `pay_platform` int(10) DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
  `platform_number` varchar(200) DEFAULT NULL COMMENT '支付宝支付流水号',
  `platform_status` varchar(20) DEFAULT NULL COMMENT '支付宝支付状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;
```



#### 2. 使用maven创建web项目

将项目分为dao、service和web三个模块，各模块的pom.xml文件配置：

###### L7Mall

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.LS</groupId>
    <artifactId>L7Mall</artifactId>
    <packaging>pom</packaging>
    <version>1.0-SNAPSHOT</version>
    <modules>
        <module>dao</module>
        <module>service</module>
        <module>web</module>
    </modules>

    <properties>
        <spring.version>4.3.25.RELEASE</spring.version>
        <mybatis.version>3.5.2</mybatis.version>
        <mybatis-spring.version>2.0.1</mybatis-spring.version>
    </properties>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <target>1.12</target>
                    <source>1.12</source>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```

###### dao

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>L7Mall</artifactId>
        <groupId>com.LS</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>dao</artifactId>

    <dependencies>
        <!-- mysql驱动-->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.18</version>
        </dependency>
        <!-- mybatis-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>${mybatis.version}</version>
        </dependency>
        <!-- spring-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-expression</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.2</version>
        </dependency>
        <!-- mybatis和spring整合-->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>${mybatis-spring.version}</version>
        </dependency>
        <!-- JDBC持久层操作-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-tx</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-dbcp2</artifactId>
            <version>2.7.0</version>
        </dependency>
    

        <!-- https://mvnrepository.com/artifact/commons-codec/commons-codec -->
        <dependency>
            <groupId>commons-codec</groupId>
            <artifactId>commons-codec</artifactId>
            <version>1.10</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/commons-configuration/commons-configuration -->
        <dependency>
            <groupId>commons-configuration</groupId>
            <artifactId>commons-configuration</artifactId>
            <version>1.10</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/commons-lang/commons-lang -->
        <dependency>
            <groupId>commons-lang</groupId>
            <artifactId>commons-lang</artifactId>
            <version>2.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/commons-logging/commons-logging -->
        <dependency>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
            <version>1.1.1</version>
        </dependency>
        <dependency>
            <groupId>com.google.zxing</groupId>
            <artifactId>core</artifactId>
            <version>2.1</version>
        </dependency>
        <dependency>
            <groupId>com.google.code.gson</groupId>
            <artifactId>gson</artifactId>
            <version>2.3.1</version>
        </dependency>
        <dependency>
            <groupId>org.hamcrest</groupId>
            <artifactId>hamcrest-core</artifactId>
            <version>1.3</version>
        </dependency>

    </dependencies>

</project>
```

###### service

```java
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>L7Mall</artifactId>
        <groupId>com.LS</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>service</artifactId>

    <dependencies>
        <!-- 引入持久层-->
        <dependency>
            <groupId>com.LS</groupId>
            <artifactId>dao</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <!-- spring AOP开发-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>aopalliance</groupId>
            <artifactId>aopalliance</artifactId>
            <version>1.0</version>
        </dependency>
        <!-- AspectJ AOP开发-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aspects</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>4.3.25.RELEASE</version>
        </dependency>
    </dependencies>

</project>
```

###### web

```java
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>com.LS</groupId>
  <artifactId>web</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>web Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.12</maven.compiler.source>
    <maven.compiler.target>1.12</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.12</version>
    </dependency>
    <!-- 引入业务层-->
    <dependency>
      <groupId>com.LS</groupId>
      <artifactId>service</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
    <!-- servlet-->
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
    </dependency>
    <!-- jstl-->
    <dependency>
      <groupId>jstl</groupId>
      <artifactId>jstl</artifactId>
      <version>1.2</version>
    </dependency>
    <!-- springMVC-->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-webmvc</artifactId>
      <version>4.3.25.RELEASE</version>
    </dependency>
  </dependencies>

  <build>
    <finalName>web</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        
      </plugins>
    </pluginManagement>
  </build>
</project>

```



#### 3. Spring初始化配置

###### spring-dao.xml

```java
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <!-- 开启自动扫描-->
    <context:component-scan base-package="com.ls.l7mall.dao"></context:component-scan>
    <!-- 数据源-->
    <bean id="database" class="org.springframework.jdbc.datasource.DriverManagerDataSource">
        <property name="driverClassName" value="com.mysql.cj.jdbc.Driver"/>
        <property name="url" value="jdbc:mysql://localhost:3306/l7mall?useUnicode=true&amp;characterEncoding=utf-8&amp;useSSL=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=Hongkong"/>
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </bean>

    <!-- sqlSession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="database"/>
        <property name="typeAliasesPackage" value="com.ls.l7mall.entity"/>
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    
                </bean>
            </array>
        </property>
    </bean>

    <!-- 映射器-->
    <bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
        <property name="basePackage" value="com.ls.l7mall.dao"/>
        <property name="sqlSessionFactoryBeanName" value="sqlSessionFactory"/>
    </bean>

</beans>
```

###### spring-service.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd 
       http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd 
       http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 导入dao层的spring配置-->
    <import resource="spring-dao.xml"/>
    <!-- 开启自动扫描-->
    <context:component-scan base-package="com.ls.l7mall.service"></context:component-scan>

    <!-- AspectJ AOP开发使用注解模式-->
    <aop:aspectj-autoproxy/>

    <!-- 事务管理使用声明式事务管理-->
    <!--tx拦截器-->
    <bean id="txManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
        <property name="dataSource" ref="database"/>
    </bean>
    <!-- 通知-->
    <tx:advice id="interceptor" transaction-manager="txManager">
        <tx:attributes>
            <tx:method name="get*" read-only="true"/>
            <tx:method name="find*" read-only="true"/>
            <tx:method name="search*" read-only="true"/>
            <tx:method name="*" propagation="REQUIRED"/>
        </tx:attributes>
    </tx:advice>
    <!-- 切点-->
    <aop:config>
        <aop:pointcut id="pointCut" expression="execution(* com.ls.l7mall.service.*.*(..))"/>
        <!-- 关联切点和通知-->
        <aop:advisor advice-ref="interceptor" pointcut-ref="pointCut"/>
    </aop:config>

</beans>
```

###### web.xml

```
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  <!-- 配置一个编码过滤器-->
  <filter>
    <filter-name>EncodingFilter</filter-name>
    <filter-class>com.ls.l7mall.global.EncodingFilter</filter-class>
  </filter>
  <filter-mapping>
    <filter-name>EncodingFilter</filter-name>
    <url-pattern>/*</url-pattern>
  </filter-mapping>

  <!-- 配置一个核心控制器-->
  <servlet>
    <servlet-name>dispatcher</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
      <param-name>contextConfigLocation</param-name>
      <param-value>classpath:spring-web.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>*.do</url-pattern>
  </servlet-mapping>
  
</web-app>
```

###### spring-web.xml

```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc https://www.springframework.org/schema/mvc/spring-mvc.xsd">

    <!-- 引入service层的spring配置-->
    <import resource="spring-service.xml"/>

    <!-- 开启自动扫描-->
    <context:component-scan base-package="com.ls.l7mall.controller"></context:component-scan>
    
    <mvc:annotation-driven/>
    
    <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="maxUploadSize" value="#{1024*1024}"/>
    </bean>
    
</beans>
```



#### 4. Logback初始化

###### 在dao层的pom.xml文件中引入依赖

```
<!-- logback依赖引入-->
        <!-- https://mvnrepository.com/artifact/ch.qos.logback/logback-classic -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
            <scope>compile</scope>
        </dependency>
        <!-- https://mvnrepository.com/artifact/ch.qos.logback/logback-core -->
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.3</version>
            <scope>compile</scope>
        </dependency>
```

###### logback.xml文件的配置，注意修改其中的生成路径

```
<?xml version="1.0" encoding="UTF-8"?>
<configuration scan="true" scanPeriod="60 seconds" debug="false">
    <appender name="console" class="ch.qos.logback.core.ConsoleAppender">
        <encoding>UTF-8</encoding>
        <encoder>
            <pattern>[%d{HH:mm:ss.SSS}][%p][%c{40}][%t] %m%n</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>DEBUG</level>
        </filter>
    </appender>

    <appender name="l7mall" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--<File>d:/mmalllog/mmall.log</File>-->
        <File>F:/L7Mall_Log/LT_Mall.log</File>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>F:/L7Mall_Log/LT_Mall.log.%d{yyyy-MM-dd}.gz</fileNamePattern>
            <append>true</append>
            <maxHistory>10</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>[%d{HH:mm:ss.SSS}][%p][%c{40}][%t] %m%n</pattern>
        </encoder>
    </appender>


    <appender name="error" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <!--<File>d:/mmalllog/error.log</File>-->
        <File>F:/L7Mall_Log/error.log</File>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>F:/L7Mall_Log/error.log.%d{yyyy-MM-dd}.gz</fileNamePattern>
            <!--<fileNamePattern>d:/mmalllog/error.log.%d{yyyy-MM-dd}.gz</fileNamePattern>-->
            <append>true</append>
            <maxHistory>10</maxHistory>
        </rollingPolicy>
        <encoder>
            <pattern>[%d{HH:mm:ss.SSS}][%p][%c{40}][%t] %m%n</pattern>
        </encoder>
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <level>ERROR</level>
            <onMatch>ACCEPT</onMatch>
            <onMismatch>DENY</onMismatch>
        </filter>
    </appender>

    <logger name="com.ls" additivity="false" level="INFO" >
        <appender-ref ref="l7mall" />
        <appender-ref ref="console"/>
    </logger>


    <!-- geelynote mybatis log 日志 -->

    <logger name="com.ls.l7mall.dao" level="DEBUG"/>

    <!--<logger name="com.ibatis.sqlmap.engine.impl.SqlMapClientDelegate" level="DEBUG" >-->
    <!--<appender-ref ref="console"/>-->
    <!--</logger>-->

    <!--<logger name="java.sql.Connection" level="DEBUG">-->
    <!--<appender-ref ref="console"/>-->
    <!--</logger>-->
    <!--<logger name="java.sql.Statement" level="DEBUG">-->
    <!--<appender-ref ref="console"/>-->
    <!--</logger>-->

    <!--<logger name="java.sql.PreparedStatement" level="DEBUG">-->
    <!--<appender-ref ref="console"/>-->
    <!--</logger>-->


    <root level="DEBUG">
        <appender-ref ref="console"/>
        <appender-ref ref="error"/>
    </root>

</configuration>
```



#### 6. FTP服务器配置

在l7mall.xml文件中配置FTP服务器的基本属性，供后期使用

```
ftp.server.ip=192.168.66.1
ftp.user=root
ftp.password=root
ftp.server.http.prefix=http://image.l7mall.com/
```



#### 7. Nginx搭建服务器解析

配置访问域名：<C:\Windows\System32\drivers\etc>

<img src="C:\Users\11518\AppData\Roaming\Typora\typora-user-images\image-20200324112659133.png" alt="image-20200324112659133" style="zoom:50%;" />

Nginx主配置：<D:\Nginx\nginx-1.16.1\conf>

​	<img src="C:\Users\11518\AppData\Roaming\Typora\typora-user-images\image-20200324112843101.png" alt="image-20200324112843101" style="zoom:50%;" /	>

配置域名转发：<D:\Nginx\nginx-1.16.1\conf\vhost\image.l7mall.com.conf>

<img src="C:\Users\11518\AppData\Roaming\Typora\typora-user-images\image-20200324112942867.png" alt="image-20200324112942867" style="zoom:50%;" />

配置端口转发：<D:\Nginx\nginx-1.16.1\conf\vhost\tomcat.l7mall.com.conf>

<img src="C:\Users\11518\AppData\Roaming\Typora\typora-user-images\image-20200324113348222.png" alt="image-20200324113348222" style="zoom:50%;" />

<img src="C:\Users\11518\AppData\Roaming\Typora\typora-user-images\image-20200324113028889.png" alt="image-20200324113028889" style="zoom:50%;" />



### 二、各功能模块开发

#### 1. 用户管理

##### 功能、接口设计：

<https://github.com/laijingshao/l7mall/wiki/1.用户管理——门户接口>

<https://github.com/laijingshao/l7mall/wiki/2.用户管理——后台接口>

##### coding

* entity

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/User.java>


* controller

  * 门户

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/portal/UserController.java>

  * 后台

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/backend/UserManageController.java>

* service

  * service

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/UserService.java>

  * impl

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/impl/UserServiceImpl.java>

* dao

  * dao

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/UserDao.java>

  * xml

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/UserDao.xml>

##### 注意问题

######  MD5明文加密及增加salt值

  * 引入工具类

  ```java
  package com.ls.l7mall.util;
  
  import java.security.MessageDigest;
  
  /**
   * @author laijs
   * @date 2020-3-14-19:45
   */
  public class MD5Util {
      private static String byteArrayToHexString(byte b[]) {
          StringBuffer resultSb = new StringBuffer();
          for (int i = 0; i < b.length; i++)
              resultSb.append(byteToHexString(b[i]));
  
          return resultSb.toString();
      }
  
      private static String byteToHexString(byte b) {
          int n = b;
          if (n < 0)
              n += 256;
          int d1 = n / 16;
          int d2 = n % 16;
          return hexDigits[d1] + hexDigits[d2];
      }
  
      /**
       * 返回大写MD5
       *
       * @param origin
       * @param charsetname
       * @return
       */
      private static String MD5Encode(String origin, String charsetname) {
          String resultString = null;
          try {
              resultString = new String(origin);
              MessageDigest md = MessageDigest.getInstance("MD5");
              if (charsetname == null || "".equals(charsetname))
                  resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
              else
                  resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
          } catch (Exception exception) {
          }
          return resultString.toUpperCase();
      }
  
      public static String MD5EncodeUtf8(String origin) {
          origin = origin + PropertiesUtil.getProperty("password.salt", "");
          return MD5Encode(origin, "utf-8");
      }
  
  
      private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
              "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
  }
  
  ```

  * 关键代码

  ```java
  public static String MD5EncodeUtf8(String origin) {
          origin = origin + PropertiesUtil.getProperty("password.salt", "");
          return MD5Encode(origin, "utf-8");
      }
  ```

  * 在l7mall.xml中配置salt值

  ```java
  password.salt = geelysdafaqj23ou89ZXcj@#$@#$#@KJdjklj;D../dSF.,
  ```

  * 使用工具类对密码进行加密

  ```java
   // 注册功能
      public ResponseEntity<String> register(User user) {
          // 检查用户名是否已被使用
          ResponseEntity<String> response = this.checkValid(user.getUsername(), Const.USERNAME);
          if (!response.isSuccess()) {
              return response;
          }
          // 检查email使用已被使用
          response = this.checkValid(user.getemail(), Const.EMAIL);
          if (!response.isSuccess()) {
              return response;
          }
          // 设置用户等级
          user.setRole(Const.Role.ROLE_CUSTOMER);
          // 对密码进行MD5加密
          user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
          // 持久层操作
          int resultCount = userDao.insert(user);
          if (resultCount == 0) {
              return ResponseEntity.responesWhenError("注册失败");
          }
          return ResponseEntity.responesWhenSuccess();
      }
  
  // 登录功能
      public ResponseEntity<User> login(String username, String password) {
          // 校验用户名是否存在
          int resultCount = userDao.checkUsername(username);
          if (resultCount == 0) {
              return ResponseEntity.responesWhenError("用户名不存在");
          }
          // 根据用户名和用户密码(MD5加密)找到用户
          String md5password = MD5Util.MD5EncodeUtf8(password);
          User user = userDao.selectToLogin(username, md5password);
          if (user == null) {
              return ResponseEntity.responesWhenError("密码错误");
          }
          // 将置空密码后的用户信息作为响应信息
          user.setPassword(StringUtils.EMPTY);
          return ResponseEntity.responesWhenSuccess("登录成功", user);
      }
  ```

###### 利用Guava缓存携带token信息

* 添加依赖

```java
<!-- https://mvnrepository.com/artifact/com.google.guava/guava -->
        <dependency>
            <groupId>com.google.guava</groupId>
            <artifactId>guava</artifactId>
            <version>28.2-jre</version>
        </dependency>
```

* 创建一个缓存类

```java
package com.ls.l7mall.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author laijs
 * @date 2020-3-14-19:42
 */
public class TokenCache {
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static final String TOKEN_PREFIX = "token_";

    private static LoadingCache<String,String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000)
            .expireAfterAccess(1, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);
            if("null".equals(value)){
                return null;
            }
            return value;
        } catch (ExecutionException e) {
            logger.error("localCache get error",e);
        }
        return null;
    }

}
```

* **使用：校验答案时生成一个具有时效的token信息，重置密码需验证该token信息，不正确或者失效都不能进行重置密码操作**

```java
// 校验答案
    @Override
    public ResponseEntity<String> checkAnswer(String username, String question, String answer) {
        int resultCount = userDao.selectAnswer(username, question, answer);
        if (resultCount > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ResponseEntity.responesWhenSuccess(forgetToken);
        }
        return ResponseEntity.responesWhenError("安全问题答案错误");
    }
// 重置密码
    @Override
    public ResponseEntity<String> forgetResetPassword(String username, String newPassword,String forgetToken) {
        // 判断forgetToken是否为空
        if(forgetToken.isBlank()){
            return ResponseEntity.responesWhenError("重置密码需要token参数");
        }
        // 判断username是否存在
        ResponseEntity<String> response = this.checkValid(username, Const.USERNAME);
        if(response.isSuccess()){
            return ResponseEntity.responesWhenError("用户不存在");
        }
        // 判断username对应的token是否失效
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)){
            return ResponseEntity.responesWhenError("token已失效");
        }
        // 判断token和forgetToken是否一致
        if(StringUtils.equals(token,forgetToken)){
            // 调用持久层进行重置密码
            String md5Password = MD5Util.MD5EncodeUtf8(newPassword);
            int resultCount = userDao.updatePassword(username, md5Password);
            if(resultCount>0){
                return ResponseEntity.responesWhenSuccess("重置密码成功");
            } else {
                return ResponseEntity.responesWhenError("重置密码失败");
            }
        } else {
            return ResponseEntity.responesWhenError("token错误，请重新获取");
        }
    }
```

###### 高复用服务响应对象的设计思想及抽象封装

* 依赖引入（Json序列化）

```java
 <!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-core-asl -->
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-core-asl</artifactId>
            <version>1.9.13</version>
        </dependency>
<!-- https://mvnrepository.com/artifact/org.codehaus.jackson/jackson-mapper-asl -->
        <dependency>
            <groupId>org.codehaus.jackson</groupId>
            <artifactId>jackson-mapper-asl</artifactId>
            <version>1.9.13</version>
        </dependency>
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-core</artifactId>
            <version>2.10.0</version>
        </dependency>
<!-- https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind -->
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.10.0</version>
        </dependency>
```

* 涉及实体类用于封装响应数据

```java
package com.ls.l7mall.global;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * @author laijs
 * @date 2020-3-14-19:40
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResponseEntity<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    // 只响应状态码
    private ResponseEntity(int status) {
        this.status = status;
    }

    //响应状态码和提示信息
    private ResponseEntity(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //响应状态码和数据
    private ResponseEntity(int status, T data) {
        this.status = status;
        this.data = data;
    }

    //响应状态码、提示信息和数据
    private ResponseEntity(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    // 响应成功时，有以下情况
    // * 只响应状态码
    public static <T> ResponseEntity<T> responesWhenSuccess(){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode());
    }
    // * 响应状态码和提示信息
    public static <T> ResponseEntity<T> responesWhenSuccess(String msg){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    // * 响应状态码和数据
    public static <T> ResponseEntity<T> responesWhenSuccess(T data){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    // * 响应状态码、提示信息和数据
    public static <T> ResponseEntity<T> responesWhenSuccess(String msg,T data){
        return new ResponseEntity<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }

    // 响应失败时，有以下情况
    // * 普通错误：响应状态码和状态描述
    public static <T> ResponseEntity<T> responesWhenError(){
        return new ResponseEntity<T>(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDecs());
    }
    // * 响应状态码和提示信息
    public static <T> ResponseEntity<T> responesWhenError(String msg){
        return new ResponseEntity<T>(ResponseCode.ERROR.getCode(),msg);
    }
    // * 响应其他状态码和提示信息
    public static <T> ResponseEntity<T> responesWhenError(int code,String msg){
        return new ResponseEntity<T>(code,msg);
    }

    // 提供一个判断是否响应成功的方法,该方法不参与json序列化
    @JsonIgnore
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }
}
```

* 响应数据中的状态信息封装

```java
package com.ls.l7mall.global;

/**
 * @author laijs
 * @date 2020-3-14-19:39
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String decs;

    ResponseCode(int code, String decs) {
        this.code = code;
        this.decs = decs;
    }

    public int getCode() {
        return code;
    }

    public String getDecs() {
        return decs;
    }
}
```

###### String类的一个安全的工具类

* 引入依赖

```java
<!-- https://mvnrepository.com/artifact/org.apache.commons/commons-lang3 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.8.1</version>
        </dependency>
```

* 工具类介绍

<http://commons.apache.org/proper/commons-lang/apidocs/org/apache/commons/lang3/StringUtils.html>

###### 涉及的常量封装

```java
package com.ls.l7mall.global;

import com.alipay.api.domain.LoanRepayPlanTerm;
import com.google.common.collect.Sets;

import java.util.Set;

/**
 * @author laijs
 * @date 2020-3-14-19:39
 */
public class Const {
    // 登录时session的key值
    public static final String CURRENT_USER = "currentUser";

    // 校验用户名和email时的type
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";

    // 用户等级
    public interface Role{
        int ROLE_CUSTOMER = 0; //普通用户
        int ROLE_ADMIN = 1;//管理员
    }
    
}
```

###### 分页插件pagehelper的使用

* 依赖引入

```java
<dependency> 
    <groupId>com.github.pagehelper</groupId> 
    <artifactId>pagehelper</artifactId> 
    <version>5.1.10</version> 
</dependency>
```

* 配置spring-service文件

```java
<!-- sqlSession-->
    <bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <property name="dataSource" ref="database"/>
        <property name="typeAliasesPackage" value="com.ls.l7mall.entity"/>
        <property name="plugins">
            <array>
                <bean class="com.github.pagehelper.PageInterceptor">
                    
                </bean>
            </array>
        </property>
    </bean>
```

* 使用

```java
// 获取用户列表
    public ResponseEntity getUserList(Integer pageSize,Integer pageNum){
        Page<Object> page = PageHelper.startPage(pageNum, pageSize);
        List<User> users = userDao.selectAll();
        PageInfo<User> userPageInfo = new PageInfo<User>(users);
        return ResponseEntity.responesWhenSuccess(userPageInfo);
    }
```

#### 2. 分类管理

##### 功能、接口设计：

<https://github.com/laijingshao/l7mall/wiki/3.分类管理>

##### coding

* entity

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/Category.java>


* controller

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/backend/CategoryManageController.java>

* service

  * service

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/CategoryService.java>

   * impl

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/impl/CategoryServiceImpl.java>


* dao

  * dao

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/CategoryDao.java>

  * xml

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/CategoryDao.xml>

##### 注意问题

###### 如何设计及封装无限层级的树状数据结构

* 为数据设计一个parentId属性，默认将最高级节点的parentId设置为0，这样就能无限拓展数据的层级

###### 复杂对象的排重

* 利用set集合不可重复的特点，使用set集合储存数据即可完成排重

###### 递归算法的设计思想

```java
public ResponseEntity<List<Integer>> getDeepChildrenCategoryById(Integer categoryId){
        // 创建一个set集合用于储存遍历的category对象
        Set<Category> categorySet = Sets.newHashSet();
        // 当category类的id一致时，就认为其是同一个的category对象，因此要重写category类的equals和hashCode方法

        // 创建一个递归方法将所有category对象存于set集合，在此调用
        findChildCategory(categorySet,categoryId);

        // 创建list集合保存set集合中所有category对象的id值
        List<Integer> categoryIdList = Lists.newArrayList();
        if(categoryId != null){
            for(Category categoryItem : categorySet){
                categoryIdList.add(categoryItem.getId());
            }
        }
        return ResponseEntity.responesWhenSuccess(categoryIdList);
    }

    // 递归算法
    private Set<Category> findChildCategory(Set<Category> categorySet,Integer categoryId){
        // 判断当前id表示的节点
        Category category = categoryDao.selectById(categoryId);
        if(category != null){
            // 加入set集合中
            categorySet.add(category);
        }
        // 获取当前节点的子节点
        List<Category> categories = categoryDao.selectChildrenCategoryById(categoryId);
        // 遍历集合，集合中的每个category都进行再遍历
        for (Category categoryItem : categories) {
            findChildCategory(categorySet,categoryItem.getId());
        }
        // 最终的set已经保存了每个category对象
        return categorySet;
    }
```


#### 3. 商品管理

##### 功能、接口设计：

<https://github.com/laijingshao/l7mall/wiki/4.商品管理——门户接口>

<https://github.com/laijingshao/l7mall/wiki/5.商品管理——后台接口>

##### coding


* entity

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/Product.java>

* controller

  * 门户

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/portal/ProductController.java>

  * 后台

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/backend/ProductManageController.java>

* service

  * service

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/ProductService.java>

   * impl

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/impl/ProductServiceImpl.java>

* dao

  * dao

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/ProductDao.java>

  * xml

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/ProductDao.xml>

##### 注意问题

###### 创建一个读取配置文件的工具类

* **在静态块中使用流来读取配置文件**

```java
package com.ls.l7mall.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @author laijs
 * @date 2020-3-14-19:45
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    private static Properties props;

    static {
        String fileName = "l7mall.properties";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            logger.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key,String defaultValue){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }
}
```

###### 使用VO对象封装POJO对象

* 当响应的数据和POJO类相关却不完成一致时，考虑创建VO（value object）对象对POJO对象进行加工封装

```java
package com.ls.l7mall.vo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author laijs
 * @date 2020-3-15-23:41
 */
public class ProductDetailVo {
    // 从product类中继承来的属性
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private String createTime;
    private String updateTime;
    
    // 拓展属性
    // 图片地址前缀
    private String imageHost;
    // 产品父类id
    private Integer parentCategoryId;

    public ProductDetailVo(Integer id, Integer categoryId, String name, String subtitle, String mainImage, String subImages, String detail, BigDecimal price, Integer stock, Integer status) {
        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.subtitle = subtitle;
        this.mainImage = mainImage;
        this.subImages = subImages;
        this.detail = detail;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    setter and getter;
}
```

* 在业务层提供一个方法将POJO对象封装为VO对象

```java
 // Product ---> ProductDetailVo
    public ProductDetailVo assembleProductDetailVo(Product product){
        // 从POJO类中继承而来、且不需加工的属性，通过构造器赋值给VO类
        ProductDetailVo vo = new ProductDetailVo(product.getId(), product.getCategoryId(), product.getName(), product.getSubtitle(), product.getMainImage(),
                product.getSubImages(), product.getDetail(), product.getPrice(), product.getStock(), product.getStatus());
        
        // POJO中的createTime和updateTime属性，在VO类中是String，创建一个用于处理时间格式的工具类DateTimeUtil
        String createTime = DateTimeUtil.dateToStr(product.getCreateTime());
        String updateTime = DateTimeUtil.dateToStr(product.getUpdateTime());
        vo.setCreateTime(createTime);
        vo.setUpdateTime(updateTime);
        
        // 图片前缀imageHost属性需要在配置文件中配置
        vo.setImageHost(PropertiesUtil.getProperty("ftp.server.http.prefix","http://image.l7mall.com/"));
        
        // 父类parentCategoryId属性的设置
        Integer categoryId = product.getCategoryId();
        Category category = categoryDao.selectById(categoryId);
        if(category == null){
            vo.setParentCategoryId(0);
        } else {
            vo.setParentCategoryId(category.getParentId());
        }
        
        return vo;
    }
```

###### 使用joda-time处理时间格式

* 简介<https://www.ibm.com/developerworks/cn/java/j-jodatime.html>
* 依赖引入

```java
<!-- https://mvnrepository.com/artifact/joda-time/joda-time -->
        <dependency>
            <groupId>joda-time</groupId>
            <artifactId>joda-time</artifactId>
            <version>2.10.5</version>
        </dependency>
```

* 使用joda-time创建一个工具类用于处理时间格式

```java
package com.ls.l7mall.util;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author laijs
 * @date 2020-3-15-23:55
 */
public class DateTimeUtil {
    
    // 将符合指定格式的时间字符串转化为Date类日期
    public static Date strToDate(String dateTimeString,String dateFormatStr) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatStr);
        Date date = simpleDateFormat.parse(dateTimeString);
        return date;
    }
    
    // 将Date类的日期按照指定格式生成字符串
    public static String dateToStr(Date date,String dateFormatStr){
        if(date == null){
            return StringUtils.EMPTY;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormatStr);
        String format = simpleDateFormat.format(date);
        return format;
    }

    // 以上是使用SimpleDateFormat类完成时间的String和Date之间的转化
    
    // 借助joda-time也可以完成，另外
    // 在同一个项目中，格式String dateFormatStr可以提取为常量
    
    private static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Date strToDate(String dateTimeString) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(STANDARD_FORMAT);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeString);
        Date date = dateTime.toDate();
        return date;
    }

    public static String dateToStr(Date date){
        if(date == null){
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        String s = dateTime.toString();
        return s;
    }
    
}
```

###### SpringMVC上传文件

* 在Spring-web.xml中配置上传文件的bean

```java
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
        <property name="defaultEncoding" value="UTF-8"/>
        <property name="maxUploadSize" value="#{1024*1024}"/>
</bean>
```

* 创建用于处理文件的接口和实现类

```java
package com.ls.l7mall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author laijs
 * @date 2020-3-17-15:28
 */
public interface FileService {
    public String upload(MultipartFile multipartFile, String path);
}

```

```java
package com.ls.l7mall.service.impl;

import com.google.common.collect.Lists;
import com.ls.l7mall.service.FileService;
import com.ls.l7mall.util.FTPServerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author laijs
 * @date 2020-3-17-15:28
 */
@Service("fileService")
public class FileServiceImpl implements FileService {
    
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
    
    public String upload(MultipartFile multipartFile,String path){
        // 获取原文件名
        String fileName = multipartFile.getOriginalFilename();
        // 分离拓展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        // 使用UUID和拓展名生成一个用于上传的不重复的文件名
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件,上传文件的文件名:{},上传的路径:{},新文件名:{}",fileName,path,uploadFileName);
        
        // 根据路径生成目录
        File fileDir = new File(path);
        if(!fileDir.exists()){
            fileDir.setExecutable(true);
            fileDir.mkdirs();
        }
        // 根据路径和文件名生成目标文件类
        File targetFile = new File(path, uploadFileName);
        // 生成文件
        try {
            multipartFile.transferTo(targetFile);
            // 至此文件已经成功上传至tomcat服务器
            
            // 将文件从tomcat服务器上传至FTPServer服务器中
            boolean upload = FTPServerUtils.upload(Lists.newArrayList(targetFile));
            if(!upload){
                logger.error("上传文件异常");
                return  null;
            }
            // 将文件从tomcat服务器中删除
            targetFile.delete();

        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
        
    }
    
}
```

* controller

```java
//  图片上传
    @RequestMapping("upload.do")
    @ResponseBody
    public ResponseEntity upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile multipartFile, HttpServletRequest request){
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            return ResponseEntity.responesWhenError("尚未登录，请登录");
        }
        // 判断是否为管理员
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            // 获取路径
            String path = request.getServletContext().getRealPath("upload");
            // 上传图片
            String targetFileName = fileService.upload(multipartFile, path);
            if(StringUtils.isBlank(targetFileName)){
                return ResponseEntity.responesWhenError("上传文件失败");
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix", "http://image.l7mall.com/") + targetFileName;
            HashMap<String, String> map = Maps.newHashMap();
            map.put("uri",targetFileName);
            map.put("url",url);

            return ResponseEntity.responesWhenSuccess(map);
        }
        return ResponseEntity.responesWhenError("需要管理员权限");
    }
```

###### FTP服务器的对接

* 依赖引入

```java
<!-- https://mvnrepository.com/artifact/commons-net/commons-net -->
        <dependency>
            <groupId>commons-net</groupId>
            <artifactId>commons-net</artifactId>
            <version>3.6</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload -->
        <dependency>
            <groupId>commons-fileupload</groupId>
            <artifactId>commons-fileupload</artifactId>
            <version>1.4</version>
        </dependency>
```

* 创建一个工具类处理和FTP服务器的连接

```java
package com.ls.l7mall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author laijs
 * @date 2020-3-17-15:46
 */
public class FTPServerUtils {
    // 连接FTPServer的四个属性
    private String ip;
    private int port;
    private String user;
    private String password;
    
    // 操作连接FTP服务器的对象属性
    private FTPClient ftpClient;

    // 将该项目用于连接FTPServer服务器的属性封装为常量
    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPassword = PropertiesUtil.getProperty("ftp.password");

    // 日志对象
    private static final Logger logger = LoggerFactory.getLogger(FTPServerUtils.class);
    
    // setter and getter
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public FTPServerUtils(String ip, int port, String user, String password) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.password = password;
    }
    
    // 对外暴露的上传文件的方法
    public static boolean upload(List<File> fileList) throws IOException {
        FTPServerUtils ftpServerUtils = new FTPServerUtils(ftpIp, 21, ftpUser, ftpPassword);
        logger.info("开始连接ftp服务器");
        boolean uploaded = ftpServerUtils.upload("img",fileList);
        logger.info("结束上传,上传结果:{}",uploaded);
        return uploaded;
    }

    private boolean upload(String remotePath,List<File> fileList) throws IOException {
        boolean uploaded = true;
        FileInputStream fis = null;
        //连接FTP服务器
        if(connectServer(this.ip,this.port,this.user,this.ftpPassword)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for(File fileItem : fileList){
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(),fis);
                }

            } catch (IOException e) {
                logger.error("上传文件异常",e);
                uploaded = false;
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        } else {
            uploaded = false;
        }
        
        return uploaded;
    }

    private boolean connectServer(String ip,int port,String user,String pwd){
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }
    
}
```

###### 富文本的上传

* 实现过程和文件上传相似，但需要对响应结果进行加工

```java
// 富文本上传
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile multipartFile, HttpServletRequest request, HttpServletResponse response){
        HashMap resultMap = Maps.newHashMap();
        // 判断是否已经登录
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if(user == null){
            resultMap.put("success",false);
            resultMap.put("msg","尚未登录，请登录");
            return resultMap;
        }
        // 判断是否为管理员
        if(user.getRole() == Const.Role.ROLE_ADMIN){
            // 获取路径
            String path = request.getServletContext().getRealPath("upload");
            // 上传图片
            String targetFileName = fileService.upload(multipartFile, path);
            if(StringUtils.isBlank(targetFileName)){
                resultMap.put("success",false);
                resultMap.put("msg","上传文件失败");
                return resultMap;
            }
            String url = PropertiesUtil.getProperty("ftp.server.http.prefix", "http://image.l7mall.com/") + targetFileName;
            resultMap.put("success",true);
            resultMap.put("msg","上传成功");
            resultMap.put("file_path",url);
            response.addHeader("Access-Control-Allow-Headers","X-File-Name");
            return resultMap;

        }
        resultMap.put("success",false);
        resultMap.put("msg","需要管理员权限");
        return resultMap;
    }
```




#### 4. 购物车管理

##### 功能、接口设计：

<https://github.com/laijingshao/l7mall/wiki/6.购物车管理>

##### coding

* entity

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/Cart.java>


* controller

<https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/portal/CartController.java>

* service

  * service

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/CartService.java>

   * impl

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/impl/CartServiceImpl.java>

* dao

  * dao

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/CartDao.java>

  * xml

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/CartDao.xml>

##### 注意问题

###### 解决浮点型数据在商业运算中的精度丢失问题

* 使用BigDecimal类的String构造器处理浮点型数据的商业运算，可以解决数据精度丢失的问题
* **必须使用其String构造器才能解决**
* 数据库中储存的是浮点型数据，而处理商业运算的业务需要使用BigDecimal类，频繁转化较为麻烦，需要将转化过程封装为工具类，提供加减乘除四种运算方法

```java
package com.ls.l7mall.util;

import java.math.BigDecimal;

/**
 * @author laijs
 * @date 2020-3-18-15:41
 */
public class BigDecimalUtils {
    private BigDecimalUtils(){

    }

    public static BigDecimal add(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2);
    }

    public static BigDecimal subtract(double v1, double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2);
    }

    public static BigDecimal multiply(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2);
    }

    public static BigDecimal divide(double v1,double v2){
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);//四舍五入,保留2位小数

    }
}
```




#### 5. 收货地址管理

##### 功能、接口设计：

<https://github.com/laijingshao/l7mall/wiki/7.收货地址管理>

##### coding

* entity

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/Shipping.java>


* controller

<https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/portal/ShippingController.java>

* service

  * service

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/ShippingService.java>

   * impl

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/impl/ShippingServiceImpl.java>

* dao

  * dao

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/ShippingDao.java>

  * xml

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/ShippingDao.xml>

##### 注意问题

###### 使用mybatis将数据写入数据库时，主键的自动生成和使用

* 使用<insert>标签时，主键可以自动生成，但是如果在数据库中插入了数据后，业务层需要使用自动生成的主键，那么需要使用useGeneratedKeys和keyProperty属性来声明使用主键和指定主键

```java
<insert id="insert" parameterType="Shipping" useGeneratedKeys="true" keyProperty="id">
        insert into l7mall_shipping
        values (#{id},#{userId},#{receiverName},#{receiverPhone},#{receiverMobile},#{receiverProvince},#{receiverCity},#{receiverDistrict},#{receiverAddress},#{receiverZip},now(),now())
    </insert>
```




#### 6. 订单管理（StringUtils含支付功能）

##### 功能、接口设计：

<https://github.com/laijingshao/l7mall/wiki/8.订单管理——门户接口>

<https://github.com/laijingshao/l7mall/wiki/9.订单管理——后台接口>

<https://github.com/laijingshao/l7mall/wiki/10、支付管理>

##### coding

* entity

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/Order.java>

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/OrderItem.java>

<https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/entity/PayInfo.java>


* controller

  * 门户

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/portal/OrderController.java>

  * 后台

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/backend/OrderManageController.java>

  * 支付

  <https://github.com/laijingshao/l7mall/blob/master/web/src/main/java/com/ls/l7mall/controller/portal/OrderController.java>

* service

  * service

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/OrderService.java>

   * impl

  <https://github.com/laijingshao/l7mall/blob/master/service/src/main/java/com/ls/l7mall/service/impl/OrderServiceImpl.java>

* dao

  * dao

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/OrderDao.java>

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/OrderItemDao.java>

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/java/com/ls/l7mall/dao/PayInfoDao.java>

  * xml

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/OrderDao.xml>

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/OrderItemDao.xml>

  <https://github.com/laijingshao/l7mall/blob/master/dao/src/main/resources/com/ls/l7mall/dao/PayInfoDao.xml>

##### 注意问题

###### 订单号的生成

* 使用System.currentTimeMillis()方法可以生成一个仅与当前时间相关的号码

```java
// assembleOrder
    public Order assembleOrder(Integer userId, Integer shippingId, BigDecimal payment) {
        long orderNo = System.currentTimeMillis() + new Random().nextInt(100);
        Order order = new Order(orderNo, userId, shippingId, payment, Const.PaymentType.ONLINE_PAY.getCode(), 0, Const.OrderStatus.NO_PAY.getCode());
        int rowCount = orderDao.insert(order);
        if (rowCount == 0) {
            return null;
        }
        return order;
    }
```



