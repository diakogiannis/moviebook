package com.diakogiannis.uel.moviebook.service;

import com.diakogiannis.uel.moviebook.enums.SortByEnum;
import com.diakogiannis.uel.moviebook.model.dto.MovieRatingDTO;
import com.diakogiannis.uel.moviebook.model.entity.movies.Movie;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Optional;

public interface   MovieService {
    Iterable<Movie> getMovies(SortByEnum sortBy, String publicIdentifier);


    @Transactional
    @Secured("ROLE_USER")
    Movie saveNewMovie(Movie movie, String username);

    @Secured("ROLE_USER")
    Map<Long, MovieRatingDTO> getUserMovieRatings(String username);

    Optional<Movie> findMovieWithUserDetails(Long movieId);

}
