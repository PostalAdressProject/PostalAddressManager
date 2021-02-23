# Postal Address Manager 

Made with spring boot on spring framework 

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

### Spring fox swagger ui 

Springfox suite of java libraries are all about automating the generation of specifications for JSON APIs

### 5. graddle 

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

### 6. Reactor

Implementation of the Reactive Programming paradigm

Reactor is a fully non-blocking reactive programming foundation for the JVM, with efficient demand management.

Offers composable asynchronous sequence API Flux (for [N] elements) and Mono (for [0|1] elements) and extensively implements the Reactive Streams specification.

**Class Mono** - A Reactive Streams Publisher with basic rx operators 
that emits at most one item via the onNext signal then terminates with an onComplete signal (successful Mono, with or without value), or only emits a single onError signal (failed Mono).

**Flux** -  A Reactive Streams Publisher with basic flow operators. Has Static factories for source generation from arbitrary callbacks types.
Instance methods allows operational building, materialized on each subscription (Flux#subscribe(), ...) or multicasting operations (such as Flux#publish and Flux#publishNext).

### Swagger 

http://localhost:8080/swagger-ui/index.html


## Seed the DB 

Mongoimport and mongoDB 
```shell script
mkdir db

mongod -port 27017  -dbpath "db"

mongoimport -d "db" -c test --type json --file washington-addresses-county.geojson
```


If ur using inetllij u can add the mongo interface by connecting with DB
![mongodriver](screenshots/Screenshot%20from%202021-02-19%2013-40-56.png)
and then import db
![mongo import DB](screenshots/Screenshot%20from%202021-02-19%2013-42-33.png)

## Oauth 2.0 authorization 

request asks for a token with the "message:read" scope:

```bash
curl reader:secret@localhost:8081/oauth/token -d grant_type=password -d username=subject -d password=password
```

Which will respond with something like:

```json
{
    "access_token":"eyJhbGciOiJSUzI1NiIsI...Fhq4RIVyA4ZAkC7T1aZbKAQ",
    "token_type":"bearer",
    "expires_in":599999999,
    "scope":"message:read",
    "jti":"8a425df7-f4c9-4ca4-be12-0136c3015da0"
}
```

You can also so the same with the `writer` client:

```bash
curl writer:secret@localhost:8081/oauth/token -d grant_type=password -d username=subject -d password=password
```

## Run 

run the application 
```shell script
$ ./gradlew build && ./gradlew :application:bootRun
```

test its running 
```shell script
$  curl -v localhost:8080/address/anh
Greetings from Spring Boot!
```

## Debug 

**Issue 1** Broken  Spring Initializer  for spring and/or intellij
```shell script
Initializr service call failed using 'https://start.spring.io' - service returned  (unexpected 404 error)
```
**Solution** Run with gradelw , make sure gradle wrapper is build beforehand


**Issue 2** Security addons throws error 
```shell script
Parameter 0 of method setSecurityWebFilterChains in org.springframework.security.config.annotation.web.reactive.WebFluxSecurityConfiguration required a bean of type 'org.springframework.security.oauth2.jwt.ReactiveJwtDecoder' that could not be found.

The following candidates were found but could not be injected:
	- Bean method 'jwtDecoder' in 'ReactiveOAuth2ResourceServerJwkConfiguration.JwtConfiguration' not loaded because @ConditionalOnProperty (spring.security.oauth2.resourceserver.jwt.jwk-set-uri) did not find property 'spring.security.oauth2.resourceserver.jwt.jwk-set-uri'
	- Bean method 'jwtDecoderByIssuerUri' in 'ReactiveOAuth2ResourceServerJwkConfiguration.JwtConfiguration' not loaded because OpenID Connect Issuer URI Condition did not find issuer-uri property
	- Bean method 'jwtDecoderByPublicKeyValue' in 'ReactiveOAuth2ResourceServerJwkConfiguration.JwtConfiguration' not loaded because Public Key Value Condition did not find public-key-location property
```
**solution** 


## Reference Documentation

**Gradle**
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/gradle-plugin/reference/html/#build-image)
* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

**Spring**
- [Spring REST](https://spring.io/guides/gs/rest-service/)
- [Spring Boot](https://spring.io/guides/gs/spring-boot/#initial)
- [Spring MVC Annotations](https://www.baeldung.com/spring-mvc-annotations)
- [HTTP status](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/HttpStatus.html)

**Lombok**
- [Autogeneration using Lombok](https://projectlombok.org/features/constructor)

**Reactor**
- [Reactor](https://projectreactor.io/docs/core/release/reference)
- [Reactor Repo](https://github.com/reactor/reactor-core)

**Swagger**
- https://springfox.github.io/springfox/docs/snapshot/#springfox-swagger-ui

## Contact 
- abisht@seattleu.edu

## License 
GPL
