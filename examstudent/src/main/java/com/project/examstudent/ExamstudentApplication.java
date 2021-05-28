package com.project.examstudent;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan({ "com.project.examstudent" })
@SpringBootApplication
@EnableFeignClients
public class ExamstudentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamstudentApplication.class, args);
	}

}
