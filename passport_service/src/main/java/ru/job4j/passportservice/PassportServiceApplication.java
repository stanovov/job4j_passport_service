package ru.job4j.passportservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.job4j.gson.adapter.LocalDateDeserializerAdapter;
import ru.job4j.gson.adapter.LocalDateSerializerAdapter;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.sql.DataSource;
import java.time.LocalDate;

@EnableScheduling
@SpringBootApplication
@EnableTransactionManagement
public class PassportServiceApplication {

    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializerAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializerAdapter())
                .create();
    }

    public static void main(String[] args) {
        SpringApplication.run(PassportServiceApplication.class, args);
    }

}
