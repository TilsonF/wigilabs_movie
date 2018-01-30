package fernandeztilson.com.wigilabs_movie.views;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;

import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fernandeztilson.com.wigilabs_movie.R;
import fernandeztilson.com.wigilabs_movie.helpers.MoviesHelper;
import fernandeztilson.com.wigilabs_movie.helpers.PagerAdapter;
import fernandeztilson.com.wigilabs_movie.models.Movie;

public class MainActivity extends AppCompatActivity {

    public static Movie moviePopular = null, movieTop = null, movieSearch = null;
    public static TabLayout tabLayout = null;
    public static ViewPager viewPager = null;
    public static PagerAdapter adapter = null;
    public static MainActivity mainActivity = null;
    /**
     * ProgressDialog for progress dialog
     */
    public static ProgressDialog progressDialog;
    public static BottomSheetBehavior sheetBehavior;
    public static LinearLayout bottom_sheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mainActivity = this;
        // define TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_tab0));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.title_tab1));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        // define ViewPager
        viewPager = (ViewPager) findViewById(R.id.pager);


        bottom_sheet = (LinearLayout) findViewById(R.id.bottom_sheet);
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState) {
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED: {
                        //btnBottomSheet.setText("Close Sheet");
                    }
                    break;
                    case BottomSheetBehavior.STATE_COLLAPSED: {
                        //btnBottomSheet.setText("Expand Sheet");
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

                    }
                    break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        break;
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("ESPERE POR FAVOR...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        /** url for API */
        String urlApi, api_key;
        urlApi = getString(R.string.videos_api);
        api_key = getString(R.string.api_key);
        MoviesHelper moviesHelper = new MoviesHelper(urlApi);
        try {
            Log.e("moviesHelper ", "yes");
            moviesHelper.moviesPopularCall(mainActivity, api_key);
        } catch (Exception e) {
            e.printStackTrace();
        }

       /* if(moviePopular == null){
            Log.e("moviepopular is null", "yes");
            Intent intent = new Intent(MainActivity.this, SplashActivity.class);
            startActivity(intent);
        }else{

        } Log.e("moviepopular is null", "no");*/


    }

    /**
     * Listener for tab selected
     *
     * @param viewPager
     * @return
     */
    @NonNull
    private TabLayout.OnTabSelectedListener getOnTabSelectedListener(final ViewPager viewPager) {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                //Toast.makeText(MainActivity.this, "Tab selected " +  tab.getPosition(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // nothing now
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // nothing now
            }
        };
    }


    public void init() {
        //  ViewPager need a PagerAdapter
        adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), moviePopular, movieTop);

        viewPager.setAdapter(adapter);

        // Listeners
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(getOnTabSelectedListener(viewPager));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mainactivity, menu);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                Log.e("new text submit", query);

                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("BUSCANDO... "+query);
                progressDialog.setCancelable(false);
                progressDialog.show();

                /** url for API */
                String urlApi, api_key;
                urlApi = getString(R.string.videos_api);
                api_key = getString(R.string.api_key);
                MoviesHelper moviesHelper = new MoviesHelper(urlApi);
                try {
                    Log.e("moviesHelper ", "yes");
                    moviesHelper.moviesSearchCall(mainActivity, api_key, query);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                Log.e("new text", newText);
                //adapter.getFilter().filter(newText);
                return true;
            }
        });
    }
}




