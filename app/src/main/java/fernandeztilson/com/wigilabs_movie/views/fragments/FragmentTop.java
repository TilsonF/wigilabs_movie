package fernandeztilson.com.wigilabs_movie.views.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import fernandeztilson.com.wigilabs_movie.R;
import fernandeztilson.com.wigilabs_movie.helpers.ItemRecyclerViewAdapter;
import fernandeztilson.com.wigilabs_movie.models.Movie;
import fernandeztilson.com.wigilabs_movie.views.MainActivity;

/**
 * Created by Tilson on 29/01/2018.
 */

public class FragmentTop extends Fragment {
    private Movie movie;
    private FragmentPopular.OnListFragmentInteractionListener interactionListener;

    View view;
    private ImageView ic_btn_back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_top, container, false);

        movie = MainActivity.movieTop;

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragment_top);
        recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
        //recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.e("movie top size", movie.toString());
        recyclerView.setAdapter(new ItemRecyclerViewAdapter(movie, interactionListener, context));

        Log.e("top", "yes");


        return view;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * <p/>
     */
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Movie item);
    }
}
