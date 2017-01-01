package ua.ck.zabochen.son.model;

import com.google.gson.annotations.SerializedName;

public class TransportSounds {

    @SerializedName("sound_1")
    private String sound1;

    public String getSound1() {
        return sound1;
    }

    public void setSound1(String sound1) {
        this.sound1 = sound1;
    }

}
