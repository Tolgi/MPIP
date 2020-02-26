package mk.ukim.finki.lab02.async_task;

import android.os.AsyncTask;
import mk.ukim.finki.lab02.room.MovieDao;

public class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
    private MovieDao movieDao;

    public DeleteAllAsyncTask(MovieDao movieDao){
        this.movieDao = movieDao;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        movieDao.deleteAll();
        return null;
    }
}