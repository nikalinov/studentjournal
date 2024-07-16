package com.studentjournal.controllers;

import com.studentjournal.entities.Student;
import com.studentjournal.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class StudentController {
    private final StudentService service;

    @Autowired
    public StudentController(final StudentService service) {
        this.service = service;
    }

    @GetMapping("/students")
    public String index() {
        return "redirect:students/list";
    }

    @GetMapping("/students/list")
    public String getStudents(
            final Model model,
            @RequestParam(value="page", defaultValue="0") final int pageNum,
            @RequestParam(value="size", defaultValue="10")  final int pageSize
    ) {
        final Page<Student> page = service.getStudents(pageNum, pageSize);
        final int currPageNum = page.getNumber();
        final int prevPageNum = page.hasPrevious() ? currPageNum - 1 : -1;
        final int nextPageNum = page.hasNext() ? currPageNum + 1 : -1;

        model.addAttribute("students", page.getContent());
        model.addAttribute("prevPageNum", prevPageNum);
        model.addAttribute("nextPageNum", nextPageNum);
        return "students/list";
    }

    @GetMapping("/students/add")
    public String add() {
        return "students/add";
    }

    @GetMapping("/students/view")
    public String view() {
        return "students/view";
    }

    @GetMapping("/students/edit")
    public String edit() {
        return "students/edit";
    }

    @GetMapping("/students/delete")
    public String delete() {
        return "students/delete";
    }
}
