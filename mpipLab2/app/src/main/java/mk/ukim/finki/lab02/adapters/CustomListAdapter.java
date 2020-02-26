package mk.ukim.finki.lab02.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import mk.ukim.finki.lab02.R;
import mk.ukim.finki.lab02.models.Movie;
import mk.ukim.finki.lab02.ui.MovieDetailsActivity;
import mk.ukim.finki.lab02.viewHolders.CustomListViewHolder;

import java.util.ArrayList;
import java.util.List;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListViewHolder>{

    public static final String MOVIE_ID = "movieID";
    private List<Movie> movies;
    private Context context;

    public CustomListAdapter(Context context) {
        this.movies = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public CustomListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.one_movieitem, viewGroup,false);
        CustomListViewHolder holder = new CustomListViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomListViewHolder movieViewHolder, int i) {
        final Movie movie = movies.get(i);
        movieViewHolder.bind(movie);
        movieViewHolder.getParent().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                intent.putExtra(MOVIE_ID, movie.getImdbID());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setMovies(List<Movie> movies){
        this.movies = movies;
        notifyDataSetChanged();
    }
}
