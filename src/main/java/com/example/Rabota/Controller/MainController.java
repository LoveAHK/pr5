package com.example.Rabota.Controller;


import com.example.Rabota.Models.Address;
import com.example.Rabota.Models.Sotrudniki;
import com.example.Rabota.repo.AddressRepository;
import com.example.Rabota.repo.SotrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    public AddressRepository addressRepository;
    @Autowired
    public SotrRepository sotrRepository;

    @GetMapping("/sotrudnik")
    public String Main1(Model model){
        Iterable<Address> address = addressRepository.findAll();
        model.addAttribute("address",address);
        return "sotrudnik";
    }

    @PostMapping("/sotrudnik/add")
    public String blogPostAdd1(@RequestParam String name, @RequestParam String street, Model model)
    {
        Address address = addressRepository.findByStreet(street);
        Sotrudniki person = new Sotrudniki(name, address);
        sotrRepository.save(person);
        return "sotrudnik";
    }
}
