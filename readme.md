# frame2 crud framework

## 定义
JavaEE 页面编码框架。

## 基于
* spring-boot
* spring-data-jpa
* hibernate
* thymeleaf
* ....

## 代码编写目标
简化页面CRUD编码，并基于原frame框架新增分布式的支持。

##代码结构

* dcrud: 业务层代码模板,应当自带一套rbac的功能模板。
    * dcrud-model  <---- frame2-model 
    * dcrud-dao   <---- frame2-data-jpa ,  dcrud-model
    * dcrud-service <---- dcrud-dao
    * dcrud-web <---- dcrud-service
* frame2: 核心代码，包含 1. 对spring-data-jpa增强组件 2. 自定义模块化的视图渲染逻辑
    * frame2-model
    * frame2-data-jpa  <--- frame2-model
