package ua.ck.zabochen.son.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.controller.AnimalsController;
import ua.ck.zabochen.son.utils.Utils;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.MyViewHolder> {

    private Context mContext;
    private MediaPlayer mMediaPlayer = null;

    public AnimalAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_animal, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // Set animal image
        holder.animalImageView.setImageDrawable(Utils.getDrawable(
                mContext,
                AnimalsController.getInstance().getAnimals().get(position).getImage()));

        // Play animal sound
        holder.animalCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.playSound(
                        mContext,
                        mMediaPlayer,
                        AnimalsController.getInstance().getAnimals().get(position).getAnimalSounds().getSound1());
            }
        });
    }

    @Override
    public int getItemCount() {
        return AnimalsController.getInstance().getAnimals().size() > 0
                ? AnimalsController.getInstance().getAnimals().size()
                : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView animalCardView;
        private ImageView animalImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.animalCardView = (CardView) itemView.findViewById(R.id.item_animal_cardview);
            this.animalImageView = (ImageView) itemView.findViewById(R.id.item_animal_image);
        }
    }
}