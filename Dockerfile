#FROM openjdk:17
#COPY target/mysqldb-0.0.1-SNAPSHOT.jar /app.jar
#EXPOSE 8080
#CMD ["java", "-jar", "/app.jar"]
#
#FROM openjdk:17
#ADD target/sb-app.jar app.jar
#ENTRYPOINT ["java", "-jar", "app.jar"]

#FROM openjdk:17
##COPY . .
#RUN #mvn clean package -Pprod -DskipTests
#FROM openjdk:17-jdk-slim
#ADD target/sb-app.jar app.jar
#COPY --from=build /target/health-management-system-0.0.1-SNAPSHOT.jar java-rs.jar
#EXPOSE 8881
#ENTRYPOINT ["java","-jar","app.jar"]
FROM openjdk:17
#ADD target/sb-app.jar app.jar
ADD target/sb-app.jar sb-app.jar
EXPOSE 5000
ENTRYPOINT ["java", "-jar/", "sb-app.jar"]