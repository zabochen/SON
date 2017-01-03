package ua.ck.zabochen.son.fragment;

import android.app.Fragment;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.adapter.QuizAdapter;
import ua.ck.zabochen.son.model.Animal;
import ua.ck.zabochen.son.utils.Utils;

public class QuizFragment extends Fragment {

    private MediaPlayer mMediaPlayer = null;
    private ArrayList<Animal> mAnimals;
    private int mTrueAnimal;

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
        mAnimals = Utils.randomAnimals(this.getArguments().getInt("numberOfAnimals"));
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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new QuizAdapter(getActivity(), mAnimals, mTrueAnimal));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Play true animal sound
        Utils.playSound(
                getActivity(),
                mMediaPlayer,
                mAnimals.get(mTrueAnimal).getAnimalSounds().getSound1()
        );
    }

}