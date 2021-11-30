package com.example.reservationsanjose.ReservationSanJose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(exclude = {ErrorMvcAutoConfiguration.class})
public class ReservationSanJoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationSanJoseApplication.class, args);
	}


}
