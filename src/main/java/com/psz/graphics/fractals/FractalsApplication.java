package com.psz.graphics.fractals;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class FractalsApplication {

		public static void main(String[] args) {
			new SpringApplicationBuilder(FractalsApplication.class)
			.headless(false)
			.web(WebApplicationType.NONE)
			.run(args);
	}

}
