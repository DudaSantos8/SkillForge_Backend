package br.com.zup.SkillForge;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SkillForgeApplication {
	public static void main(String[] args) {
		Dotenv.configure().load();
		SpringApplication.run(SkillForgeApplication.class, args);
	}
}