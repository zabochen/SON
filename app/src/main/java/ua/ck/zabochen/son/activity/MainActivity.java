package ua.ck.zabochen.son.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ua.ck.zabochen.son.R;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUi();
    }


    private void setUi() {
        // Layout
        setContentView(R.layout.activity_main);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        if (toolbar != null) {
            // Set title color
            toolbar.setTitleTextColor(ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.activity_main_toolbar_title));
            // Toolbar instead ActionBar
            setSupportActionBar(toolbar);
        }

        // Find buttons
        Button btnStartListeningActivity = (Button) findViewById(R.id.activity_main_button_start_listening);
        Button btnStartQuizActivity = (Button) findViewById(R.id.activity_main_button_start_quiz);

        // Set buttons listener
        btnStartListeningActivity.setOnClickListener(this);
        btnStartQuizActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_main_button_start_listening:
                startActivity(new Intent(getApplicationContext(), ListeningActivity.class));
                break;
            case R.id.activity_main_button_start_quiz:
                startActivity(new Intent(getApplicationContext(), QuizActivity.class));
                break;
        }
    }
}