package com.shaiban.audioplayer.mplayer;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by Mohammed on 8/27/2015.
 */
public class ActivityPlayView  extends AppCompatActivity {
    ImageView image_view_close;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_view);

      /*  Intent i = getIntent();
        String eSongALbum = i.getStringExtra("EXTRA_SONG_ALBUM");
        String ePosition = i.getStringExtra("EXTRA_SONG_POSITION");
        String albumArt=retrieveAlbumArt(eSongALbum);

*/




        image_view_close = (ImageView)findViewById(R.id.closed_button_pressed);
        image_view_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ActivityPlayView.this,MainActivity.class);
                overridePendingTransition(R.anim.animation_close,R.anim.animation_open);
                startActivity(i);
                return;
            }
        });

    }
    public String retrieveAlbumArt(String aname){
        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;

        String[] columns = {
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ALBUM_ART,};
        String where = android.provider.MediaStore.Audio.Albums.ALBUM
                + "=?";
        String whereVal[] = {aname};
        String orderBy = MediaStore.Audio.Albums.ALBUM;
        Cursor orderCursor = contentResolver.query(uri, columns, where, whereVal, orderBy);
        if(orderCursor.moveToNext()){
            return orderCursor.getString(1);
        }
        return null;
    }




    @Override
    public void onBackPressed() {
        finish();
        Intent i = new Intent(ActivityPlayView.this,MainActivity.class);
        overridePendingTransition(R.anim.animation_close, R.anim.animation_open);
        startActivity(i);
        return;
    }
}
