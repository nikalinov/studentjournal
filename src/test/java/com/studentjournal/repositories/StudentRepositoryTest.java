package com.studentjournal.repositories;

import com.studentjournal.entities.Student;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveRecordWhenRecordIsValid() {
        final Student expected = new Student();
        expected.setFirstName(UUID.randomUUID().toString());
        expected.setLastName(UUID.randomUUID().toString());
        final Student saved = studentRepository.save(expected);
        final Student actual = entityManager.find(Student.class, saved.getId());
        assertThat(actual).isEqualTo(expected);
    }
}