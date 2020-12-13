docker build --build-arg JAR_FILE=target/*.jar -t rss-feed-reader .
docker run -p 8080:8080 rss-feed-reader
