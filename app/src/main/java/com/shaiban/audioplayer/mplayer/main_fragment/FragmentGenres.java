package com.shaiban.audioplayer.mplayer.main_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiban.audioplayer.mplayer.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohammed on 8/25/2015.
 */
public class FragmentGenres extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rv = inflater.inflate(R.layout.fragment_genres, container, false);

        return rv;
    }
}
