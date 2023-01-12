package com.example.bookshopsystem.repository;

import com.example.bookshopsystem.entity.Book;
import com.example.bookshopsystem.enums.AgeRestriction;
import com.example.bookshopsystem.enums.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Set<Book> findAllByReleaseDateAfter(LocalDate year);

    Set<Book> findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle(String firstName, String lastName);

    Set<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    Set<Book> findAllByEditionTypeAndCopiesIsLessThan(EditionType editionType, int copies);

    Set<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lessThan, BigDecimal greaterThan);

    Set<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate before, LocalDate after);

    Set<Book> findAllByReleaseDateBefore(LocalDate before);

    Set<Book> findAllByTitleIsContaining(String word);

    @Query("SELECT b FROM Book AS b LEFT JOIN Author AS a ON b.author.id = a.id WHERE a.lastName LIKE :word%")
    Set<Book> findAllByAuthorLastNameStartsWith(String word);

    @Query("SELECT count(b) FROM Book AS b WHERE LENGTH(b.title) > :size")
    int countBooksByTitleSize(int size);

    @Query("SELECT b.title, b.editionType, b.price FROM Book AS b WHERE b.title like %:title%")
    Set<String> getBookInformationWithTitle(String title);
}
