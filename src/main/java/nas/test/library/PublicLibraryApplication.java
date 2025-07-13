package nas.test.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PublicLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicLibraryApplication.class, args);
	}

}
