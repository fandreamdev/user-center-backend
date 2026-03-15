# 第一阶段：构建 (Build)
FROM maven:3.8.5-openjdk-17-slim AS build
WORKDIR /app

# 1. 拷贝 pom.xml 并下载依赖（利用 Docker 缓存层，只要 pom 不变就不需重新下载）
COPY pom.xml .
RUN mvn dependency:go-offline

# 2. 拷贝源码并打包
COPY src ./src
RUN mvn clean package -DskipTests

# 第二阶段：运行 (Run)
FROM openjdk:17-jdk-slim
WORKDIR /app

# 3. 从构建阶段拷贝生成的 jar 包（注意打包后的文件名，通常是 target/*.jar）
COPY --from=build /app/target/*.jar app.jar

# 4. 设置时区（可选，解决日志时间问题）
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# 5. 暴露端口
EXPOSE 8080

# 6. 启动程序
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=prod"]