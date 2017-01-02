package ua.ck.zabochen.son.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.event.QuizFragmentEvent;
import ua.ck.zabochen.son.model.Animal;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.MyViewHolder> {

    private static final String TAG = QuizAdapter.class.getSimpleName();

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
        try {
            InputStream inputStream = mContext.getAssets().open(
                    mAnimals.get(position).getImage()
            );
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position == mTrueAnimal) {
                    EventBus.getDefault().post(new QuizFragmentEvent(true));
                } else {
                    EventBus.getDefault().post(new QuizFragmentEvent(false));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAnimals.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.item_quiz_card_view);
            this.imageView = (ImageView) itemView.findViewById(R.id.item_quiz_image_view);
        }
    }


}
