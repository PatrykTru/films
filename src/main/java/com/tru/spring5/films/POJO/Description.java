package com.tru.spring5.films.POJO;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@EqualsAndHashCode(exclude = {"film"})
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Film film;

    @Lob
    private String descriptionText;
}
