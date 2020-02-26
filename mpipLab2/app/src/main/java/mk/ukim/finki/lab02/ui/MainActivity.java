package mk.ukim.finki.lab02.ui;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import mk.ukim.finki.lab02.R;
import mk.ukim.finki.lab02.adapters.CustomListAdapter;
import mk.ukim.finki.lab02.models.Movie;
import mk.ukim.finki.lab02.models.MovieList;
import mk.ukim.finki.lab02.omdb_api.GetMovieService;
import mk.ukim.finki.lab02.omdb_api.RetrofitClientInstance;
import mk.ukim.finki.lab02.viewmodels.MovieViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class MainActivity extends AppCompatActivity {


    private SearchView searchView;
    private GetMovieService omdbApiCalls;
    private MovieViewModel movieViewModel;
    private CustomListAdapter adapter;
    private RecyclerView movieRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final CustomListAdapter movieAdapter = new CustomListAdapter(this);
        movieRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_1);
        movieRecyclerView.setAdapter(movieAdapter);
        movieRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        omdbApiCalls = RetrofitClientInstance.getRetrofitInstance().create(GetMovieService.class);
        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.getMovies().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                if (movies != null) {
                    movieAdapter.setMovies(movies);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        searchView = (SearchView) menu.findItem(R.id.menu_item1).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                syncData(s.trim());
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }

    public void syncData(String query){
        Call<MovieList> call = omdbApiCalls.getAllMovies("461c964c", query);
        call.enqueue(new Callback<MovieList>() {

            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                movieViewModel.deleteAll();

                if(response.isSuccessful()) {
                    List<Movie> movies = response.body().getMovies();
                    for (Movie movie : movies) {
                        movieViewModel.insert(movie);
                    }
                }else {
                    System.out.println("CODE: " + response.code());
                    return;
                }
            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {

            }
        });
    }


}
