package com.kaistsw.arch.subscribe;

import com.kaistsw.arch.subscribe.config.SpringCloudStreamBindings;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.boot.WebApplicationType;

@SpringBootApplication
@EnableBinding(SpringCloudStreamBindings.class)
public class SubscribeApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SubscribeApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

}
