package com.postal.addressdao;

import com.postal.addressdao.accessor.AddressAccessor;
import com.postal.model.enums.Field;
import com.postal.model.models.Address;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan({"com.postal.addressdao.accessor", "com.postal.addressdao.repository",
	"com.postal.addressdao.configuration"})
@EnableMongoRepositories("com.postal.addressdao.repository")
//@EnableAutoConfiguration
public class AddressMongoDBApplication implements CommandLineRunner {

	@Autowired
	AddressAccessor addressAccessor;

	public static void main(final String[] args) {
		SpringApplication.run(AddressMongoDBApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		final Map<Field, String> fieldStringMap = new HashMap<>();
		fieldStringMap.put(Field.STREET, "EKLUTNA VILLAGE RD");
		//fieldStringMap.put(Field.NUMBER, "41903");
		final List<Address> addressList = addressAccessor
			.findAddressAcrossCountries(fieldStringMap);
		System.exit(0);
	}
}
