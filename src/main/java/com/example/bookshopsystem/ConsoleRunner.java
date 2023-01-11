package com.example.bookshopsystem;

import com.example.bookshopsystem.entity.Author;
import com.example.bookshopsystem.entity.Book;
import com.example.bookshopsystem.service.AuthorService;
import com.example.bookshopsystem.service.BookService;
import com.example.bookshopsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
@Transactional
public class ConsoleRunner implements CommandLineRunner {
    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;

    @Autowired
    public ConsoleRunner(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        //authorService.renderAuthors();
        //categoryService.renderCategories();
        //bookService.renderBooks();


        // Get all books after year 2000
        //exerciseOne();

        // Get all authors with one book after 1990
        //exerciseTwo();

        // Get all authors with their book count
        //exerciseTree();

        // Get all George Powell books
        //exerciseFour();

        System.out.println();
    }

    private void exerciseOne() throws Exception {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2000");
        Set<Book> booksAfterYear = this.bookService.findBooksAfterYear(date);
        booksAfterYear.forEach(record -> System.out.printf("%s  -> %s%n", record.getTitle(), record.getReleaseDate().toString()));
    }

    private void exerciseTwo() {
        Set<Author> authorsByBooksCount = this.authorService.getAuthorsByBooksCount();
        authorsByBooksCount.forEach(author -> System.out.printf("%s %s%n", author.getFirstName(), author.getLastName()));
    }

    private void exerciseTree() {
        Set<Author> allAuthorsWithBooksCount = this.authorService.getAllAuthorsWithBooksCount();
        allAuthorsWithBooksCount.forEach(author -> System.out.printf("%s %s %d%n", author.getFirstName(), author.getLastName(), author.getBooks().size()));
    }

    private void exerciseFour() {
        Set<Book> booksByAuthorNames = this.bookService.getBooksByAuthorNames();
        booksByAuthorNames.forEach(book -> System.out.printf("%s %s %d%n", book.getTitle(), book.getReleaseDate(), book.getCopies()));
    }
}
