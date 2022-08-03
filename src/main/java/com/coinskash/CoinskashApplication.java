package com.coinskash;

import com.coinskash.model.AppUser;
import com.coinskash.model.Roles;
import com.coinskash.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@Configuration
@EnableJpaRepositories
@EnableScheduling
public class CoinskashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinskashApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Roles(null, "ROLE_USER"));
			userService.saveRole(new Roles(null, "ROLE_MANAGER"));
			userService.saveRole(new Roles(null, "ROLE_ADMIN"));
			userService.saveRole(new Roles(null, "ROLE_SUPER_ADMIN"));

			userService.saveUser(new AppUser(null, "John", "Travolta", "john@gmail.com", "Nigeria","09069167788","1234", true,null, new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Will", "Smith", "will@will.com", "Niger", "09011111111","1234",true,null, new ArrayList<>()));
			userService.saveUser(new AppUser(null, "Jim", "Carry", "jim@gmail.com","Nigeria","09022321111", "1234",true,null, new ArrayList<>()));

			userService.addRoleToUser("john@gmail.com", "ROLE_USER");
			userService.addRoleToUser("will@will.com", "ROLE_MANAGER");
			userService.addRoleToUser("jim@gmail.com", "ROLE_ADMIN");
//
		};
	}
}
