package com.weng.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.weng.*.mapper")
@ComponentScan(
		basePackages = "com.weng.system",
		excludeFilters = @ComponentScan.Filter(
				type = FilterType.REGEX,
				pattern = "com\\.weng\\.system\\.(lab|script)\\..*"
		)
)
public class JavaUtilsApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaUtilsApplication.class, args);
	}

}
