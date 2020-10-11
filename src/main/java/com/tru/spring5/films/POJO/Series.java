package com.tru.spring5.films.POJO;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class Series {

    public Series() {
    }

    public Series(String title, int seasons, String posterUrl, Double rating, Integer yearOfPublishing, Description description) {
        this.title = title;
        this.seasons = seasons;
        this.posterUrl = posterUrl;
        this.rating = rating;
        this.yearOfPublishing = yearOfPublishing;
        this.description = description;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int rank;
    private String title;
    private int seasons;
    private String posterUrl;
    private Double rating;
    private Integer yearOfPublishing;


    @OneToOne(cascade = CascadeType.ALL)
    private Description description;


    @ManyToMany
    @JoinTable(name = "series_category",
            joinColumns = @JoinColumn(name = "series_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();


}
