package com.shaiban.audioplayer.mplayer.design_helpers;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mohammed on 8/26/2015.
 */
public class TabBarBehaviour extends CoordinatorLayout.Behavior<TabLayout> {

    private int toolbarHeight;

    public TabBarBehaviour(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.toolbarHeight = Utils.getToolbarHeight(context);
    }


    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent,TabLayout tab, View dependency) {
        return dependency instanceof AppBarLayout;
    }

}

