package com.tru.spring5.films.POJO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.Set;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"films"})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String category;

    @ManyToMany(mappedBy = "categories")
    private Set<Film> films ;
}
