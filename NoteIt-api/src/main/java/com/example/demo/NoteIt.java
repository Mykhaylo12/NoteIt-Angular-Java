package com.example.demo;

import com.example.demo.db.DbSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class NoteIt extends SpringBootServletInitializer {

    private final DbSeeder dbSeeder;

    public NoteIt(DbSeeder dbSeeder) {
        this.dbSeeder = dbSeeder;
    }

    public static void main(String[] args) {
        SpringApplication.run(NoteIt.class, args);
    }

    @EventListener
    public void seed(ContextRefreshedEvent event) {
        dbSeeder.seedUserRoles();
        dbSeeder.seedUserWithUserRole();
        dbSeeder.seedUserWithAdminRole();
        dbSeeder.seedUserWithAdminAndUserRoles();
    }
}
// TODO: 17.09.2020  change password in mysql and change properties