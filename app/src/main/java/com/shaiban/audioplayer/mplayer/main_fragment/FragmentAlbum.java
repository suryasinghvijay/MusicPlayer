package com.shaiban.audioplayer.mplayer.main_fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.shaiban.audioplayer.mplayer.HidingScrollListener;
import com.shaiban.audioplayer.mplayer.R;
import com.shaiban.audioplayer.mplayer.custom_adapter.recyclerview_adapter_album;

import java.util.ArrayList;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class FragmentAlbum extends Fragment {
    final ArrayList<Item> mList = new ArrayList<Item>();
    TabLayout tablayout;
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_album, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.album_recycleview);
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rv.setHasFixedSize(true);

        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        String [] projection =
                {
                        MediaStore.Audio.Albums.ALBUM,
                        MediaStore.Audio.Albums.ALBUM_ART,
                        //    MediaStore.Audio.Media.ALBUM,
                        //    MediaStore.Audio.Media.DURATION,
                        //        MediaStore.Audio.Albums.ALBUM_ART
                        //	MediaStore.Audio.Media.

                };

        Cursor orderCursor = contentResolver.query(uri, projection, null, null, null);

        Log.i("fragment_album", "Query executed");

        while(orderCursor.moveToNext()) {

            mList.add(new Item(orderCursor.getString(0), orderCursor.getString(1)));

        }
        Log.i("fragment_album", "Fetched n added values to mList");
        /*rv.setAdapter(new recyclerview_adapter_album(mList));*/

        recyclerview_adapter_album adapter = new recyclerview_adapter_album(mList,
                new recyclerview_adapter_album.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent i = new Intent(getActivity(),AlbumSongListing.class);

                        Item oList = mList.get(position);
                        String aname = oList.getAlbum();
                        String album_art_path=oList.getalbum_art_path();

                        i.putExtra("EXTRA_ALBUM_NAME", aname);
                        i.putExtra("EXTRA_ALBUM_ART",album_art_path);

                        Bundle extra = new Bundle();
                        extra.putSerializable("objects", mList);

                        startActivity(i);
                    }
                });
        tablayout= (TabLayout)((AppCompatActivity)getActivity()).findViewById(R.id.tabLayout);
        rv.addOnScrollListener(new HidingScrollListener() {

            @Override
            public void onHide() {
                tablayout.animate().translationY(-tablayout.getHeight()).setInterpolator(new AccelerateInterpolator(2));
            }

            @Override
            public void onShow() {
                tablayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
            }
        });
        rv.setAdapter(adapter);

        return rootView;
    }

    public static class Item {
        // long id;
        // String artist;
        // String title;
        String album;
        //  int total_album_song;
        // long duration;
        //  String duration;
        String album_art_path;

        //   Bitmap image;
        //    public Item(long id, String title, String artist, String album, String duration, String album_art_path) {
        public Item( String album, String album_art_path) {
            //  public Item( String title,String artist, String album,long duration) {
            //   this.id = id;
            //   this.artist = artist;
            //    this.title = title;
            this.album = album;
            //  this.duration = duration;
            this.album_art_path=album_art_path;
            //      this.total_album_song=total_album_song;
            //     this.image = Songimage;
        }

     /*    public long getId() {
             return id;
         }
      */
    /*     public String getArtist() {
             return artist;
         }
 */
        //  public String getTitle() {
        //      return title;
        //   }

        public String getAlbum() {
            return album;
        }

        /*    public String getDuration() {
                return duration;
            }
    */
        public String getalbum_art_path(){ return album_art_path;}
        //     public String gettotal_album_song(){return total_album_song;}

    }
}