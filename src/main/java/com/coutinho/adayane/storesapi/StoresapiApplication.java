package com.coutinho.adayane.storesapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoresapiApplication {

    private static Logger logger = LoggerFactory.getLogger(StoresapiApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a API de consulta de Lojas próximas por Faixa de Cep.");
	    SpringApplication.run(StoresapiApplication.class, args);
	}

}
