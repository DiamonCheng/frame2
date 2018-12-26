/*
MySQL Backup
Database: frame2
Backup Time: 2018-12-26 11:40:17
*/

SET FOREIGN_KEY_CHECKS=0;
BEGIN;
LOCK TABLES `frame2`.`sys_resource` WRITE;
DELETE FROM `frame2`.`sys_resource`;
INSERT INTO `frame2`.`sys_resource` (`id`,`create_date_time`,`update_date_time`,`version`,`code`,`icon_class`,`requesturi`,`sort_order`,`type`,`parent_id`,`name_en`,`name_zh`) VALUES (1, '2018-12-21 14:45:04', '2018-12-21 14:45:04', 1, 'index', 'layui-icon layui-icon-home', '/', 1, 'menu', NULL, 'Index', '首页'),(2, '2018-12-21 14:45:04', '2018-12-21 14:45:04', 1, 'system', 'layui-icon layui-icon-set', NULL, 2, 'menu', NULL, 'System Manager', '系统管理'),(3, '2018-12-21 14:45:04', '2018-12-21 14:45:04', 1, 'user', 'layui-icon layui-icon-user', '/dcrud/user/', 1, 'menu', 2, 'User Manager', '用户管理'),(4, '2018-12-21 14:45:04', '2018-12-21 14:45:04', 1, 'role', 'layui-icon layui-icon-haoyouqingqiu', '/dcrud/role/', 2, 'menu', 2, 'Role Manager', '角色管理'),(6, '2018-12-24 16:24:36', '2018-12-24 16:24:36', 0, 'resource', 'layui-icon layui-icon-yemian1', '/dcrud/resource/', 3, 'menu', 2, 'Resource Manager', '资源管理'),(7, '2018-12-24 17:27:16', '2018-12-24 17:35:26', 4, '业务管理', '2', '1231223', 3, 'menu', NULL, 'Business Manager2', '业务管理2'),(8, '2018-12-24 17:29:10', '2018-12-24 17:30:23', 1, 'yewuguanli2', 'layui-icon layui-icon-cart-simple', 'yewuguanli2', 1, 'menu', 7, 'yewuguanli2', 'yewuguanli2');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `frame2`.`sys_role` WRITE;
DELETE FROM `frame2`.`sys_role`;
INSERT INTO `frame2`.`sys_role` (`id`,`create_date_time`,`update_date_time`,`version`,`code`,`description`,`name`) VALUES (1, '2018-12-21 14:45:04', '2018-12-25 17:19:11', 2, 'admin', 'administrator', 'admin'),(2, '2018-12-21 14:45:04', '2018-12-26 11:19:54', 5, 'operator2', 'operator222', 'operator2'),(4, '2018-12-21 16:56:29', '2018-12-21 16:56:29', 0, 'role111', 'role111', 'role111'),(8, '2018-12-25 17:19:22', '2018-12-25 17:19:22', 0, '测试权限', '测试权限', '测试权限');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `frame2`.`sys_role_resources` WRITE;
DELETE FROM `frame2`.`sys_role_resources`;
INSERT INTO `frame2`.`sys_role_resources` (`role_entity_id`,`resources_id`) VALUES (1, 1),(2, 1),(8, 1),(1, 2),(2, 2),(8, 2),(1, 3),(2, 3),(8, 3),(1, 4),(2, 4),(8, 4),(1, 6),(2, 6),(8, 6),(1, 7),(1, 8);
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `frame2`.`sys_user` WRITE;
DELETE FROM `frame2`.`sys_user`;
INSERT INTO `frame2`.`sys_user` (`id`,`create_date_time`,`update_date_time`,`version`,`nick_name`,`password`,`username`) VALUES (1, '2018-04-17 08:22:32', '2018-12-24 17:12:36', 4, '沈程辉_114a', '61771a5b192e5e4da099f6b26743097f17994953', 'admin_514'),(2, '2018-04-17 08:22:34', '2018-04-17 08:22:34', 0, '沈程辉_657', '61771a5b192e5e4da099f6b26743097f17994953', 'admin_1066'),(3, '2018-04-24 16:57:35', '2018-04-24 16:57:38', 0, 'DC', '61771a5b192e5e4da099f6b26743097f17994953', 'DC'),(12, '2018-12-05 09:37:55', '2018-12-05 10:37:44', 11, 'admin1', '931bfc9c02a1666578cb060182c40e9d48678a17', 'admin'),(13, '2018-12-21 16:56:40', '2018-12-26 11:20:11', 1, 'role1', 'fc4d649bfa3b44dd6292c6d3cd50794a714ee953', 'role1');
UNLOCK TABLES;
COMMIT;
BEGIN;
LOCK TABLES `frame2`.`sys_user_roles` WRITE;
DELETE FROM `frame2`.`sys_user_roles`;
INSERT INTO `frame2`.`sys_user_roles` (`user_entity_id`,`roles_id`) VALUES (1, 1),(3, 1),(12, 1),(1, 2),(12, 2),(13, 2);
UNLOCK TABLES;
COMMIT;
