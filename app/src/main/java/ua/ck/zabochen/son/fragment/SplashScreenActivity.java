package ua.ck.zabochen.son.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.activity.MainActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN_TIME = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUi();
        startMainActivity();
    }

    private void setUi() {
        // Layout
        setContentView(R.layout.activity_splash_screen);
    }

    private void startMainActivity() {
        new SplashScreenTask().execute();
    }

    class SplashScreenTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                TimeUnit.SECONDS.sleep(SPLASH_SCREEN_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            return null;
        }
    }
}
