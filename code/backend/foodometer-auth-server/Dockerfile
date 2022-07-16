FROM openjdk:18.0

ARG TARGET_JAR_FILE=target/${project.artifactId}-${project.version}.jar

WORKDIR /opt/app/food-o-meter

COPY ${TARGET_JAR_FILE} ${project.artifactId}.jar

ENTRYPOINT ["java", "-jar", "${project.artifactId}.jar"]

EXPOSE 9090