package ua.ck.zabochen.son.adapter;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

import ua.ck.zabochen.son.R;
import ua.ck.zabochen.son.controller.TransportController;

public class TransportAdapter extends RecyclerView.Adapter<TransportAdapter.MyViewHolder> {

    private Context mContext;
    private MediaPlayer mMediaPlayer;

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
        try {
            InputStream inputStream = mContext.getAssets().open(
                    TransportController.getInstance().getsTransport().get(position).getImage()
            );
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            holder.imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                if (mMediaPlayer != null) {
                    mMediaPlayer.release();
                    mMediaPlayer = null;
                }

                try {
                    mMediaPlayer = new MediaPlayer();

                    AssetFileDescriptor assetFileDescriptor = mContext.getAssets().openFd(
                            TransportController.getInstance().getsTransport().get(position).getTransportSounds().getSound1()
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
        });
    }

    @Override
    public int getItemCount() {
        return TransportController.getInstance().getsTransport().size() > 0
                ? TransportController.getInstance().getsTransport().size()
                : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;
        private ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.cardView = (CardView) itemView.findViewById(R.id.item_transport_cardview);
            this.imageView = (ImageView) itemView.findViewById(R.id.item_transport_image);
        }
    }
}