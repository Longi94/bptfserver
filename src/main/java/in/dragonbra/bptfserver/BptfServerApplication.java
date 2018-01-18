package in.dragonbra.bptfserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BptfServerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BptfServerApplication.class, args);
	}
}
