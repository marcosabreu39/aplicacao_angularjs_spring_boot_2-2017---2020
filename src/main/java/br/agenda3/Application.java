package br.agenda3;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "br.agenda3" })
@EnableJpaRepositories("br.agenda3.repository")
public class Application {
	
//	private static final Logger LOGGER=LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CacheManager cacheManager() {
	    return new org.springframework.cache.concurrent.ConcurrentMapCacheManager();
	}
}