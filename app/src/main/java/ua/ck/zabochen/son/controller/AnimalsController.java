package ua.ck.zabochen.son.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.model.Animal;

public class AnimalsController {

    private static AnimalsController sAnimalsController;
    private static ArrayList<Animal> sAnimals = new ArrayList<>();

    private AnimalsController() {
    }

    public static AnimalsController getInstance() {
        if (sAnimalsController == null) {
            sAnimalsController = new AnimalsController();
        }
        return sAnimalsController;
    }

    public ArrayList<Animal> getAnimals() {
        return sAnimals;
    }

    public void setAnimals(Context context) {
        String buffer;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.animals)));

        // Reading '/raw/animals.json'
        try {
            while ((buffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert JSON to ArrayList<Animal>
        Gson gson = new Gson();
        sAnimals = gson.fromJson(stringBuilder.toString(), new TypeToken<ArrayList<Animal>>() {
        }.getType());
    }

}
