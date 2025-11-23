package com.locacaomidia.locacaoMidia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.locacaomidia", // 💡 Escaneia o pacote pai E todos os seus sub-pacotes
})
public class LocacaoMidiaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocacaoMidiaApplication.class, args);
	}

}
