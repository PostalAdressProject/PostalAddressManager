package com.postal.microservice.composite.address;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.emptyList;


@SpringBootApplication
public class Application {

    /**
     * TBD : where are these coming from
     */
//    @Value("${api.common.version}")           String apiVersion;
//    @Value("${api.common.title}")             String apiTitle;
//    @Value("${api.common.description}")       String apiDescription;
//    @Value("${api.common.termsOfServiceUrl}") String apiTermsOfServiceUrl;
//    @Value("${api.common.license}")           String apiLicense;
//    @Value("${api.common.licenseUrl}")        String apiLicenseUrl;
//    @Value("${api.common.contact.name}")      String apiContactName;
//    @Value("${api.common.contact.url}")       String apiContactUrl;
//    @Value("${api.common.contact.email}")     String apiContactEmail;

    /**
     * Will exposed on $HOST:$PORT/swagger-ui.html
     *
     * @return
     */
    @Bean
    public Docket apiDocumentation() {

        String apiTitle = "ergrer";
        String apiVersion="34";
        String apiTermsOfServiceUrl = "34t34t43";
        String apiDescription="3rfq3ef3";
        String apiContactName="altanai";
        String apiContactUrl= "asomething";
        String apiContactEmail="abisht@seattleu.edu";
        String apiLicense = "GPL";
        String apiLicenseUrl = "ergewrgeger";

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("se.magnus.microservices.composite.product"))
                .paths(PathSelectors.any())
                .build()
                .globalResponseMessage(RequestMethod.POST, emptyList())
                .globalResponseMessage(RequestMethod.GET, emptyList())
                .globalResponseMessage(RequestMethod.DELETE, emptyList())
                .apiInfo(new ApiInfo(
                        apiTitle,
                        apiDescription,
                        apiVersion,
                        apiTermsOfServiceUrl,
                        new Contact(apiContactName, apiContactUrl, apiContactEmail),
                        apiLicense,
                        apiLicenseUrl,
                        emptyList()
                ));
    }

    @Bean
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        final WebClient.Builder builder = WebClient.builder();
        return builder;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//        return args -> {
//
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            Arrays.sort(beanNames);
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }

}