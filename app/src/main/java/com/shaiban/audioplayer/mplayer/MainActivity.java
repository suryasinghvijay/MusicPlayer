package com.shaiban.audioplayer.mplayer;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionMenu;
import com.shaiban.audioplayer.mplayer.custom_adapter.PagerAdapter;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentAlbum;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentArtist;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentFile;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentGenres;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentPlaylist;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentSongs;
import com.shaiban.audioplayer.mplayer.main_fragment.FragmentSuggested;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    FloatingActionMenu myFab;

    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        navigationbar();



        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FragmentSongs(), "SONGS");
        adapter.addFragment(new FragmentAlbum(), "ALBUM");
        adapter.addFragment(new FragmentArtist(), "ARTIST");
        adapter.addFragment(new FragmentGenres(), "WHATSAPP");
        adapter.addFragment(new FragmentPlaylist(), "PLAYLIST");
        adapter.addFragment(new FragmentSuggested(), "SUGGESTED");
        adapter.addFragment(new FragmentFile(), "Files");

        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

   /*     collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        collapsingToolbarLayout.setTitle("Dream Player");
*/
        /*myFab = (FloatingActionMenu) findViewById(R.id.fabButton);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                 Intent in = new Intent(MainActivity.this,ActivityPlayView.class );
                overridePendingTransition(R.anim.animation_open,R.anim.animation_close);
                 startActivity(in);
            }
        });*/


    }

    public void navigationbar()
    {
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.inbox:
                        Toast.makeText(getApplicationContext(), "Inbox Selected", Toast.LENGTH_SHORT).show();
                       /* ContentFragment fragment = new ContentFragment();
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.frame,fragment);
                        fragmentTransaction.commit();*/
                        return true;

                    // For rest of the options we just show a toast on click

                    case R.id.starred:
                        Toast.makeText(getApplicationContext(), "Stared Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.sent_mail:
                        Toast.makeText(getApplicationContext(), "Send Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.drafts:
                        Toast.makeText(getApplicationContext(), "Drafts Selected", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.allmail:
                        Toast.makeText(getApplicationContext(), "All Mail Selected", Toast.LENGTH_SHORT).show();
                        return true;
                   /* case R.id.trash:
                        Toast.makeText(getApplicationContext(),"Trash Selected",Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.spam:
                        Toast.makeText(getApplicationContext(),"Spam Selected",Toast.LENGTH_SHORT).show();*/
                       /* return true;*/
                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        return true;

                }
            }
        });
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
         ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer, R.string.close_drawer){

        @Override
        public void onDrawerClosed(View drawerView) {
            // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
            super.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

            super.onDrawerOpened(drawerView);
        }
    };

    //Setting the actionbarToggle to drawer layout
    drawerLayout.setDrawerListener(actionBarDrawerToggle);

    //calling sync state is necessay or else your hamburger icon wont show up
    actionBarDrawerToggle.syncState();
}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    @Override
    public void onBackPressed()
    {
        finish();
    }
}