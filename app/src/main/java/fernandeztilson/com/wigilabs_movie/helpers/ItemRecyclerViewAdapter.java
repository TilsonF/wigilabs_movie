package fernandeztilson.com.wigilabs_movie.helpers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fernandeztilson.com.wigilabs_movie.R;
import fernandeztilson.com.wigilabs_movie.models.Movie;
import fernandeztilson.com.wigilabs_movie.views.DetailActivity;
import fernandeztilson.com.wigilabs_movie.views.fragments.FragmentPopular;

/**
 * Created by Tilson on 29/01/2018.
 */

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ViewHolder> {

    private final Movie movies;
    private Context context;
    private final FragmentPopular.OnListFragmentInteractionListener interactionListener;

    public ItemRecyclerViewAdapter(Movie items, FragmentPopular.OnListFragmentInteractionListener listener, Context context) {
        movies = items;
        interactionListener = listener;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public ImageView poster;
        //public final TextView idView;
        public final TextView titleView;
        // public final TextView detailView;
        public final LinearLayout linearLayout;
        public Movie movie;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            //idView = (TextView) view.findViewById(R.id.id);
            titleView = (TextView) view.findViewById(R.id.title);
            //detailView = (TextView) view.findViewById(R.id.detail);
            poster = (ImageView) view.findViewById(R.id.poster_movie);
            linearLayout = (LinearLayout) view.findViewById(R.id.linear);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_list, parent, false);


        ViewHolder myViewHolder = new ViewHolder(view);
        Log.e("movies viewholder", movies.toString());
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Log.e("position", position + "");

        //holder.idView.setText(movies.getResults().get(position).getId());
        holder.titleView.setText(movies.getResults().get(position).getTitle());
        //holder.detailView.setText(movies.getResults().get(position).getOverview());
        String posterUrl = context.getString(R.string.images_url) + movies.getResults().get(position).getPosterPath();
        Picasso.with(context)
                .load(posterUrl)
                .into(holder.poster);

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("vote_count", movies.getResults().get(position).getVoteCount());
                bundle.putDouble("vote_average", movies.getResults().get(position).getVoteAverage());
                bundle.putString("title", movies.getResults().get(position).getTitle());
                bundle.putString("overview", movies.getResults().get(position).getOverview());
                bundle.putString("release_date", movies.getResults().get(position).getReleaseDate());
                bundle.putString("poster", movies.getResults().get(position).getBackdropPath());
                intent.putExtra("movie", bundle);

                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.getResults().size();
    }
}
