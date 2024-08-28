package com.meet.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.meet.repository.SchedulerRepository;

@SpringBootApplication(scanBasePackages = "com.meet.*")
@EnableJpaRepositories(basePackageClasses = SchedulerRepository.class)
@EntityScan(basePackages = "com.meet.entity")
public class MeetingSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeetingSchedulerApplication.class, args);
	}

}
