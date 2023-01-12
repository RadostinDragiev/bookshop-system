package com.example.bookshopsystem.service;

import com.example.bookshopsystem.entity.Book;
import com.example.bookshopsystem.enums.AgeRestriction;
import com.example.bookshopsystem.enums.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

public interface BookService {
    void renderBooks() throws IOException;

    Set<Book> findBooksAfterYear(LocalDate year);

    Set<Book> getBooksByAuthorNames();

    Set<Book> getAllBooksByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> getAllBooksByCopies(EditionType editionType, int copies);

    Set<Book> getAllBooksInPriceRange(BigDecimal lessThan, BigDecimal greaterThan);

    Set<Book> getAllBooksByReleaseYear(String year);

    Set<Book> getAllBooksBeforeDate(LocalDate before);

    Set<Book> getAllBooksByTitlePart(String word);

    Set<Book> getAllBooksAuthorLastNameStartsWith(String word);

    int getBooksCountWithLongerTitlesThan(int size);

    Set<String> getBookInformationByTitle(String title);
}
