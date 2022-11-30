package com.example.Rabota.Controller;

import com.example.Rabota.Models.University;
import com.example.Rabota.repo.UniversityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UniversityController {
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/University")
    public String GetRab(Model model) {
        Iterable<University> university = universityRepository.findAll();
        model.addAttribute("university", university);
        return "MainUniversity";
    }

    @GetMapping("/Add/University")
    public String GetAddUniversity(University university, Model model) {
        return "Add-University";
    }

    @PostMapping("/Add/University")
    public String blogPostAdd(@ModelAttribute("university") @Valid University university, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Add-University";
        }
        universityRepository.save(university);
        return "redirect:/University";
    }
}