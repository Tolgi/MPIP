package mk.ukim.finki.lab02.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import mk.ukim.finki.lab02.async_task.DeleteAllAsyncTask;
import mk.ukim.finki.lab02.async_task.InsertAsyncTask;
import mk.ukim.finki.lab02.models.Movie;
import mk.ukim.finki.lab02.room.MovieDao;
import mk.ukim.finki.lab02.room.MovieDatabase;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<List<Movie>> movies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
        movies = movieDao.findAll();
    }

    public void insert(Movie movie) {
        new InsertAsyncTask(movieDao).execute(movie);
    }

    public void deleteAll() {
        new DeleteAllAsyncTask(movieDao).execute();
    }

    public LiveData<List<Movie>> getMovies() {
        return movies;
    }

}
