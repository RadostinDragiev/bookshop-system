package com.example.bookshopsystem.service.impl;

import com.example.bookshopsystem.entity.Book;
import com.example.bookshopsystem.entity.Category;
import com.example.bookshopsystem.enums.AgeRestriction;
import com.example.bookshopsystem.enums.EditionType;
import com.example.bookshopsystem.repository.BookRepository;
import com.example.bookshopsystem.service.AuthorService;
import com.example.bookshopsystem.service.BookService;
import com.example.bookshopsystem.service.CategoryService;
import com.example.bookshopsystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;
    private final FileUtil fileUtil;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService, FileUtil fileUtil) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void renderBooks() throws IOException {
        String[] input = fileUtil.readFile("src/main/resources/files/books.txt");

        Arrays.stream(input).forEach(row -> {
            String[] line = row.split("\\s+");

            Set<Category> categories = new HashSet<>();
            for (int i = 0; i < 2; i++) {
                categories.add(this.categoryService.getCategory());
            }

            EditionType editionType = EditionType.values()[Integer.parseInt(line[0])];
            SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy");
            Date releaseDate = null;
            try {
                releaseDate = formatter.parse(line [1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int copies = Integer.parseInt(line[2]);
            BigDecimal price = new BigDecimal(line[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(line[4])];
            StringBuilder titleBuilder = new StringBuilder();
            for (int i = 5; i < line.length; i++) {
                titleBuilder.append(line[i]).append(" ");
            }
            titleBuilder.delete(titleBuilder.lastIndexOf(" "), titleBuilder.lastIndexOf(" "));
            String title = titleBuilder.toString();

            Book book = new Book();
            book.setAuthor(this.authorService.getAuthor());
            book.setEditionType(editionType);
            book.setReleaseDate(releaseDate);
            book.setCopies(copies);
            book.setPrice(price);
            book.setAgeRestriction(ageRestriction);
            book.setTitle(title);
            book.setCategories(categories);

            this.bookRepository.saveAndFlush(book);
        });
    }

    @Override
    public Set<Book> findBooksAfterYear(Date year) {
        return this.bookRepository.findAllByReleaseDateAfter(year);
    }

    @Override
    public Set<Book> getBooksByAuthorNames() {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell");
    }
}
