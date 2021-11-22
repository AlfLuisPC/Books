FROM openjdk:8
EXPOSE 8080
ADD /books-1.0.0.war books-1.0.0.war
ENTRYPOINT ["java", "-jar", "books-1.0.0.war"]
