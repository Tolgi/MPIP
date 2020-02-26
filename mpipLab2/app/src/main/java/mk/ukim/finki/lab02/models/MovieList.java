package mk.ukim.finki.lab02.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {

    @SerializedName("Search")
    public List<Movie> movies;


    public MovieList(List<Movie> movies) {
        this.movies = movies;
    }
    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "movies=" + movies +
                '}';
    }
}
