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
import ua.ck.zabochen.son.controller.TransportController;
import ua.ck.zabochen.son.utils.Utils;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.MyViewHolder> {

    private Context mContext;
    private MediaPlayer mMediaPlayer = null;

    public TransportAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_transport, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        // Set transport image
        holder.transportImageView.setImageDrawable(Utils.getDrawable(
                mContext,
                TransportController.getInstance().getsTransport().get(position).getImage()
        ));

        // Play transport sound
        holder.transportCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.playSound(
                        mContext,
                        mMediaPlayer,
                        TransportController.getInstance().getsTransport().get(position).getTransportSounds().getSound1()
                );
            }
        });
    }

    @Override
    public int getItemCount() {
        return TransportController.getInstance().getsTransport().size() > 0
                ? TransportController.getInstance().getsTransport().size()
                : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView transportCardView;
        private ImageView transportImageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.transportCardView = (CardView) itemView.findViewById(R.id.item_transport_cardview);
            this.transportImageView = (ImageView) itemView.findViewById(R.id.item_transport_image);
        }
    }
}