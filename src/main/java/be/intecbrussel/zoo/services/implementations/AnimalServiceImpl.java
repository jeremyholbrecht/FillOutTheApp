package be.intecbrussel.zoo.services.implementations;

import be.intecbrussel.zoo.data.Animal;
import be.intecbrussel.zoo.data.Country;
import be.intecbrussel.zoo.repositories.AnimalRepository;
import be.intecbrussel.zoo.services.interfaces.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnimalServiceImpl implements AnimalService {

    private AnimalRepository animalRepository;

    @Autowired
    public AnimalServiceImpl(AnimalRepository animalRepository){
        this.animalRepository = animalRepository;
    }

    @Override
    public Animal getAnimalByID(long id) {
       return animalRepository.getOne(id);
    }

    @Override
    public List<Animal> getAnimalsByCountry(Country country) {
        return animalRepository.getByCountry(country);
    }

    @Override
    public void addAnimal(Animal animal) {
        animalRepository.save(animal);
    }

    @Override
    public void deleteAnimal(long id) {

        animalRepository.delete(animalRepository.getOne(id));
    }
}
