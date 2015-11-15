package com.shaiban.audioplayer.mplayer.custom_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shaiban.audioplayer.mplayer.main_fragment.FragmentArtist;
import com.shaiban.audioplayer.mplayer.R;

import java.util.List;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class recyclerview_adapter_artist extends RecyclerView.Adapter<recyclerview_adapter_artist.ViewHolder> {

    List<FragmentArtist.ItemArtist> nList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public final TextView mTextView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.artist_item_text_view);
        }
    }
    public recyclerview_adapter_artist(List<FragmentArtist.ItemArtist> items) {
        nList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.artist_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        FragmentArtist.ItemArtist oList = nList.get(position);
        holder.mTextView.setText(oList.getArtist());

}
    @Override
    public int getItemCount() {
        return nList.size();
    }
}
