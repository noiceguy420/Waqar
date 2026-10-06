# ---------- Stage 1: Build ----------
FROM eclipse-temurin:21-jdk-alpine AS build

WORKDIR /app

# Copy only the files needed to resolve dependencies first — this layer
# is cached and only re-runs when pom.xml itself changes, not on every
# source code edit, which is the single biggest speed win in CI.
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Now copy source and build — only this layer re-runs on code changes
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ---------- Stage 2: Runtime ----------
FROM eclipse-temurin:21-jre-alpine AS runtime

# Run as non-root — never run a production container as root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

WORKDIR /app

# Copy only the built jar from the build stage — final image has no
# Maven, no source code, no build cache, just the JRE + jar
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

# Respect container memory limits (cgroup-aware JVM, default since Java 10+,
# but explicit flags below help in constrained environments) and allow
# runtime JVM tuning via an env var without rebuilding the image
ENV JAVA_OPTS=""
ENV JWT_SECRET="lkG1UAHvkVgPlzwHXGyHPHiIhfuow69YQgRPQTLBm6lPde/PV0AmdnOKiLi4KiOSipNJHbr5K9QpkKr7K6Bd3A==" #only for development no security risk
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]

#build command (from project root): docker build -t waqar-api:latest ./
#run command: docker run -p 8080:8080 waqar-api:latest