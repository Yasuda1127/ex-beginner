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
        model.addAttribute("total", total); // 結果を追加
        return "exam02-result.html";
    }
}
