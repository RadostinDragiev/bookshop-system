package com.example.bookshopsystem.repository;

import com.example.bookshopsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    @Query(value = "SELECT * FROM authors JOIN books b on authors.id = b.author_id WHERE release_date > '1990-01-01' ORDER BY  release_date DESC", nativeQuery = true)
    Set<Author> findAllByBooksCount();

    @Query("SELECT a FROM Author AS a ORDER BY a.books.size DESC")
    Set<Author> getAllAuthorsByBooksCountDesc();
}
