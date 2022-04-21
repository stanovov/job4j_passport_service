package ru.job4j.mailservice;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.gson.adapter.LocalDateDeserializerAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class MailServiceApplication {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDate.class, new LocalDateDeserializerAdapter())
                .create();
    }


    public static void main(String[] args) {
        SpringApplication.run(MailServiceApplication.class, args);
    }

}
