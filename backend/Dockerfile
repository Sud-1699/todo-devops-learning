FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN chmod +x mvnw

RUN ./mvnw dependency:go-offline -B

COPY src ./src

RUN ./mvnw clean package -DskipTests -B


FROM eclipse-temurin:21-jre

WORKDIR /app

RUN useradd --system --create-home spring

COPY --from=builder /app/target/*.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 9100

ENV DB_TIMEZONE=Asia/Kolkata

ENTRYPOINT ["java", "-Duser.timezone=${DB_TIMEZONE}", "-jar", "app.jar"]