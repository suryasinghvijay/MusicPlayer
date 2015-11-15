package com.shaiban.audioplayer.mplayer.music_attributes;

import android.net.Uri;

/**
 * Created by Mohammed on 9/4/2015.
 */
public class SongAttributeList {
    private String mSongName, mSongAlbumName , mSongFullPath , mSongDuration ;
    private Uri mSongUri;
    private int mSongId;
    /*private String album_art;*/
    public SongAttributeList(){ }
    public SongAttributeList(String name , int id ,  String album_name , String full_path , String duration , Uri songuri ){
        this.mSongName = name;
        this.mSongId = id;
        this.mSongAlbumName = album_name;
        this.mSongFullPath = full_path;
        this.mSongDuration = duration;
        this.mSongUri = songuri;
    }
   /* public String getAlbum_art() {
        return album_art;
    }

    public void setAlbum_art(String album_art) {
        this.album_art = album_art;
    }*/

    public String getSongName() {
        return mSongName;
    }

    public void setSongName(String mSongName) {
        this.mSongName = mSongName;
    }

    public String getSongFullPath() {
        return mSongFullPath;
    }

    public void setSongFullPath(String mSongFullPath) {
        this.mSongFullPath = mSongFullPath;
    }

    public String getSongAlbumName() {
        return mSongAlbumName;
    }

    public void setSongAlbumName(String mSongAlbumName) {
        this.mSongAlbumName = mSongAlbumName;
    }

    public String getSongDuration() {
        return mSongDuration;
    }

    public void setSongDuration(String mSongDuration) {
        this.mSongDuration = mSongDuration;
    }

    public int getSongId() {
        return mSongId;
    }

    public void setSongId(int mSongId) {
        this.mSongId = mSongId;
    }

    public void setSongUri(Uri uri){ this.mSongUri = uri; }

    public Uri getSongUri(){
        return this.mSongUri;
    }
}
/*  long id;
    String artist;
    String title;
    String album;
    // long duration;
    String duration;
    //   Bitmap image;
    int data;
    private Uri mSongUri;

    public SongAttributeList(long id, String title, String artist, String album, String duration, int data, Uri songuri) {
        //  public Item( String title,String artist, String album,long duration) {
        this.id = id;
        this.artist = artist;
        this.title = title;
        this.album = album;
        this.duration = duration;
        this.data = data;
        this.mSongUri = songuri;
        //     this.image = Songimage;
    }

    public long getId() {
        return id;
    }

    public String getArtist() {
        return artist;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getDuration() {
        return duration;
    }

    public Uri getSongUri(){
        return mSongUri;
    }*/

