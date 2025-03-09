package br.com.zup.SkillForge.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

    @Configuration
    public class AppConfig {

        @Bean
        public DataSource dataSource() {
            Dotenv dotenv = Dotenv.load();

            return DataSourceBuilder.create()
                    .driverClassName("org.postgresql.Driver")
                    .url("jdbc:postgresql://" + dotenv.get("DB_HOST") + ":" + dotenv.get("DB_PORT") + "/" + dotenv.get("DB_NAME"))
                    .username(dotenv.get("DB_USER"))
                    .password(dotenv.get("DB_PASSWORD"))
                    .build();
        }
    }
