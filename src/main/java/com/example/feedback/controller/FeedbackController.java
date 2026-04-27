package com.example.feedback.controller;

import com.example.feedback.model.Feedback;
import com.example.feedback.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackRepository repo;

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @GetMapping("/form")
    public String form(Model model){ // model send the data from html to db
        model.addAttribute("feedback", new Feedback());
        return "feedback-form";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute Feedback feedback){
        repo.save(feedback);
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("feedbacks", repo.findAll());
        return "feedback-list";
    }
}