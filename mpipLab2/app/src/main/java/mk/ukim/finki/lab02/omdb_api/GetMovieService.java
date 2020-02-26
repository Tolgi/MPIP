package mk.ukim.finki.lab02.omdb_api;

import mk.ukim.finki.lab02.models.MovieList;
import mk.ukim.finki.lab02.models.Movie;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetMovieService {

    @GET("/")
    Call<MovieList> getAllMovies(@Query("apikey") String apikey, @Query("s") String s);

    @GET("/?apikey=461c964c")
    Call<Movie> getMovieDetails(@Query("i") String movieId);
}
