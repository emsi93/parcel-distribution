package com.parcel.distribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"com.parcel.distribution"})
@SpringBootApplication
public class ParcelDistributionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParcelDistributionApplication.class, args);
	}
}
