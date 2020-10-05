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

    @Id
    @GeneratedValue
    private Long id;
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
