package ua.ck.zabochen.son.utils;

import java.util.ArrayList;
import java.util.Random;

import ua.ck.zabochen.son.controller.AnimalsController;
import ua.ck.zabochen.son.model.Animal;

public class Utils {

    public static ArrayList<Animal> randomAnimals(int numberOfAnimals) {
        Random random = new Random();
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < numberOfAnimals; i++) {
            animals.add(AnimalsController.getInstance().getAnimals().get(
                    random.nextInt(AnimalsController.getInstance().getAnimals().size())
            ));
        }
        return animals;
    }

    public static int trueAnimal(ArrayList<Animal> animals) {
        Random random = new Random();
        return random.nextInt(animals.size());
    }

}
