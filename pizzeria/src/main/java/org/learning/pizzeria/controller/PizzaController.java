package org.learning.pizzeria.controller;

import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/pizze")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @GetMapping
    public String index(Model model){
        List<Pizza> listaPizze = pizzaRepository.findAll();
        model.addAttribute("list", listaPizze);

        return "/pizze/index";
    }

    @GetMapping("/{pizzeId}")
    public String show(@PathVariable("pizzeId") Integer id, Model model){
        Optional<Pizza> result = pizzaRepository.findById(id);
        if(result.isPresent()){
            model.addAttribute("pizza", result.get());
            return "/pizze/show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("pizza", new Pizza());
        return "/pizze/create";
    }

    @PostMapping("/create")
    public String doCreate(@ModelAttribute("Pizza") Pizza formPizza){
        //VALIDATION
        pizzaRepository.save(formPizza);
        return "redirect:/pizze";
    }

    @GetMapping("/search")
    public String search(Model model, @RequestParam(name = "q") String keyword){

        System.out.println(pizzaRepository.findByNome(keyword));

        List<Pizza> filteredPizze = pizzaRepository.findByNomeContainingIgnoreCase(keyword);
        model.addAttribute("list", filteredPizze);
        return "/pizze/index";
    }
}