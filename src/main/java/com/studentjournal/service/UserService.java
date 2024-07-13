package com.studentjournal.service;

import com.studentjournal.entities.Student;
import com.studentjournal.entities.User;
import com.studentjournal.repositories.StudentRepository;
import com.studentjournal.repositories.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(final UserRepository studentRepository) {
        this.userRepository = studentRepository;
    }

    public Page<User> getUsers(final int pageNumber, final int size) {
        return userRepository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<User> getUser(final UUID id) {
        return userRepository.findById(id);
    }

    public User save(final User user) {
        return userRepository.save(user);
    }

    public void delete(final UUID id) {
        userRepository.deleteById(id);
    }
}
