package com.example.Rabota.Controller;


import com.example.Rabota.Models.Students;
import com.example.Rabota.Models.University;
import com.example.Rabota.repo.StudentsRepository;
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
public class RabotaController {

    @Autowired
    private StudentsRepository studentsRepository;
    @Autowired
    private UniversityRepository universityRepository;

    @GetMapping("/")
public String Glavn(Model model)
    {
        Iterable<Students> students = studentsRepository.findAll();
        model.addAttribute("students",students);
        return "Main";
    }
    @GetMapping("/Add/Student")
    public String GetAddStudent(Students students,Model model)
    {
        return "Add-Student";
    }
    @PostMapping("/Add/Student")
    public String StudAdd(@ModelAttribute("students")@Valid Students students, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return "Add-Student";
        }
        studentsRepository.save(students);
        return "redirect:/";
    }


    @GetMapping( path = "/Search/Student")
    public String blogFilter(Model model)
    {
        return "Search-Student";
    }

    @PostMapping("/Search/Student-result")
    public String blogResult(@RequestParam String lastname, Model model)
    {
        List<Students> stud = studentsRepository.findByLastname(lastname);
        model.addAttribute("result", stud);
        return "Search-Student";
    }
    @PostMapping("/Search/Student")
    public String simpleSearch(@RequestParam String lastname, Model model)
    {
        List<Students> stud = studentsRepository.findByLastnameContains(lastname);
        model.addAttribute("result", stud);
        return "Search-Student";
    }
    @GetMapping("/Stud/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model)
    {
        Optional<Students> student = studentsRepository.findById(id);
        ArrayList<Students> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        if(!studentsRepository.existsById(id))
        {
            return "redirect:/";
        }
        return "Stud-details";
    }
    @GetMapping("/Stud/{id}/edit")
    public String blogEdit(@PathVariable("id")long id,
                           Model model)
    {
        Students students = studentsRepository.findById(id).orElseThrow(()
                ->new IllegalArgumentException("Invalid students Id" + id));
        model.addAttribute("students",students);
        return "Stud-edit";
    }
    @PostMapping("/Stud/{id}/edit")
    public String blogPostUpdate(@ModelAttribute("students")@Valid Students students, BindingResult bindingResult,
                                 @PathVariable("id") long id) {

        students.setId(id);
        if (bindingResult.hasErrors()) {
            return "Stud-edit";
        }
        studentsRepository.save(students);
        return "redirect:/";
    }
    @PostMapping("/Stud/{id}/remove")
    public String blogBlogDelete(@PathVariable("id") long id, Model model){
        Students student = studentsRepository.findById(id).orElseThrow();
        studentsRepository.delete(student);
        return "redirect:/";
    }

    @GetMapping("/person")
    private String Main(Model model){
        Iterable<Students> students = studentsRepository.findAll();
        model.addAttribute("students", students);
        Iterable<University> universities = universityRepository.findAll();
        model.addAttribute("universities", universities);

        return "person";
    }

    @PostMapping("/person/add")
    public String blogPostAdd(@RequestParam String student, @RequestParam String universiti, Model model)
    {
        Students student2 = studentsRepository.findByName(student);
        University university2 = universityRepository.findByName(universiti);
        student2.getUniversities().add(university2);
        university2.getStudents().add(student2);
        studentsRepository.save(student2);
        universityRepository.save(university2);
        return "person";
    }


}
