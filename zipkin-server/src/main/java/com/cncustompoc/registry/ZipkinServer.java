package com.cncustompoc.registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ZipkinServer {
	private static Logger logger= LoggerFactory.getLogger(ZipkinServer.class);
	public static void main(String[] args) {
		SpringApplication.run(ZipkinServer.class, args);
		logger.info("===ZipkinServer started===");
	}
}