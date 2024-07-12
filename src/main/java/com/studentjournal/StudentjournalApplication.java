package com.studentjournal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class StudentjournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentjournalApplication.class, args);
    }

}
