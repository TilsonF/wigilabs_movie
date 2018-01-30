package fernandeztilson.com.wigilabs_movie.helpers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fernandeztilson.com.wigilabs_movie.models.Movie;
import fernandeztilson.com.wigilabs_movie.views.fragments.FragmentPopular;
import fernandeztilson.com.wigilabs_movie.views.fragments.FragmentTop;

/**
 * Created by Tilson on 29/01/2018.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int numTabs;
    private Movie moviePopular;
    private Movie movieTop;


    public PagerAdapter(FragmentManager fm, int numTabs, Movie moviePopular,
                        Movie movieTop) {
        super(fm);
        this.numTabs = numTabs;
        this.moviePopular = moviePopular;
        this.movieTop = movieTop;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FragmentPopular tab1 = new FragmentPopular();
                return tab1;
            case 1:
                FragmentTop tab2 = new FragmentTop();
                return tab2;

            default:
                throw new RuntimeException("Tab position invalid " + position);
        }
    }

    @Override
    public int getCount() {
        return numTabs;
    }

}
