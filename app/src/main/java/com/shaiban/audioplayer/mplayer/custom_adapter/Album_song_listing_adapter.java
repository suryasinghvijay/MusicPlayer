package com.shaiban.audioplayer.mplayer.custom_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shaiban.audioplayer.mplayer.R;
import com.shaiban.audioplayer.mplayer.main_fragment.AlbumSongListing;

import java.util.ArrayList;

/**
 * Created by Mohammed on 9/8/2015.
 */


        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.TextView;

        import com.shaiban.audioplayer.mplayer.main_fragment.FragmentSongs;
        import com.shaiban.audioplayer.mplayer.R;
        import com.shaiban.audioplayer.mplayer.music_attributes.SongAttributeList;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class Album_song_listing_adapter extends RecyclerView.Adapter<Album_song_listing_adapter.ViewHolder> {
    public static Context ctx=null;
    ArrayList<AlbumSongListing.ItemAlbumListing> nList;
    /*private OnItemClickListener mOnItemClickListener;*/

   /* public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }*/
    public static class ViewHolder extends RecyclerView.ViewHolder  {

        public final View mView;

        public final TextView mTextView;


        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.textview1);
        }


    }

    public Album_song_listing_adapter(ArrayList<AlbumSongListing.ItemAlbumListing> items) {
        /*mOnItemClickListener = onItemClickListener;*/
        nList = items;
    }

    //Execution of adapter class starts from onCreateViewHolder()
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ctx= parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_song_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final AlbumSongListing.ItemAlbumListing oList = nList.get(position);
        holder.mTextView.setText(oList.getSong());
       /* holder.mArtistView.setText(oList.getSongAlbumName());
        holder.mTimeView.setText(oList.getSongDuration());
        holder.playpauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });*/
        /*viewHolder.container.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
*/


    }
    @Override
    public int getItemCount() {
        return nList.size();
    }
}
