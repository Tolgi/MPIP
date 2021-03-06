package mk.ukim.finki.lab02.async_task;

import android.os.AsyncTask;
import mk.ukim.finki.lab02.models.Movie;
import mk.ukim.finki.lab02.room.MovieDao;

public class UpdateAsyncTask extends AsyncTask<Movie, Void, Void> {

    private MovieDao movieDao;

    public UpdateAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }

    @Override
    protected Void doInBackground(Movie... movies) {
        movieDao.update(movies[0]);
        return null;
    }
}