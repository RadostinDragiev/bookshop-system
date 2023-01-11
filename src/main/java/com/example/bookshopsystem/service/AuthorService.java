package com.example.bookshopsystem.service;

import com.example.bookshopsystem.entity.Author;

import java.io.IOException;
import java.util.Set;

public interface AuthorService {
    void renderAuthors() throws IOException;

    Author getAuthor();

    Set<Author> getAuthorsByBooksCount();

    Set<Author> getAllAuthorsWithBooksCount();
}
