docker images --filter=reference="library-system-library-system-main" --format "{{.Repository}}:{{.Tag}}" | xargs docker rmi
rm ~/Documents/library-system/build/libs/library-system-0.0.1-SNAPSHOT.jar
./gradlew bootjar
docker compose up -d