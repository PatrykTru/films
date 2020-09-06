package com.tru.spring5.films.POJO;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String pictureUrl;
    @Lob
    private String newsTextShort;
    @Lob
    private String newsTextLong;

}

