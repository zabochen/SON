package ua.ck.zabochen.son.utils;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import ua.ck.zabochen.son.controller.AnimalsController;
import ua.ck.zabochen.son.model.Animal;

public class Utils {

    public static ArrayList<Animal> randomAnimals(int numberOfAnimals) {

        // Inflate Set random values
        Random random = new Random();
        Set<Integer> animalIdSet = new HashSet<>();
        while (animalIdSet.size() < numberOfAnimals) {
            animalIdSet.add(random.nextInt(AnimalsController.getInstance().getAnimals().size()));
        }

        // Iterate Set and inflate array
        int[] animalIdArray = new int[animalIdSet.size()];
        Iterator<Integer> iterator = animalIdSet.iterator();
        for (int i = 0; iterator.hasNext(); ++i) {
            animalIdArray[i] = iterator.next();
        }

        // Set List with selected animals
        ArrayList<Animal> animals = new ArrayList<>();
        for (int i = 0; i < animalIdArray.length; i++) {
            animals.add(AnimalsController.getInstance().getAnimals().get(animalIdArray[i]));
        }

        return animals;
    }

    public static int trueAnimal(ArrayList<Animal> animals) {
        Random random = new Random();
        return random.nextInt(animals.size());
    }

    public static void playSound(Context context, MediaPlayer mediaPlayer, String sound) {

        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        try {
            mediaPlayer = new MediaPlayer();

            AssetFileDescriptor assetFileDescriptor = context.getAssets().openFd(sound);

            mediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());

            assetFileDescriptor.close();

            mediaPlayer.prepare();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Drawable getDrawable(Context context, String image) {
        try {
            InputStream inputStream = context.getAssets().open(image);
            return Drawable.createFromStream(inputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
