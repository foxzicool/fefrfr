# 使用 Java 8 基础映像
FROM openjdk:8-jdk-alpine

# 复制 JAR 文件
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# 开放端口
EXPOSE 8081

# 启动应用
ENTRYPOINT ["java","-jar","/app.jar"]
