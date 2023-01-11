package com.example.bookshopsystem.service;

import com.example.bookshopsystem.entity.Book;

import java.io.IOException;
import java.util.Date;
import java.util.Set;

public interface BookService {
    void renderBooks() throws IOException;

    Set<Book> findBooksAfterYear(Date year);

    Set<Book> getBooksByAuthorNames();
}
