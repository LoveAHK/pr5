package com.example.Rabota.Controller;

import com.example.Rabota.Models.Address;
import com.example.Rabota.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping("/Address")
    public String GetRab(Model model) {
        Iterable<Address> address = addressRepository.findAll();
        model.addAttribute("address", address);
        return "MainAddress";
    }

    @GetMapping("/Add/Address")
    public String GetAddAddress(Address address, Model model) {
        return "Add-Address";
    }

    @PostMapping("/Add/Address")
    public String blogPostAdd(@ModelAttribute("address") @Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Add-Address";
        }
        addressRepository.save(address);
        return "redirect:/Address";
    }
}