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


    public Description() {

    }

    public Description(String descriptionText) {
        this.descriptionText = descriptionText;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Film film;

    @OneToOne
    private Series series;

    @Lob
    private String descriptionText;
}
