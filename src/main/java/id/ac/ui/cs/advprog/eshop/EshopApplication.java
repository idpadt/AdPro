package id.ac.ui.cs.advprog.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

@SpringBootApplication
public class EshopApplication {

	private static final Logger log = LoggerFactory.getLogger(EshopApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EshopApplication.class, args);
		log.info("Application started");
	}

}
