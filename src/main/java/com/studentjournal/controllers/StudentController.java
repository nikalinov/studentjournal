package com.studentjournal.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StudentController {
    @GetMapping("/students")
    public String index() {
        return "redirect:students/list";
    }

    @GetMapping("/students/list")
    public String getStudents(final Model model) {
        model.addAttribute("message", "AAAAAAAAAAAA");
        return "students/list";
    }
}
