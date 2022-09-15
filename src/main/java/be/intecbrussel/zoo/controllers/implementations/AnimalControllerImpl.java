package be.intecbrussel.zoo.controllers.implementations;

import be.intecbrussel.zoo.controllers.interfaces.AnimalController;
import be.intecbrussel.zoo.data.Animal;
import be.intecbrussel.zoo.data.Country;
import be.intecbrussel.zoo.services.interfaces.AnimalService;
import be.intecbrussel.zoo.services.interfaces.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AnimalControllerImpl implements AnimalController {

    private AnimalService animalService;
    private CountryService countryService;

    @Autowired
    public AnimalControllerImpl(AnimalService animalService, CountryService countryService){
        this.animalService = animalService;
        this.countryService = countryService;
    }



    @Override
    @GetMapping("animals/{countryName}")
    public String seeAllAnimalsByCountry(Model model, @PathVariable("countryName") String countryName) {
        Country country = countryService.getCountryByName(countryName);
        List<Animal> animals = animalService.getAnimalsByCountry(country);
        model.addAttribute("animals", animals);
        model.addAttribute("animal", new Animal("",country));

        return "animals";
    }

    @Override
    @PostMapping("addAnimal/{countryName}")
    public String addAnimal(String animalName, @PathVariable("countryName") String countryName) {
        Animal animal = new Animal(animalName, countryService.getCountryByName(countryName));
        animalService.addAnimal(animal);
        return "redirect:/animals/"+countryName;
    }

    @Override
    public String deleteAnimal(long animalId) {
        animalService.deleteAnimal(animalId);
        return "animals";
    }
}