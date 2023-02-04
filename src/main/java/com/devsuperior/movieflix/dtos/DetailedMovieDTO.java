package com.devsuperior.movieflix.dtos;

import com.devsuperior.movieflix.entities.Movie;

public class DetailedMovieDTO extends MovieDTO {
	private static final long serialVersionUID = 1L;

	private String synopsis;
	private GenreDTO genre;

	public DetailedMovieDTO() {
	}

	public DetailedMovieDTO(Long id, String title, String subTitle, Integer year, String imgUrl, String synopsis,
			GenreDTO genre) {
		super(id, title, subTitle, year, imgUrl);
		this.synopsis = synopsis;
		this.genre = genre;
	}

	public DetailedMovieDTO(Movie movie) {
		super(movie);
		synopsis = movie.getSynopsis();
		genre = new GenreDTO(movie.getGenre());
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public GenreDTO getGenre() {
		return genre;
	}

	public void setGenre(GenreDTO genre) {
		this.genre = genre;
	}
}
