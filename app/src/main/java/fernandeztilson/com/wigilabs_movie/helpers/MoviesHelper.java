package fernandeztilson.com.wigilabs_movie.helpers;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import fernandeztilson.com.wigilabs_movie.R;
import fernandeztilson.com.wigilabs_movie.models.Movie;
import fernandeztilson.com.wigilabs_movie.services.MovieService;
import fernandeztilson.com.wigilabs_movie.views.MainActivity;
import fernandeztilson.com.wigilabs_movie.views.SplashActivity;
import fernandeztilson.com.wigilabs_movie.views.fragments.FragmentPopular;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Usuario on 10/11/2017.
 */

public class MoviesHelper {
    MovieService movieService;
    public static Movie movieTop, moviePopular, movieSearch;
    private String urlApi;

    public MoviesHelper(String urlApi) {
        this.urlApi = urlApi;
        try {


            // Create connection to REST service
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(urlApi)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            // Create API connection
            this.movieService = retrofit.create(MovieService.class);

        } catch (UnsupportedOperationException e) {
            e.getMessage();
        }
    }

    public void moviesPopularCall(final MainActivity mainActivity, final String api_key) {
        try {
            Call<Movie> citiesCallData = movieService.getMoviesPopupar(api_key, "es-CO", 1);
            citiesCallData.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {

                    int statusCode = response.code();
                    Log.e("status code", "" + statusCode);
                    if (!response.isSuccessful()) {
                        mainActivity.progressDialog.dismiss();
                    } else {
                        Log.e("moviePopular", response.body().toString());
                        moviePopular = response.body();
                        mainActivity.moviePopular = moviePopular;

                        MoviesHelper moviesHelper = new MoviesHelper(urlApi);
                        try {
                            Log.e("moviesHelper ", "yes");
                            moviesHelper.moviesTopCall(MainActivity.mainActivity, api_key);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    mainActivity.progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mainActivity.progressDialog.dismiss();
        }

    }

    public void moviesTopCall(final MainActivity mainActivity, final String api_key) {
        try {
            Call<Movie> citiesCallData = movieService.getMoviesTop(api_key, "es-CO", 1);
            citiesCallData.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {

                    int statusCode = response.code();
                    Log.e("status code", "" + statusCode);
                    if (!response.isSuccessful()) {
                        mainActivity.progressDialog.dismiss();
                    } else {
                        Log.e("movieTop", response.body().toString());
                        movieTop = response.body();
                        mainActivity.movieTop = movieTop;


                        mainActivity.init();

                        //mainActivity.startActivity(new Intent(mainActivity.getClass()));

                        mainActivity.progressDialog.dismiss();

                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    mainActivity.progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mainActivity.progressDialog.dismiss();
        }

    }

    public void moviesSearchCall(final MainActivity mainActivity, final String api_key, final String query) {
        try {
            Call<Movie> citiesCallData = movieService.getMovieSearch(api_key, "es-CO", query, 1, "false");
            citiesCallData.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(Call<Movie> call, Response<Movie> response) {

                    Log.e("url", call.request().url().toString());
                    int statusCode = response.code();
                    Log.e("status code", "" + statusCode);
                    if (!response.isSuccessful()) {
                        mainActivity.progressDialog.dismiss();
                    } else {
                        Log.e("movieTop", response.body().toString());
                        movieSearch = response.body();

                        mainActivity.movieSearch = movieSearch;


                        mainActivity.init();
                        mainActivity.progressDialog.dismiss();


                        FragmentPopular.OnListFragmentInteractionListener interactionListener = null;

                        RecyclerView recyclerView = (RecyclerView) mainActivity.findViewById(R.id.search_movie);
                        recyclerView.setLayoutManager(new GridLayoutManager(mainActivity.getApplicationContext(), 2));
                        //recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        Log.e("movie search size", movieSearch.toString());
                        recyclerView.setAdapter(new ItemRecyclerViewAdapter(movieSearch, interactionListener, mainActivity.getApplicationContext()));


                        mainActivity.sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

                    }
                }

                @Override
                public void onFailure(Call<Movie> call, Throwable t) {
                    mainActivity.progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mainActivity.progressDialog.dismiss();
        }



    }




}
