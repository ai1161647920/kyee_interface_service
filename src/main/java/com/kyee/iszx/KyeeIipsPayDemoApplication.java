package com.kyee.iszx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.kyee.iszx.mapper")
@SpringBootApplication
public class KyeeIipsPayDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KyeeIipsPayDemoApplication.class, args);
	}

}
