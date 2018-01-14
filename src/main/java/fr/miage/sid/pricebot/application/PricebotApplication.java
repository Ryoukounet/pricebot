package fr.miage.sid.pricebot.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan
@SpringBootApplication
public class PricebotApplication  extends SpringBootServletInitializer  {

	public static void main(String[] args) {
		SpringApplication.run(PricebotApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(PricebotApplication.class);
	}

	private static Class<PricebotApplication> applicationClass = PricebotApplication.class;
}
@RestController
class hellocontroller{
	@RequestMapping("/hello/{name}")
	public String hello(@PathVariable String name) {
		// System.out.println("cooooo");
		return name;
	}
}



