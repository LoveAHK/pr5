package com.example.Rabota.Controller;


import com.example.Rabota.Models.Address;
import com.example.Rabota.Models.Pasport;
import com.example.Rabota.Models.Person;
import com.example.Rabota.repo.AddressRepository;
import com.example.Rabota.repo.PasportRepository;
import com.example.Rabota.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OnetoOneController {
    @Autowired
    private PasportRepository pasportRepository;
    @Autowired
    private PersonRepository personRepository;
    @GetMapping("/Users")
    public String Userss(Model model){
        Iterable<Pasport> pasport = pasportRepository.findAll();
        model.addAttribute("pasport", pasport);
        return "Users";
    }

    @PostMapping("/Users/add")
    public String UsAdd(@RequestParam String name, @RequestParam String number, Model model)
    {
        System.out.println(name);
        Pasport pasport = pasportRepository.findByNumber(number);
        System.out.println(pasport.getId());
        Person person = new Person(name, pasport);
        personRepository.save(person);
        return "Users";
    }
}
