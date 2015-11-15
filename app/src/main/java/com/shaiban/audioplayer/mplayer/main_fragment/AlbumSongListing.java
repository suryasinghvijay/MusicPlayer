package com.shaiban.audioplayer.mplayer.main_fragment;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.shaiban.audioplayer.mplayer.ActivityPlayView;
import com.shaiban.audioplayer.mplayer.R;
import com.shaiban.audioplayer.mplayer.custom_adapter.Album_song_listing_adapter;
import com.shaiban.audioplayer.mplayer.custom_adapter.recyclerview_adapter_artist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumSongListing extends AppCompatActivity {
    final ArrayList<ItemAlbumListing> albumSongList = new ArrayList<ItemAlbumListing>();
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_song_listing);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.transparent));
        collapsingToolbarLayout.setContentScrimColor(Color.BLUE);
        collapsingToolbarLayout.setStatusBarScrimColor(Color.GREEN);

       /* NestedScrollView scrollView = (NestedScrollView)findViewById(R.id.scroll);
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) scrollView.getLayoutParams();
        AppBarLayout.ScrollingViewBehavior behavior =
                (AppBarLayout.ScrollingViewBehavior) params.getBehavior();
        behavior.setOverlayTop(128); // Note: in pixels*/


        ContentResolver contentResolver = getContentResolver();
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Intent i = getIntent();
        String id = i.getStringExtra("EXTRA_ALBUM_NAME");
        String id1 = i.getStringExtra("EXTRA_ALBUM_ART");

        Toast.makeText(getApplicationContext(),""+id,Toast.LENGTH_LONG).show();

        String[] columns = {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.MIME_TYPE,};
        String where = android.provider.MediaStore.Audio.Media.ALBUM
                + "=?";

        String whereVal[] = {id};

        String orderBy = android.provider.MediaStore.Audio.Media.TITLE;

        Cursor orderCursor = contentResolver.query(uri, columns, where, whereVal, orderBy);

        while (orderCursor.moveToNext()) {
            albumSongList.add(new ItemAlbumListing(orderCursor.getString(2), orderCursor.getString(1)));

        }

        TextView tv =(TextView)findViewById(R.id.albumtitle);
        tv.setText(id);
        ImageView imageView = (ImageView)findViewById(R.id.image);
        Picasso.with(this)
                .load(Uri.parse("file://" + id1))

                .error(R.drawable.ic_search)
                .into(imageView);

        RecyclerView lv = (RecyclerView)findViewById(R.id.recycleview_album_song);
        lv.setLayoutManager(new LinearLayoutManager(lv.getContext()));
        lv.setHasFixedSize(true);

        lv.setAdapter(new Album_song_listing_adapter(albumSongList));


        FloatingActionButton myFab = (FloatingActionButton) findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent in = new Intent(AlbumSongListing.this, ActivityPlayView.class);
                overridePendingTransition(R.anim.animation_open, R.anim.animation_close);
                startActivity(in);
            }
        });

    }


    public static class ItemAlbumListing {
        // long id;
        // String artist;
        // String title;
        String song;
        //  int total_album_song;
        // long duration;
        //  String duration;
        String album_art_path;

        //   Bitmap image;
        //    public Item(long id, String title, String artist, String album, String duration, String album_art_path) {
        public ItemAlbumListing(String album, String album_art_path) {
            //  public Item( String title,String artist, String album,long duration) {
            //   this.id = id;
            //   this.artist = artist;
            //    this.title = title;
            this.song = album;
            //  this.duration = duration;
            this.album_art_path = album_art_path;
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

        public String getSong() {
            return song;
        }

        /*    public String getDuration() {
                return duration;
            }
    */
        public String getalbum_art_path() {
            return album_art_path;
        }
        //     public String gettotal_album_song(){return total_album_song;}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_album_song_listing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
