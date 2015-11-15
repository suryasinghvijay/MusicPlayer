package com.shaiban.audioplayer.mplayer.custom_adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shaiban.audioplayer.mplayer.main_fragment.FragmentSongs;
import com.shaiban.audioplayer.mplayer.R;
import com.shaiban.audioplayer.mplayer.music_attributes.SongAttributeList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class recyclerview_adapter_songs extends RecyclerView.Adapter<recyclerview_adapter_songs.ViewHolder> {
    public static Context ctx=null;
    ArrayList<SongAttributeList> nList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public final View mView;

        public final TextView mTextView,mTimeView,mArtistView;
        public final ImageButton playpauseButton;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.textview1);
            mTimeView = (TextView)view.findViewById(R.id.textview2);
            mArtistView= (TextView)view.findViewById(R.id.textview3);
            playpauseButton = (ImageButton)view.findViewById(R.id.playbutton);
        }


    }

    public recyclerview_adapter_songs(ArrayList<SongAttributeList> items, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        nList = items;
    }

    //Execution of adapter class starts from onCreateViewHolder()
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ctx= parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.songs_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SongAttributeList oList = nList.get(position);
        holder.mTextView.setText(oList.getSongName());
        holder.mArtistView.setText(oList.getSongAlbumName());
        holder.mTimeView.setText(oList.getSongDuration());

        Picasso.with(ctx)
                .load(Uri.parse("file://" + R.drawable.logo))
                .error(R.drawable.ic_search)
                .into(holder.playpauseButton);

        holder.playpauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
    }
    @Override
    public int getItemCount() {
        return nList.size();
    }
}
