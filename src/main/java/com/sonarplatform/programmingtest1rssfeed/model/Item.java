package com.sonarplatform.programmingtest1rssfeed.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "news_article")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @Column(unique = true)
    private String url;

    private String content;
    private String summary;
    private Long articleTs;

    @DateTimeFormat(pattern = "dd-mm-yyyy")
    private LocalDate publishedDate;

    private LocalDateTime inserted;
    private LocalDateTime updated;
}


