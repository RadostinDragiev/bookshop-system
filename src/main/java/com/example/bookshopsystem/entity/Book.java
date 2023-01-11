package com.example.bookshopsystem.entity;

import com.example.bookshopsystem.enums.AgeRestriction;
import com.example.bookshopsystem.enums.EditionType;
 import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String title;
    @Column(length = 1000)
    private String description;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "edition_type", nullable = false)
    private EditionType editionType;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private int copies;
    @Column(name = "release_date")
    private Date releaseDate;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Category> categories;
}
