package com.diakogiannis.uel.moviebook.service;

import com.diakogiannis.uel.moviebook.exceptions.MovieDoesNotExistException;
import com.diakogiannis.uel.moviebook.exceptions.MovieSelfVoteException;
import com.diakogiannis.uel.moviebook.exceptions.RatingDoesNotExistException;
import com.diakogiannis.uel.moviebook.enums.LikeEnum;
import com.diakogiannis.uel.moviebook.model.entity.movies.Movie;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RatingService {
    @Secured("ROLE_USER")
    @Transactional
    void castVote(LikeEnum action, String username, Optional<Movie> movie) throws MovieDoesNotExistException, MovieSelfVoteException, RatingDoesNotExistException;


}
