FROM maven:3.9.9-eclipse-temurin-22 AS build
WORKDIR /workspace

COPY pom.xml ./
RUN mvn dependency:copy-dependencies \
    -DincludeArtifactIds=h2 \
    -DskipTests \
    -DoutputDirectory=./lib \
    -B

COPY src/main/resources/db/migration/ ./migrations/
RUN cat migrations/*.sql > init.sql

FROM eclipse-temurin:22-jre-jammy AS create-db
WORKDIR /app

COPY --from=build /workspace/lib/h2-*.jar ./h2.jar
COPY --from=build /workspace/init.sql ./init.sql

RUN mkdir -p /data
RUN groupadd -g 1000 appgroup \
 && useradd -u 1000 -g appgroup appuser \
 && chown -R appuser:appgroup /data
USER appuser

RUN java -cp h2.jar org.h2.tools.RunScript \
      -url jdbc:h2:file:/data/db \
      -script init.sql

CMD ["echo", "Banco H2 criado em /data/db.mv.db"]