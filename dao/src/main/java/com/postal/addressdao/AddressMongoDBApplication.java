package com.postal.addressdao;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@ComponentScan({"com.postal.addressdao.accessor", "com.postal.addressdao.repository",
	"com.postal.addressdao.configuration"})
@EnableMongoRepositories("com.postal.addressdao.repository")

@EnableAutoConfiguration
public class AddressMongoDBApplication {
//    @Autowired
//    AddressAccessor addressAccessor ; //doesnt work for som reason
//
//    public void main2(String args[]) throws AddressDataAccessException {
//
//        final Map<Field, String> fieldStringMap = new HashMap<>();
//		fieldStringMap.put(Field.STREET, "3a Street");
//		//fieldStringMap.put(Field.STREET, "39a Street");
//		//fieldStringMap.put(Field.NUMBER, "41903 90378");
//		final List<Address> addressList = addressAccessor.findAddressByCountry("United Arab Emirates", fieldStringMap);
//		System.out.println(addressList.get(0).getRegion());
//		System.out.println(addressList.get(0).getDistrict());
//
//		final Map<Field, String> fieldStringMap2 = new HashMap<>();
//		fieldStringMap.put(Field.CITY, "Kennewick");
//		final List<Address> addressList2 = addressAccessor.findAddressByCountry("United States", fieldStringMap);
//		System.out.println(addressList2.get(0).getDistrict());
//
//    }

//
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
//		fieldStringMap.put(Field.STREET, "EKLUTNA VILLAGE RD");
//		//fieldStringMap.put(Field.NUMBER, "41903");
//		final List<Address> addressList = addressAccessor
//			.findAddressAcrossAllCountries(fieldStringMap);
//		System.exit(0);
//	}

}


//import com.postal.addressdao.accessor.AddressAccessor;
//import com.postal.model.enums.Field;
//import com.postal.model.models.Address;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//@SpringBootApplication
//@ComponentScan({"com.postal.addressdao.accessor", "com.postal.addressdao.repository",
//	"com.postal.addressdao.configuration"})
//@EnableMongoRepositories("com.postal.addressdao.repository")
//@EnableAutoConfiguration
//public class AddressMongoDBApplication implements CommandLineRunner {
//	@Autowired
//	AddressAccessor addressAccessor;
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
