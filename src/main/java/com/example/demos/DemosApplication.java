package com.example.demos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.example.demos.role.Role;
import com.example.demos.role.RoleRepository;

@SpringBootApplication
@EnableJpaAuditing
@EnableAsync
public class DemosApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemosApplication.class, args);
		System.out.println("hello world");
	}

	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository) {
		return args -> {
			if (roleRepository.findByName("USER").isEmpty()) {
				roleRepository.save(Role.builder().name("USER").build());
			}
		};
	}
}
