package com.shaiban.audioplayer.mplayer.main_fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shaiban.audioplayer.mplayer.R;

/**
 * Created by Mohammed on 8/28/2015.
 */
public class FragmentPlaylist extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rv = inflater.inflate(R.layout.fragment_playlist, container, false);

        return rv;
    }
}

