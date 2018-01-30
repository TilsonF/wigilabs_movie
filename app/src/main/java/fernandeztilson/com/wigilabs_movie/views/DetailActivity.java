package fernandeztilson.com.wigilabs_movie.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fernandeztilson.com.wigilabs_movie.R;

public class DetailActivity extends AppCompatActivity {

    TextView title, release_date, overview, vote_count, vote_average;
    ImageView poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getBundleExtra("movie");

        title = (TextView) findViewById(R.id.title);
        release_date = (TextView) findViewById(R.id.date);
        overview = (TextView) findViewById(R.id.overview);
        vote_count = (TextView) findViewById(R.id.vote_count);
        vote_average = (TextView) findViewById(R.id.vote_average);
        poster = (ImageView) findViewById(R.id.poster);

        String posterUrl = getString(R.string.images_url) + bundle.getString("poster");
        Picasso.with(DetailActivity.this)
                .load(posterUrl)
                .into(poster);

        title.setText(bundle.getString("title"));
        release_date.setText(bundle.getString("release_date"));
        overview.setText(bundle.getString("overview"));
        vote_count.setText(String.valueOf(bundle.getInt("vote_count")));
        vote_average.setText(String.valueOf(bundle.getDouble("vote_average")));




    }
}
