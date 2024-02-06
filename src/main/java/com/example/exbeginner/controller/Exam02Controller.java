package com.example.exbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Exam02Controller {

    @RequestMapping("/exam02")
    public String start() {
        return "exam02.html";
    }

    @RequestMapping("/exam02-result")
    public String result(@RequestParam("first") int first, @RequestParam("second") int second, Model model) {
        int total = first + second;
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("total", total);
        return "exam02-result.html";
    }

    @RequestMapping("/exam02-result2")
    public String result2(){
        return "forward:/exam02-result";
    }
}
