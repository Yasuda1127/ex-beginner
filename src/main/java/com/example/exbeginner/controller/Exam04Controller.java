package com.example.exbeginner.controller;

import com.example.exbeginner.model.User;

import jakarta.validation.Valid;

import com.example.exbeginner.form.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Exam04Controller {

    @RequestMapping("/exam04")
    public String start(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "exam04";
    }

    @PostMapping("/exam04-result")
    public String result(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "exam04"; 
        }
        User user = new User();
        user.setName(userForm.getName());
        user.setAge(userForm.getAge());
        user.setComment(userForm.getComment());
        
        model.addAttribute("user", user);
        return "exam04-result";
    }
}
