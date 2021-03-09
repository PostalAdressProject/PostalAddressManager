package com.postal.formatdao;

import com.postal.formatdao.accessor.FormatAccessor;

import com.postal.model.models.PostalFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@SpringBootApplication
@ComponentScan({"com.postal.formatdao.accessor", "com.postal.formatdao.repository",
	"com.postal.formatdao.configuration"})
@EnableMongoRepositories("com.postal.format.repository")
@EnableAutoConfiguration
public class FormatMongoDBApplication implements CommandLineRunner {

	@Autowired
	FormatAccessor formatAccessor;

	public static void main(final String[] args) {
		SpringApplication.run(FormatMongoDBApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		final PostalFormat format = formatAccessor.getFormat("australia");

		final List<PostalFormat> postalFormats = formatAccessor.getFormats();
		System.exit(0);
	}
}
