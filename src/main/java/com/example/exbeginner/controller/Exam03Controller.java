package com.example.exbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;



@Controller
public class Exam03Controller implements ServletContextAware {

    private jakarta.servlet.ServletContext servletContext;

    @Autowired
    public void setServletContext(jakarta.servlet.ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @RequestMapping("/exam03")
    public String start() {
        return "exam03.html";
    }

    @RequestMapping("/exam03-result")
    public String result(@RequestParam("first") int first, @RequestParam("second") int second,
                         @RequestParam("third") int third, Model model) {
        int total = first + second + third;
        int inTax = (int) (total * 1.1);
        model.addAttribute("first", first);
        model.addAttribute("second", second);
        model.addAttribute("third", third);
        model.addAttribute("total", total);
        model.addAttribute("inTax", inTax);

        // ServletContextにデータを格納
        servletContext.setAttribute("exam03Data", model.asMap());

        return "exam03-result.html";
    }
}
