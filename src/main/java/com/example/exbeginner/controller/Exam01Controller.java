package com.example.exbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class Exam01Controller {

    @RequestMapping("/exam01")
    public String start() {
        return "exam01.html";
    }

    @RequestMapping("/exam01-result")
    public String result(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "exam01-result.html";
    }
}
