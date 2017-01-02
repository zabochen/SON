package ua.ck.zabochen.son.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.event.QuizFragmentEvent;
import ua.ck.zabochen.son.fragment.QuizFragment;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = QuizActivity.class.getSimpleName();
    private static int sCounter = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUi();
        setQuizFragment(savedInstanceState, 2);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(QuizFragmentEvent event) {
        if (event.getResponse()) {
            if (sCounter > 3 && sCounter < 10) {
                setQuizFragment(null, 3);
            } else if (sCounter > 10) {
                setQuizFragment(null, 4);
            } else {
                setQuizFragment(null, 2);
            }
        } else {
            Toast.makeText(getApplicationContext(), "FALSE", Toast.LENGTH_SHORT).show();
        }
    }

    private void setUi() {
        // Layout
        setContentView(R.layout.activity_quiz);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_quiz_toolbar);
        if (toolbar != null) {
            // Set title color
            toolbar.setTitleTextColor(ContextCompat.getColor(
                    getApplicationContext(),
                    R.color.activity_quiz_toolbar_title));
            // Toolbar instead ActionBar
            setSupportActionBar(toolbar);
            // Add Home button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setQuizFragment(Bundle bundle, int numberOfAnimals) {
        if (bundle == null) {
            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_quiz_frame_layout, new QuizFragment().newInstance(numberOfAnimals))
                    .commit();
            sCounter++;
        }
    }
}
