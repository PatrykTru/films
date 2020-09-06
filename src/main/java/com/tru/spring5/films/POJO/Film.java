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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private double rating;
    private int yearOfPublishing;
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
