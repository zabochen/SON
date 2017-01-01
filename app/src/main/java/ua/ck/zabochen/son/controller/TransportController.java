package ua.ck.zabochen.son.controller;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.model.Transport;

public class TransportController {

    private static TransportController sTransportController;
    private static ArrayList<Transport> sTransport = new ArrayList<>();

    private TransportController() {
    }

    public static TransportController getInstance() {
        if (sTransportController == null) {
            sTransportController = new TransportController();
        }
        return sTransportController;
    }

    public ArrayList<Transport> getsTransport() {
        return sTransport;
    }

    public void setTransport(Context context) {
        String buffer;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(R.raw.transport)));

        try {
            while ((buffer = bufferedReader.readLine()) != null) {
                stringBuilder.append(buffer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        sTransport = gson.fromJson(stringBuilder.toString(), new TypeToken<ArrayList<Transport>>() {
        }.getType());
    }

}
