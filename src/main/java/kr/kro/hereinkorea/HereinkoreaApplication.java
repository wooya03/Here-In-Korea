package kr.kro.hereinkorea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class HereinkoreaApplication {
	public static void main(String[] args) {

		SpringApplication.run(HereinkoreaApplication.class, args);


	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
