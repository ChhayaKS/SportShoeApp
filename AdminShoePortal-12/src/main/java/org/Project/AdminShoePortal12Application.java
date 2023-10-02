package org.Project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class AdminShoePortal12Application {

	public static void main(String[] args) {
		SpringApplication.run(AdminShoePortal12Application.class, args);
	}

}
