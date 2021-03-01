package com.postal.address;

import com.postal.address.repository.impl.AddressRepositoryImpl;
import com.postal.api.enums.Field;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.postal.address.accessor", "com.postal.address.repository",
	"com.postal.address.configuration"})
@EnableMongoRepositories("com.postal.address.repository")
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
