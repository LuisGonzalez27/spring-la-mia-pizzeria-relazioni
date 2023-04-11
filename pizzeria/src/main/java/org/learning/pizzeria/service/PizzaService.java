package org.learning.pizzeria.service;

import org.learning.pizzeria.exceptions.PizzaNotFoundException;
import org.learning.pizzeria.model.Pizza;
import org.learning.pizzeria.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {
    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza createPizza(Pizza formPizza){
        Pizza pizzaToPersist = new Pizza();
        pizzaToPersist.setNome(formPizza.getNome());
        pizzaToPersist.setPrezzo(formPizza.getPrezzo());
        pizzaToPersist.setDescrizione(formPizza.getDescrizione());
        pizzaToPersist.setIngredienti(formPizza.getIngredienti());
        return pizzaRepository.save(pizzaToPersist);
    }

    public List<Pizza> getAllPizze(){
        return pizzaRepository.findAll(Sort.by("nome"));
    }

    public  List<Pizza> getFilteredPizze(String keyword){
        return pizzaRepository.findByNomeContainingIgnoreCase(keyword);
    }

    public Pizza getById(Integer id) throws  PizzaNotFoundException{
        Optional<Pizza> result = pizzaRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new PizzaNotFoundException(Integer.toString(id));
        }
    }

    public  Pizza updatePizza(Pizza formPizza, Integer id) throws PizzaNotFoundException {
        Pizza pizzaToUpdate= getById(id);
        pizzaToUpdate.setNome(formPizza.getNome());
        pizzaToUpdate.setDescrizione(formPizza.getDescrizione());
        pizzaToUpdate.setPrezzo(formPizza.getPrezzo());
        pizzaToUpdate.setIngredienti(formPizza.getIngredienti());
        return  pizzaRepository.save(pizzaToUpdate);
    }

    public boolean deleteById(Integer id) throws PizzaNotFoundException {
        pizzaRepository.findById(id).orElseThrow(() -> new PizzaNotFoundException(Integer.toString(id)));
        try {
            pizzaRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }

}