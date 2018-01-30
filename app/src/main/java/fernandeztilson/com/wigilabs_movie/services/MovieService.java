package fernandeztilson.com.wigilabs_movie.services;

import fernandeztilson.com.wigilabs_movie.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Tilson on 29/01/2018.
 */

public interface MovieService {
    //https://api.themoviedb.org/3/movie/popular?api_key=33b17ab57b26981fc9b738094617084d&language=es-CO&page=1
    //https://api.themoviedb.org/3/movie/top_rated?api_key=33b17ab57b26981fc9b738094617084d&language=es-CO&page=1
    //https://api.themoviedb.org/3/search/movie?api_key=33b17ab57b26981fc9b738094617084d&language=es-CO&query=query&page=1&include_adult=false


    @GET("movie/popular")
    Call<Movie> getMoviesPopupar(@Query("api_key") String api_key, @Query("language") String language, @Query("page") int page);

    @GET("movie/top_rated")
    Call<Movie> getMoviesTop(@Query("api_key") String api_key, @Query("language") String language, @Query("page") int page);

    @GET("search/movie")
    Call<Movie> getMovieSearch(@Query("api_key") String api_key, @Query("language") String language, @Query("query") String query, @Query("page") int page, @Query("include_adult") String include_adult);
}
