package com.shaiban.audioplayer.mplayer.custom_adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shaiban.audioplayer.mplayer.main_fragment.FragmentAlbum;
import com.shaiban.audioplayer.mplayer.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class recyclerview_adapter_album  extends RecyclerView.Adapter<recyclerview_adapter_album.ViewHolder> {
    Context context;
    ArrayList<FragmentAlbum.Item> nList;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mTextView;
        public final ImageView mArtView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //mImageView = (ImageView) view.findViewById(R.id.avatar);
            mTextView = (TextView) view.findViewById(R.id.textview4);
            mArtView= (ImageView)view.findViewById(R.id.album_art_view);
        }
    }

    public recyclerview_adapter_album(ArrayList<FragmentAlbum.Item> items, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        nList = items;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context= parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.album_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        FragmentAlbum.Item oList = nList.get(position);

        holder.mTextView.setText(oList.getAlbum());

        Picasso.with(context)
                .load(Uri.parse("file://" + oList.getalbum_art_path()))
                .error(R.drawable.ic_search)
                .into(holder.mArtView);

        holder.mTextView.setOnClickListener(new View.OnClickListener() {
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
