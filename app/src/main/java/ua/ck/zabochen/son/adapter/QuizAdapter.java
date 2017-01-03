package ua.ck.zabochen.son.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.event.QuizFragmentEvent;
import ua.ck.zabochen.son.model.Animal;
import ua.ck.zabochen.son.utils.Utils;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {

    private Context mContext;
    private ArrayList<Animal> mAnimals;
    private int mTrueAnimal;

    public QuizAdapter(Context context, ArrayList<Animal> animals, int trueAnimal) {
        this.mContext = context;
        this.mAnimals = animals;
        this.mTrueAnimal = trueAnimal;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_quiz, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // Set animal image
        holder.animalImageView.setImageDrawable(Utils.getDrawable(
                mContext,
                mAnimals.get(position).getImage()
        ));

        // Get user choice
        holder.animalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == mTrueAnimal) {
                    // True answer
                    EventBus.getDefault().post(new QuizFragmentEvent(true));
                } else {
                    // False answer
                    EventBus.getDefault().post(new QuizFragmentEvent(false));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnimals.size() > 0 ? mAnimals.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView animalCardView;
        private ImageView animalImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.animalCardView = (CardView) itemView.findViewById(R.id.item_quiz_card_view);
            this.animalImageView = (ImageView) itemView.findViewById(R.id.item_quiz_image_view);
        }
    }
}