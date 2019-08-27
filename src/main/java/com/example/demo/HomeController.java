package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {
    @Autowired
    JobRepository jobRepository;

    @RequestMapping("/")
    public String listJobs(Model model) {
        model.addAttribute("todos", jobRepository.findAll() );
        return "list";
    }

    @GetMapping("/add")
    public String JobForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "jobform";

    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "jobform";
        }
        jobRepository.save(todo);
        return "redirect:/";
    }

}

