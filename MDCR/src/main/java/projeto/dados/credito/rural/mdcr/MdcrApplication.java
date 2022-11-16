package projeto.dados.credito.rural.mdcr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class MdcrApplication {
	public static void main(String[] args) {
		SpringApplication.run(MdcrApplication.class, args);
	}

}
