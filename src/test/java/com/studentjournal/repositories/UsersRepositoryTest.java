package com.studentjournal.repositories;

import com.studentjournal.entities.Student;
import com.studentjournal.entities.User;
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
class UserRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveRecordWhenRecordIsValid() {
        final User expected = new User();
        expected.setUsername(UUID.randomUUID().toString());
        expected.setPassword(UUID.randomUUID().toString());
        final User saved = userRepository.save(expected);
        final User actual = entityManager.find(User.class, saved.getId());
        assertThat(actual).isEqualTo(expected);
    }
}