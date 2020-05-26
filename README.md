# spring-demo
spring web mvc demo

- 项目描述

  一个由maven构建的spring web mvc demo项目

- 构建

  mvn -Dmaven.test.skip=true clean package
  
- 部署

  java -jar demo-0.0.1-SNAPSHOT.jar
  
- URL页面说明
 
  主页
  - index：http://localhost:8080/

  信息展示（获取系统和应用信息）
  - show: http://localhost:8080/show

  健康检查
  - health：http://localhost:8080/health
