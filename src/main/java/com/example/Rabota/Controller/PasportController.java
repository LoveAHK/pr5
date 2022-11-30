package com.example.Rabota.Controller;

import com.example.Rabota.Models.Pasport;
import com.example.Rabota.repo.PasportRepository;
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
public class PasportController {
    @Autowired
    private PasportRepository pasportRepository;

    @GetMapping("/Pasport")
    public String GetRab(Model model) {
        Iterable<Pasport> pasport = pasportRepository.findAll();
        model.addAttribute("pasport", pasport);
        return "MainPasport";
    }

    @GetMapping("/Add/Pasport")
    public String GetAddPasport(Pasport pasport, Model model) {
        return "Add-Pasport";
    }

    @PostMapping("/Add/Pasport")
    public String blogPostAdd(@ModelAttribute("pasport") @Valid Pasport pasport, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Add-Pasport";
        }
        pasportRepository.save(pasport);
        return "redirect:/Pasport";
    }
}