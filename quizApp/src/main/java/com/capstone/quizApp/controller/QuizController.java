package com.capstone.quizApp.controller;

import com.capstone.quizApp.model.QuizQuestions;
import com.capstone.quizApp.model.Result;
import com.capstone.quizApp.myServices.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class QuizController {

    @Autowired
    Result result;
    @Autowired
    QuizService quizService;

    Boolean submitted = false;

    @ModelAttribute("result")
    public Result getResult() {
        return result;
    }
    @GetMapping("index")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/quiz")
    public String quiz(@RequestParam String username, Model m) {
        if(username.equals("")) {
            return "redirect:/";
        }
        submitted = false;
        result.setUsername(username);
        QuizQuestions quizQuestions = quizService.getQuestions();
        m.addAttribute("quizQuestions", quizQuestions);
        return "quiz";
    }

}
