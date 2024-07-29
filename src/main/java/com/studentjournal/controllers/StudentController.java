package com.studentjournal.controllers;

import com.studentjournal.entities.Student;
import com.studentjournal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;
import java.util.UUID;


@Controller
public class StudentController {
    private final StudentService service;
    private final int DEFAULTPAGESIZE = 1;

    @Autowired
    public StudentController(final StudentService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:students/list";
    }

    @GetMapping("/students/list")
    public String getStudents(
            final Model model,
            @RequestParam(name="page", defaultValue="0") final int pageNum,
            @RequestParam(name="size", defaultValue= "" + DEFAULTPAGESIZE)  final int pageSize
    ) {
        final Page<Student> page = service.getStudents(pageNum, pageSize);
        final int currPageNum = page.getNumber();
        final int prevPageNum = page.hasPrevious() ? currPageNum - 1 : -1;
        final int nextPageNum = page.hasNext() ? currPageNum + 1 : -1;

        model.addAttribute("students", page.getContent());
        model.addAttribute("prevPageNum", prevPageNum);
        model.addAttribute("nextPageNum", nextPageNum);
        model.addAttribute("pageSize", pageSize);
        return "students/list";
    }

    @GetMapping("/students/add")
    public String add() {
        return "students/add";
    }

    @GetMapping("/students/view")
    public String view(final Model model, @RequestParam(name="id") final UUID id) {
        final Optional<Student> record = service.getStudent(id);
        model.addAttribute("student", record.orElseGet(Student::new));
        return "students/view";
    }

    @GetMapping("/students/edit")
    public String edit(final Model model, @RequestParam(name="id") final UUID id) {
        final Optional<Student> record = service.getStudent(id);
        model.addAttribute("student", record.orElseGet(Student::new));
        return "students/edit";
    }

    @GetMapping("/students/delete")
    public String deleteGet(final Model model, @RequestParam(name="id") final UUID id) {
        final Optional<Student> record = service.getStudent(id);
        model.addAttribute("student", record.orElseGet(Student::new));
        return "students/delete";
    }

    @PostMapping("/students/delete")
    public String deletePost(final Model model, @RequestParam(name="id") final UUID id) {
        service.delete(id);
        return "redirect:list";
    }


    @PostMapping("/students/save")
    public String save(final Model model, @ModelAttribute(name="student") final Student student) {
        service.save(student);
        return "redirect:list";
    }
}
