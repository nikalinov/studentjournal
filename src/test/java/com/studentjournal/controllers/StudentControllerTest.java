package com.studentjournal.controllers;

import com.studentjournal.entities.Student;
import com.studentjournal.service.StudentService;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.*;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
    @Mock
    private StudentService service;
    private MockMvc mvc;
    private StudentController studentController;

    @Test
    public void returnsViewPageWithStudentIfExists() throws Exception {
        final UUID id = UUID.randomUUID();
        final Student student = new Student(id, UUID.randomUUID().toString(), UUID.randomUUID().toString());
        given(service.getStudent(id)).willReturn(Optional.of(student));
        mvc.perform(get("/students/view").param("id", id.toString()))
                .andExpect(status().isOk())
                .andExpect(model().attribute("student", notNullValue()))
                .andExpect(model().attribute("student", hasProperty("id", is(student.getId()))))
                .andExpect(model().attribute("student", hasProperty("firstName", is(student.getFirstName()))))
                .andExpect(model().attribute("student", hasProperty("lastName", is(student.getLastName()))));
    }

    @BeforeEach
    void setup() {
        studentController = new StudentController(service);
        this.mvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }



}