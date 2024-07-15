package com.studentjournal;

import com.studentjournal.entities.Student;
import com.studentjournal.entities.User;
import com.studentjournal.repositories.StudentRepository;
import com.studentjournal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.UUID;

@SpringBootApplication
public class StudentjournalApplication {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(StudentjournalApplication.class, args);
    }

    @Bean
    public ApplicationRunner initializeStudents() {
        final Student student1 = new Student(UUID.randomUUID(), "John", "Doe");
        final Student student2 = new Student(UUID.randomUUID(), "Jane", "Smith");
        return args -> {studentRepository.saveAll(Arrays.asList(student1, student2));};
    }
}
