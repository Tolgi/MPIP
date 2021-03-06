package mk.ukim.finki.lab02.room;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import mk.ukim.finki.lab02.models.Movie;

@Database(entities = Movie.class, version = 1, exportSchema = false)
abstract public class MovieDatabase extends RoomDatabase {


    public abstract MovieDao movieDao();
    private static MovieDatabase movieDatabase;

    public static MovieDatabase getMovieDatabase(Context context){
        if(movieDatabase == null){
            synchronized (MovieDatabase.class){
                if(movieDatabase == null){
                    movieDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, "book_database").build();
                }
            }
        }
        return movieDatabase;
    }
}