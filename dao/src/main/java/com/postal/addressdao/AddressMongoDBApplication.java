package com.postal.addressdao;

public class AddressMongoDBApplication {
}

//@SpringBootApplication
//@ComponentScan({"com.postal.addressdao.accessor", "com.postal.addressdao.repository",
//	"com.postal.addressdao.configuration"})
//@EnableMongoRepositories("com.postal.addressdao.repository")
////@EnableAutoConfiguration
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
//		final List<Address> addressList = addressAccessor
//			.findAddressByCountry("United Arab Emirates", fieldStringMap);
////		System.out.println(addressList.get(0).getRegion());
//		System.out.println(addressList.get(0).toString());
//		System.exit(0);
//	}
//}
