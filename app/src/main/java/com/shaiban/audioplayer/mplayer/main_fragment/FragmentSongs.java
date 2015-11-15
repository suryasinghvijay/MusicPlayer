package com.shaiban.audioplayer.mplayer.main_fragment;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.shaiban.audioplayer.mplayer.HidingScrollListener;
import com.shaiban.audioplayer.mplayer.MusicService;
import com.shaiban.audioplayer.mplayer.R;
import com.shaiban.audioplayer.mplayer.custom_adapter.recyclerview_adapter_songs;
import com.shaiban.audioplayer.mplayer.music_attributes.SongAttributeList;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mohammed on 8/25/2015.
 */

public class FragmentSongs extends Fragment {

    RecyclerView rv;
    private String[] STAR = {"*"};
    final ArrayList<SongAttributeList> songList = new ArrayList<>();
    private MusicService serviceMusic;
    private Intent playIntent;
    View rootView;
    TabLayout tablayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        rv = (RecyclerView) rootView.findViewById(R.id.songs_recycleview);
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext()));

        Log.i("fragment_song", "function starting");
        ContentResolver contentResolver = getActivity().getContentResolver();
        Uri uri = android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String selection = MediaStore.Audio.Media.IS_MUSIC + " != 0 ";

        Cursor cursor = contentResolver.query(uri, STAR, selection, null, MediaStore.Audio.Media.TITLE);
        while (cursor.moveToNext()) {
            SongAttributeList song = new SongAttributeList();
            String data = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            String[] res = data.split("\\.");
            song.setSongName(res[0]);
            song.setSongFullPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            song.setSongId(cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID)));
            song.setSongFullPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            song.setSongAlbumName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
            song.setSongUri(ContentUris.withAppendedId(
                    android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                    cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media._ID))));
            String duration = getDuration(Integer.parseInt(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))));
            song.setSongDuration(duration);
           /* String albumart= retrieveAlbumArt(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
            song.setAlbum_art(albumart);*/
            /*String albumId = cursor.getString(cursor
                    .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
            Uri sArtworkUri = Uri
                    .parse("content://media/external/audio/albumart");
            Uri albumArtUri = ContentUris.withAppendedId()
            String albumart= albumArtUri.toString();
            song.setAlbum_art(albumart);*/
            songList.add(song);


        }

        // rv.setAdapter(new recyclerview_adapter_songs(mList));
        recyclerview_adapter_songs adapter = new recyclerview_adapter_songs(songList,
                new recyclerview_adapter_songs.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        serviceMusic.setSelectedSong(position, MusicService.NOTIFICATION_ID);

                      /*  SongAttributeList sList = songList.get(position);
                        String songAlbumName= sList.getSongAlbumName();

                        Intent i = new Intent(getActivity(), ActivityPlayView.class);
                        i.putExtra("EXTRA_SONG_ALBUM", songAlbumName);
                        i.putExtra("EXTRA_SONG_POSITION",position);
                        startActivity(i);*/
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
    private void hideViews() {

        tablayout.animate().translationY(-tablayout.getHeight()).setInterpolator(new AccelerateInterpolator(2));

      //  FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) mFabButton.getLayoutParams();
      //  int fabBottomMargin = lp.bottomMargin;
      //  mFabButton.animate().translationY(mFabButton.getHeight()+fabBottomMargin).setInterpolator(new AccelerateInterpolator(2)).start();
    }

    private void showViews() {
        tablayout.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2));
    //    mFabButton.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
    }

    private ServiceConnection musicConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.PlayerBinder binder = (MusicService.PlayerBinder) service;
            //get servic1
            serviceMusic = binder.getService();
            serviceMusic.setSongList(songList);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    public void onStart() {
        super.onStart();
        //Start service
        if (playIntent == null) {
            playIntent = new Intent(getActivity(), MusicService.class);
            getActivity().bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(playIntent);
        }
    }

/*
    @Override
    public void onDestroy() {
        //Stop service
          */
/*  getActivity().stopService(playIntent);
        serviceMusic = null;*//*

        super.onDestroy();
    }
*/


    /*   private ServiceConnection musicConnection = new ServiceConnection(){

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MusicService.MusicBinder binder = (MusicService.MusicBinder)service;
            //get service
            musicSrv = binder.getService();
            //pass list
            musicSrv.setList(mList);
            musicBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            musicBound = false;
        }
    };

    @Override
    public void onStart() {
        super.onStart();
        if(playIntent==null){
            playIntent = new Intent(getActivity(), MusicService.class);
            getActivity().bindService(playIntent, musicConnection, Context.BIND_AUTO_CREATE);
            getActivity().startService(playIntent);
        }
    }
    @Override
    public void onDestroy() {
        getActivity().stopService(playIntent);
        musicSrv=null;
        super.onDestroy();
    }*/
    private static String getDuration(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        millis -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);

        StringBuilder sb = new StringBuilder(6);
        sb.append(minutes < 10 ? "0" + minutes : minutes);
        sb.append(":");
        sb.append(seconds < 10 ? "0" + seconds : seconds);
        //sb.append(" Secs");
        return sb.toString();
    }



/*

    public String convertDuration(long duration) {
        String out = null;
        long hours = 0;
        try {
            hours = (duration / 3600000);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return out;
        }

        Log.i("fragment_song", "Inside function");
        long remaining_minutes = (duration - (hours * 3600000)) / 60000;
        String minutes = String.valueOf(remaining_minutes);
        if (minutes.equals(0)) {
            minutes = "00";
        }
        long remaining_seconds = (duration - (hours * 3600000) - (remaining_minutes * 60000));
        String seconds = String.valueOf(remaining_seconds);
        if (seconds.length() < 2) {
            seconds = "00";
        } else {
            seconds = seconds.substring(0, 2);
        }

        if (hours > 0) {
            out = hours + ":" + minutes + ":" + seconds;
        } else {
            out = minutes + ":" + seconds;
        }

        return out;

    } //convert duration end*/


}