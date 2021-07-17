package pl.bejot.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.bejot.sfgpetclinic.model.Vet;
import pl.bejot.sfgpetclinic.services.VetService;

import java.util.Set;

@Controller
@RequestMapping("/vets")
public class VetController {

    private final VetService vetService;

    public VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @RequestMapping({"", "/index", "/index.html"})
    public String listVets(Model model) {
        Set<Vet> vets = vetService.findAll();
        model.addAttribute("vets", vets);

        return "vets/index";
    }
}
