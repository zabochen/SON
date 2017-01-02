package ua.ck.zabochen.son.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.util.ArrayList;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.adapter.QuizAdapter;
import ua.ck.zabochen.son.model.Animal;
import ua.ck.zabochen.son.utils.Utils;

public class QuizFragment extends Fragment {

    private int mNumberOfAnimals;
    private MediaPlayer mMediaPlayer;
    private ArrayList<Animal> mAnimals;
    private int mTrueAnimal;

    private static final String TAG = QuizFragment.class.getSimpleName();

    public static QuizFragment newInstance(int numberOfAnimal) {
        QuizFragment quizFragment = new QuizFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("numberOfAnimals", numberOfAnimal);
        quizFragment.setArguments(bundle);
        return quizFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mNumberOfAnimals = this.getArguments().getInt("numberOfAnimals");
        mAnimals = Utils.randomAnimals(mNumberOfAnimals);
        mTrueAnimal = Utils.trueAnimal(mAnimals);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_quiz_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new QuizAdapter(getActivity(), mAnimals, mTrueAnimal));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        playSound(mAnimals, mTrueAnimal);
    }

    private void playSound(ArrayList<Animal> animals, int animalId) {

        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

        try {
            mMediaPlayer = new MediaPlayer();

            AssetFileDescriptor assetFileDescriptor = getActivity().getAssets().openFd(
                    animals.get(animalId).getAnimalSounds().getSound1()
            );

            mMediaPlayer.setDataSource(
                    assetFileDescriptor.getFileDescriptor(),
                    assetFileDescriptor.getStartOffset(),
                    assetFileDescriptor.getLength());

            assetFileDescriptor.close();

            mMediaPlayer.prepare();
            mMediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
