package com.maruf.kalah;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Collections;

@Slf4j
@SpringBootApplication
@EnableScheduling
public class Application {

	private final Environment env;
	public Application(Environment env) {
		this.env = env;
	}

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Application.class);
		app.setDefaultProperties(Collections.singletonMap("spring.profiles.default", "dev"));
		Environment env = app.run(args).getEnvironment();
		log.info("\n----------------------------------------------------------\n\t" +
						"Application: \t'{}' \n\t" +
						"Version: \t'{}' \n\t" +
						"Access URL: \thttp://localhost:{}\n\t" +
						"Profile(s): \t{}" +
				 "\n----------------------------------------------------------",
				env.getProperty("app.name"),
				env.getProperty("app.version"),
				env.getProperty("server.port"),
				env.getActiveProfiles()
		);

	}
}
