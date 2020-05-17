package com.inspire12.homepage;

import com.inspire12.homepage.storage.StorageProperties;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class HomepageApplication implements CommandLineRunner {

	public static void main(String[] args){
        SpringApplication application = new SpringApplication(HomepageApplication.class);
        application.addListeners(new ApplicationPidFileWriter());
        application.run(args);
	}

    @Override
    public void run(String... args) throws Exception {

    }


}

