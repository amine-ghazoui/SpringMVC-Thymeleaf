package org.example.springmvcthymeleaf.web;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.springmvcthymeleaf.entities.Patient;
import org.example.springmvcthymeleaf.repositories.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;


    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "5") int size,
                        @RequestParam(name = "keyword", defaultValue = "")String keyword) {
        Page<Patient> pagePatients = patientRepository.findByNomContains(keyword, PageRequest.of(page, size));
        model.addAttribute("ListePatients", pagePatients.getContent());
        model.addAttribute("pages", new int[pagePatients.getTotalPages()]);
        model.addAttribute("pageCurrent", page);
        model.addAttribute("keyword", keyword);
        return "patients";
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, int size) {

        patientRepository.deleteById(id);
        return "redirect:/index?page=" + page + "&size="+ size;
    }

    @GetMapping("formPatient")
    public String formPatient(Model model) {

        model.addAttribute("patient", new Patient());
        return "patientForm";
    }

    @PostMapping
    public String save (Model model,@Valid Patient patient,
                        @RequestParam(defaultValue = "0") int page,
                        @RequestParam(defaultValue = "") String keyword) {

        patientRepository.save(patient);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/editPatient")
    public String editPatient(Model model, Long id, String keyword, int page){

        Patient patient = patientRepository.findById(id).orElse(null);

        if(patient == null) throw new RuntimeException("Patient not found");

        model.addAttribute("patient", patient);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);

        return "editPatient";
    }

    @GetMapping("/ ")
    public String home(){

        return "redirect:/index";
    }

}
