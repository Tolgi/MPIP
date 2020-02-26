package mk.ukim.finki.lab02.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import mk.ukim.finki.lab02.async_task.UpdateAsyncTask;
import mk.ukim.finki.lab02.models.Movie;
import mk.ukim.finki.lab02.room.MovieDao;
import mk.ukim.finki.lab02.room.MovieDatabase;

public class MovieDetailsViewModel extends AndroidViewModel {

    private MovieDao movieDao;
    private LiveData<Movie> movie;

    public MovieDetailsViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getMovieDatabase(application.getApplicationContext()).movieDao();
    }

    public void setMovie(String movieId){
        movie = movieDao.findById(movieId);
    }

    public void update(Movie movie){
        new UpdateAsyncTask(movieDao).execute(movie);
    }

    public LiveData<Movie> getMovie(){
        return movie;
    }

}

