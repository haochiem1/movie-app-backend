package com.insert.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(scanBasePackages={"com.insert.register"})
//@ComponentScan(basePackages = {"com.insert.register"})
public class RegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
	}

}

//(scanBasePackages={"com.insert.register.UserService"})