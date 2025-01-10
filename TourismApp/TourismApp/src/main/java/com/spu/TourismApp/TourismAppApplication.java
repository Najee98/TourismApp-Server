package com.spu.TourismApp;

import com.spu.TourismApp.Services.AuthenticationService;
import com.spu.TourismApp.Shared.Dto.Authentication.RegisterRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.spu.TourismApp.Models.Utils.Role.ADMIN;

@SpringBootApplication
public class TourismAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourismAppApplication.class, args);
	}


//	@Bean
//	public CommandLineRunner commandLineRunner(
//			AuthenticationService service
//	){
//		return args -> {
//			var admin = RegisterRequest.builder()
//					.firstName("admin")
//					.lastName("admin")
//					.email("admin@admin.com")
//					.password("admin")
//					.role(ADMIN)
//					.build();
//
//			System.out.println("Access token: " + service.register(admin).getToken());
//		};
//	}
}
