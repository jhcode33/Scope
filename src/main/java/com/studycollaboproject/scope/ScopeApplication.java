package com.studycollaboproject.scope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@PropertySource("/config.properties")
@Profile(value = "prod")
@EnableJpaAuditing
@SpringBootApplication
public class ScopeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScopeApplication.class, args);
    }


    @PostConstruct
    public void before() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

}

