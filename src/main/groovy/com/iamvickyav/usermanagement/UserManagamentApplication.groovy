package com.iamvickyav.usermanagement

import com.fasterxml.jackson.databind.ObjectMapper
import com.iamvickyav.usermanagement.filter.SecurityFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class UserManagamentApplication {

	@Autowired
	SecurityFilter securityFilter

	static void main(String[] args) {
		SpringApplication.run(UserManagamentApplication, args)
	}

	@Bean
	FilterRegistrationBean filterRegistrationBean() {
		final FilterRegistrationBean registrationBean = new FilterRegistrationBean()
		registrationBean.setFilter(securityFilter )
		registrationBean.addUrlPatterns("/secure/*")

		return registrationBean
	}

	@Bean
	ObjectMapper objectMapper(){
		return  new ObjectMapper()
	}
}
