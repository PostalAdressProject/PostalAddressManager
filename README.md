# Postal Address Manager 

MAde with spring boot on spring framework 

## Requirnments

### 1. Java 
```shell script
> java -version
openjdk version "11.0.9.1" 2020-11-04
OpenJDK Runtime Environment (build 11.0.9.1+1-Ubuntu-0ubuntu1.18.04)
OpenJDK 64-Bit Server VM (build 11.0.9.1+1-Ubuntu-0ubuntu1.18.04, mixed mode, sharing)

```

### 2. Spring boot cli 
```shell script
wget https://repo.spring.io/release/org/springframework/boot/spring-boot-cli/2.4.3/spring-boot-cli-2.4.3-bin.tar.gz
tar -xvf spring-boot-cli-2.4.3-bin.tar.gz
cd spring-2.4.3/bin
```
https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-installing-the-cli

Add to path
```shell script
export PATH="$PATH:/home/altanai/Documents/architecture/spring-2.4.3/bin"
```
check for version 
```shell script
spring --version
Spring CLI v2.4.3
```

### 3. spring intializer 
https://start.spring.io/
![screenshot spring initilaizer](screenshots/Screenshot%20from%202021-02-19%2011-24-26.png)


### 4. graddle 

To install graddle SDKMAN is a recomemndation ( https://sdkman.io/)
First install SDKMAN
```shell script
curl -s "https://get.sdkman.io" | bash
```
once install clsoe , opena  new window to install graddle 
```shell script
sdk install gradle 6.8.2
```

the build gradle wrapper for `gradlew'
```shell script
gradle wrapper
````

Reference Documentation
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/#build-image)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)


## Seed the DB 

Mongoimport and mongoDB 
```shell script
mkdir db

mongod -port 27017  -dbpath "db"

mongoimport -d "db" -c test --type json --file washington-addresses-county.geojson
```
## Run 

run the application 
```shell script
cd api_gateway

./gradlew bootRun
```


test its runing 
```shell script
$ curl localhost:8080
Greetings from Spring Boot!
```

## Debug 

**Issue 1** Broken  Spring Initializr  for spring and/or intellij
```shell script
Initializr service call failed using 'https://start.spring.io' - service returned  (unexpected 404 error)
```


More on https://spring.io/guides/gs/spring-boot/#initial

## Contact 

## License 
GPL
