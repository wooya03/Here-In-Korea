package kr.kro.hereinkorea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HereinkoreaApplication {
	public static void main(String[] args) {

		SpringApplication.run(HereinkoreaApplication.class, args);


	}
}
