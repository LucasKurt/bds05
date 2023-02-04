package com.devsuperior.movieflix.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dtos.DetailedMovieDTO;
import com.devsuperior.movieflix.dtos.MovieDTO;
import com.devsuperior.movieflix.dtos.ReviewDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;

@Service
public class MovieService {

	@Autowired
	private MovieRepository repository;
	
	@Autowired
	private GenreRepository genreRepository;
	
	@Transactional(readOnly = true)
	public Page<MovieDTO> findAllByGenre(Long genreId, Pageable pageable) {
		Genre genre = genreId == 0 ? null : genreRepository.getOne(genreId);
		
		return repository.findAllByGenre(genre, pageable).map(x -> new MovieDTO(x));
	}
	
	@Transactional(readOnly = true)
	public DetailedMovieDTO findById(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie movie = obj.orElseThrow(() -> new EntityNotFoundException("Unable to find movie with id " + id));
		
		return new DetailedMovieDTO(movie);
	}
	
	@Transactional(readOnly = true)
	public List<ReviewDTO> findAllReviewsByMovie(Long id) {
		Optional<Movie> obj = repository.findById(id);
		Movie movie = obj.orElseThrow(() -> new EntityNotFoundException("Unable to find movie with id " + id));
		
		return movie.getReviews().stream().map(x -> new ReviewDTO(x)).toList();
	}
}
