package ua.ck.zabochen.son.model;

import com.google.gson.annotations.SerializedName;

public class Animal {

    @SerializedName("id")
    private int id;

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;

    @SerializedName("image")
    private String image;

    @SerializedName("sounds")
    private AnimalSounds animalSounds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AnimalSounds getAnimalSounds() {
        return animalSounds;
    }

    public void setAnimalSounds(AnimalSounds animalSounds) {
        this.animalSounds = animalSounds;
    }

}
