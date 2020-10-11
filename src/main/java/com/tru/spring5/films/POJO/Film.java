package com.tru.spring5.films.POJO;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Film {

    public Film() {
    }

    public Film(String title, Double rating, Integer yearOfPublishing, Duration movieDuration, String posterUrl, Description description) {
        this.title = title;
        this.rating = rating;
        this.yearOfPublishing = yearOfPublishing;
        this.movieDuration = movieDuration;
        this.posterUrl = posterUrl;
        this.description = description;
    }

    public Film(String title, Double rating, Integer yearOfPublishing, Duration movieDuration,  String posterUrl, Description description,String trailerLink) {
        this.title = title;
        this.rating = rating;
        this.yearOfPublishing = yearOfPublishing;
        this.movieDuration = movieDuration;
        this.trailerLink = trailerLink;
        this.posterUrl = posterUrl;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rank;
    private String title;
    private Double rating;
    private Integer yearOfPublishing;
    private Duration movieDuration;
    private String trailerLink;
    private String posterUrl;

    @OneToOne(cascade = CascadeType.ALL)
    private Description description;

    @ManyToMany
    @JoinTable(name = "film_category",
            joinColumns = @JoinColumn(name = "film_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


}
