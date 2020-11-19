package com.recruitment.challenge.utilities.settings;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.recruitment.challenge.entities.Role;
import com.recruitment.challenge.entities.User;
import com.recruitment.challenge.repositories.RoleRepository;
import com.recruitment.challenge.repositories.UserRepository;
import com.recruitment.challenge.security.JwtUtil;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerSettings {

	private static final String SWAGGER_EMAIL = "test@swagger.com";
	private static final String SWAGGER_USERNAME = "Swagger test";
	private static final String SWAGGER_PASSWORD = String.valueOf(RandomUtils.nextLong(10000000, 99999999));
	
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.recruitment.challenge.controllers"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Recruitment challenge")
				.description("Recruitment challenge docs")
				.version("1.0")
				.build();
	}
	
	@Bean("swaggerUserInit")
	@DependsOn("rolesInit")
	public void swaggerUserInit() {
		Optional<User> userOptional = userRepository.findByEmail(SWAGGER_EMAIL);
		
		if(!userOptional.isPresent()) {
			Optional<Role> role	 = roleRepository.findByName(RolesSettings.RoleEnum.ROLE_USER.name()); 
			userRepository.save(new User()
					.setEmail(SWAGGER_EMAIL)
					.setUsername(SWAGGER_USERNAME)
					.setPassword(SWAGGER_PASSWORD)
					.addRole(role.get())
			);
		}
	}
	
	@Bean
	@DependsOn("swaggerUserInit")
	public SecurityConfiguration security() {
		String token = JwtUtil.JWT_PROVIDER;
		
		try {
			User user = (User) userDetailsService.loadUserByUsername(SWAGGER_EMAIL);
			Set<String> roles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
					.collect(Collectors.toSet());
			token += jwtUtil.generateToken(user.getEmail(), roles);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new SecurityConfiguration
				(null, null, null, null, token, ApiKeyVehicle.HEADER, HttpHeaders.AUTHORIZATION, ",");
	}
	
}
