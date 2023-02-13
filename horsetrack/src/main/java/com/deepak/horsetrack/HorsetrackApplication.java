package com.deepak.horsetrack;


import com.deepak.horsetrack.controller.DisplayControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication //(exclude = {DataSourceAutoConfiguration.class })
public class HorsetrackApplication implements CommandLineRunner {

	@Autowired
	private ConfigurableApplicationContext context;

	@Autowired
	DisplayControl displayControl;

	public static void main(String[] args) {
		SpringApplication.run(HorsetrackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("RUN...");

		displayControl.initLoadStartupData();
		displayControl.startUpDefaultScreen();

		Scanner commandInput = new Scanner(System.in);

		while (!(displayControl.quit())) {
			displayControl.executeInputCommand(commandInput.nextLine());
		}


		System.exit(SpringApplication.exit(context));
	}
}
