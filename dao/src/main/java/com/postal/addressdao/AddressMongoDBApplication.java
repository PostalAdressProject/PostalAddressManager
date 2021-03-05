package com.postal.addressdao;

import com.postal.addressdao.accessor.AddressAccessor;
import com.postal.addressdao.exception.AddressDataAccessException;
import com.postal.apil.enums.Field;
import com.postal.apil.models.Address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
//@EnableAutoConfiguration
public class AddressMongoDBApplication {
	private static AddressAccessor addressAccessor;
//	@Autowired
//    AddressAccessor addressAccessor;

    public static void main(String args[]) throws AddressDataAccessException {
        final Map<Field, String> fieldStringMap = new HashMap<>();
		fieldStringMap.put(Field.STREET, "3a Street");
		final List<Address> addressList = addressAccessor.findAddressByCountry("United Arab Emirates", fieldStringMap);
		System.out.println(addressList.get(0).getDistrict());
    }
}

//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//@SpringBootApplication
//@ComponentScan({"com.postal.addressdao.accessor", "com.postal.addressdao.repository",
//	"com.postal.addressdao.configuration"})
//@EnableMongoRepositories("com.postal.addressdao.repository")
//@EnableAutoConfiguration
//public class AddressMongoDBApplication implements CommandLineRunner {
//	@Autowired
//	AddressAccessor addressAccessor;
//
//	public static void main(final String[] args) {
//		SpringApplication.run(AddressMongoDBApplication.class, args);
//	}
//
//	@Override
//	public void run(final String... args) throws Exception {
//		final Map<Field, String> fieldStringMap = new HashMap<>();
//		fieldStringMap.put(Field.STREET, "3a Street");
//		//fieldStringMap.put(Field.STREET, "39a Street");
//		//fieldStringMap.put(Field.NUMBER, "41903 90378");
//		final List<Address> addressList = addressAccessor.findAddressByCountry("United Arab Emirates", fieldStringMap);
////		System.out.println(addressList.get(0).getRegion());
//		System.out.println(addressList.get(0).getDistrict());
//		System.exit(0);
//	}
//}
