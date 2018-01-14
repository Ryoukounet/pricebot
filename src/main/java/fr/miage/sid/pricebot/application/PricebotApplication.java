package fr.miage.sid.pricebot.application;

import fr.miage.sid.pricebot.service.PriceApi;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
@ComponentScan(basePackages="fr.miage.sid.pricebot")
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




