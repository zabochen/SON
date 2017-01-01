package ua.ck.zabochen.son.model;

import com.google.gson.annotations.SerializedName;

public class AnimalSounds {

    @SerializedName("sound_1")
    private String sound1;

    @SerializedName("sound_2")
    private String sound2;

    public String getSound1() {
        return sound1;
    }

    public void setSound1(String sound1) {
        this.sound1 = sound1;
    }

    public String getSound2() {
        return sound2;
    }

    public void setSound2(String sound2) {
        this.sound2 = sound2;
    }

}
