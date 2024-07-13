package com.studentjournal.service;

import com.studentjournal.entities.Student;
import com.studentjournal.repositories.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(final StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Page<Student> getStudents(final int pageNumber, final int size) {
        return studentRepository.findAll(PageRequest.of(pageNumber, size));
    }

    public Optional<Student> getStudent(final UUID id) {
        return studentRepository.findById(id);
    }

    public Student save(final Student student) {
        return studentRepository.save(student);
    }

    public void delete(final UUID id) {
        studentRepository.deleteById(id);
    }
}
