package com.postal.addressdao;

import com.postal.addressdao.repository.impl.AddressRepositoryImpl;
import com.postal.model.enums.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@ComponentScan({"com.postal.addressdao.accessor", "com.postal.addressdao.repository",
        "com.postal.addressdao.configuration"})
@EnableMongoRepositories("com.postal.addressdao.repository")
@EnableAutoConfiguration
public class AddressMongoDBApplication implements CommandLineRunner {

    @Autowired
    AddressRepositoryImpl addressRepository;

    public static void main(final String[] args) {
        SpringApplication.run(AddressMongoDBApplication.class, args);
    }

    @Override
    public void run(final String... args) throws Exception {
        final Map<Field, String> fieldStringMap = new HashMap<>();
        fieldStringMap.put(Field.STREET, "15a Street");
        addressRepository.findAddressByCountry("United Arab Emirates", fieldStringMap);
        System.exit(0);
    }
}