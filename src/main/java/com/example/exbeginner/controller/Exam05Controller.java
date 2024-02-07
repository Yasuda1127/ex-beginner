package com.example.exbeginner.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbeginner.model.Member;
import com.example.exbeginner.repository.MemberRepository;

@Controller
public class Exam05Controller {

    @Autowired
    private MemberRepository repository;

    @RequestMapping("/exam05")
    public String start() {
        return "exam05";
    }

    @RequestMapping("/exam05-result")
    public String result(Member member, Model model) {

        List<Member> searchResults = repository.findByName(member);
        // 取得したデータをModelに追加
        model.addAttribute("searchResults", searchResults);

        return "exam05-result";
    }
}
