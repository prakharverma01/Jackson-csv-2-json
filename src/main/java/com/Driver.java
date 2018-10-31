package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.Controller.CSVController;

@SpringBootApplication
public class Driver implements CommandLineRunner{

		@Autowired
		private CSVController controller;
		
		public static void main(String[] args) {
			SpringApplication.run(Driver.class, args);
		}
	
		@Override
		public void run(String... args) throws Exception {
			controller.startTask();
		}
}
