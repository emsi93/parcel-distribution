package com.parcel.distribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories("com.parcel.distribution.db.repository")
@Configuration
@EnableAutoConfiguration
@ComponentScan({"com.parcel.distribution"})
@EntityScan("com.parcel.distribution.db.entity")
@EnableScheduling
public class ParcelDistributionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelDistributionApplication.class, args);
	}
}
