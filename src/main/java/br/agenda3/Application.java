package br.agenda3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages = { "br.agenda3" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CacheManager cacheManager() {
	    return new org.springframework.cache.concurrent.ConcurrentMapCacheManager();
	}
}