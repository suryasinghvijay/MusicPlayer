package com.shaiban.audioplayer.mplayer.main_fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiban.audioplayer.mplayer.R;
import com.shaiban.audioplayer.mplayer.custom_adapter.recyclerview_adapter_artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class FragmentArtist extends Fragment {

    final List<ItemArtist> mList1 = new ArrayList<ItemArtist>();
    RecyclerView rv;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artist, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.artist_recycleview);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));
        rv.setHasFixedSize(true);


        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        String [] projection =
                {
                        MediaStore.Audio.Artists.ARTIST,
                        MediaStore.Audio.Artists.ARTIST_KEY
                };
        Cursor orderCursor = contentResolver.query(uri,projection,null,null,MediaStore.Audio.Artists.ARTIST + " ASC");
        while(orderCursor.moveToNext()) {
            mList1.add(new ItemArtist(orderCursor.getString(0), orderCursor.getString(1)));
        }
        rv.setAdapter(new recyclerview_adapter_artist(mList1));

        return rootView;
    }

    public static class ItemArtist {
        // long id;
        // String artist;
        // String title;
        String artist;
        //  int total_album_song;
        // long duration;
        //  String duration;
        String artist_art_path;

        //   Bitmap image;
        //    public Item(long id, String title, String artist, String album, String duration, String album_art_path) {
        public ItemArtist( String artist, String artist_art_path) {
            //  public Item( String title,String artist, String album,long duration) {
            //   this.id = id;
            //   this.artist = artist;
            //    this.title = title;
            this.artist = artist;
            //  this.duration = duration;
            this.artist_art_path=artist_art_path;
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

        public String getArtist() {
            return artist;
        }

        /*    public String getDuration() {
                 return duration;
             }
     */
        public String getArtist_art_path(){ return artist_art_path;}
        //     public String gettotal_album_song(){return total_album_song;}

    }
}
