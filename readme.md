# frame2 crud framework

## 定义
JavaEE 页面编码框架。

## 基于
* spring-boot
* spring-data-jpa
* hibernate
* thymeleaf
* sitemesh3
* ....

## 代码编写目标
简化页面CRUD编码，并基于原frame框架新增分布式的支持。

## 特性

1. 提供增强jpa的解析查询类为分页条件查询语句的功能
2. 提供Freemarker嵌套化组件化的视图渲染框架以及开发流程
3. 提供默认的快速生成增删改查视图的方法
4. 使用layui作为默认的前端样式框架
5. 使用sitemesh装饰器模式提供菜单页面和业务页面隔离

##代码结构

* dcrud: 业务层代码模板,应当自带一套rbac的功能模板。
    * dcrud-model  <---- frame2-model -- 业务代码
    * dcrud-dao   <---- frame2-data-jpa ,  dcrud-model -- 业务代码
    * dcrud-service <---- dcrud-dao --业务代码
    * dcrud-web <---- dcrud-service,dcrud-web-support --业务代码
    * dcrud-web-support <---- frame2-views -- 对frame2-views增强，并适配前端视图框架 layui，框架性代码
* frame2: 核心代码，包含 1. 对spring-data-jpa增强组件 2. 自定义模块化的视图渲染逻辑
    * frame2-common -- 工具类
    * frame2-model
    * frame2-data-jpa  <--- frame2-model
    * frame2-views -- 核心 提供Freemarker嵌套视图解析，核心类为Frame2View，FreemarkerView
