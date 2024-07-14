package com.studentjournal.service;
import com.studentjournal.entities.Student;
import com.studentjournal.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.BDDMockito.then;
import java.util.Optional;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {
    @MockBean
    private StudentRepository studentRepository;
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        studentService = new StudentService(studentRepository);
    }

    @Test
    public void getStudentReturnsStudentWhenExists() {

        final UUID id = randomUUID();

        final Student student = new Student(randomUUID(), randomUUID().toString(), randomUUID().toString());

        final Optional<Student> expected = Optional.of(student);

        when(studentRepository.findById(id)).thenReturn(expected);

        final Optional<Student> actual = studentService.getStudent(id);

        assertThat(actual).isEqualTo(expected);

        then(studentRepository).should().findById(id);
        then(studentRepository).shouldHaveNoMoreInteractions();
    }
}