package com.example.bookshopsystem;

import com.example.bookshopsystem.service.AuthorService;
import com.example.bookshopsystem.service.BookService;
import com.example.bookshopsystem.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;

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
        //this.bookService.findBooksAfterYear(LocalDate.parse("01/01/2000", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
        //        .forEach(record -> System.out.printf("%s  -> %s%n", record.getTitle(), record.getReleaseDate().toString()));

        // Get all authors with one book after 1990
        //this.authorService.getAuthorsByBooksCount()
        //        .forEach(author -> System.out.printf("%s %s%n", author.getFirstName(), author.getLastName()));

        // Get all authors with their book count
        //this.authorService.getAllAuthorsWithBooksCount()
        //        .forEach(author -> System.out.printf("%s %s %d%n", author.getFirstName(), author.getLastName(), author.getBooks().size()));

        // Get all George Powell books
        //this.bookService.getBooksByAuthorNames()
        //        .forEach(book -> System.out.printf("%s %s %d%n", book.getTitle(), book.getReleaseDate(), book.getCopies()));

        // #Spring Data Advanced Quering
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        // 1. Books Titles by Age Restriction
        //this.bookService.getAllBooksByAgeRestriction(AgeRestriction.valueOf(input.toUpperCase()))
        //                .forEach(book -> System.out.println(book.getTitle()));

        // 2. Golden Books
        //this.bookService.getAllBooksByCopies(EditionType.GOLD, 5000)
        //        .forEach(book -> System.out.println(book.getTitle()));

        // 3. Books by Price
        //this.bookService.getAllBooksInPriceRange(new BigDecimal(5), new BigDecimal(40))
        //        .forEach(book -> System.out.printf("%s - %.2f%n", book.getTitle(), book.getPrice()));

        // 4. Not Released Books
        //this.bookService.getAllBooksByReleaseYear(input)
        //        .forEach(book -> System.out.println(book.getTitle()));

        // 5. Books Released Before Date
        //this.bookService.getAllBooksBeforeDate(LocalDate.parse(
        //        LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy")).
        //                format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
        //        .forEach(book -> System.out.printf("%s %s %.2f%n", book.getTitle(), book.getEditionType(), book.getPrice()));

        // 6. Authors Search
        //this.authorService.getAllAuthorsNamesEndingWith(input)
        //        .forEach(author -> System.out.printf("%s %s%n", author.getFirstName(), author.getLastName()));

        // 7. Books Search
        //this.bookService.getAllBooksByTitlePart(input)
        //        .forEach(book -> System.out.println(book.getTitle()));

        // 8. Book Titles Search
        //this.bookService.getAllBooksAuthorLastNameStartsWith(input)
        //        .forEach(book -> System.out.printf("%s (%s %s)%n", book.getTitle(), book.getAuthor().getFirstName(), book.getAuthor().getLastName()));

        // 9. Count Books
        //System.out.printf("There are %d books with longer title than %s symbols%n", this.bookService.getBooksCountWithLongerTitlesThan(Integer.parseInt(input)), input);

        // 10. Total Book Copies
        //this.authorService.getAllAuthorsWithAllBookCopies().forEach(record -> {
        //    String[] elements = record.split(",");
        //    System.out.printf("%s %s - %s%n", elements[0], elements[1], elements[2]);
        //});

        // 11. Reduced Book
        //this.bookService.getBookInformationByTitle(input)
        //        .forEach(book -> {
        //            String[] elements = book.split(",");
        //            System.out.printf("%s %s %.2f%n", elements[0], elements[1], Double.parseDouble(elements[2]));
        //        });
    }
}
