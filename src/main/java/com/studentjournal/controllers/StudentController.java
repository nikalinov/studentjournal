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
