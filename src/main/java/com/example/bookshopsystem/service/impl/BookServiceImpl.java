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
import java.time.LocalDate;
import java.util.Arrays;
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
            LocalDate releaseDate = LocalDate.parse(line[1]);

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
    public Set<Book> findBooksAfterYear(LocalDate year) {
        return this.bookRepository.findAllByReleaseDateAfter(year);
    }

    @Override
    public Set<Book> getBooksByAuthorNames() {
        return this.bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitle("George", "Powell");
    }

    @Override
    public Set<Book> getAllBooksByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public Set<Book> getAllBooksByCopies(EditionType editionType, int copies) {
        return this.bookRepository.findAllByEditionTypeAndCopiesIsLessThan(editionType, copies);
    }

    @Override
    public Set<Book> getAllBooksInPriceRange(BigDecimal lessThan, BigDecimal greaterThan) {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(lessThan, greaterThan);
    }

    @Override
    public Set<Book> getAllBooksByReleaseYear(String yearStr) {
        int year = Integer.parseInt(yearStr);
        return this.bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(
                LocalDate.of(year, 1, 1),
                LocalDate.of(year, 12, 31));
    }

    @Override
    public Set<Book> getAllBooksBeforeDate(LocalDate before) {
        return this.bookRepository.findAllByReleaseDateBefore(before);
    }

    @Override
    public Set<Book> getAllBooksByTitlePart(String word) {
        return this.bookRepository.findAllByTitleIsContaining(word);
    }

    @Override
    public Set<Book> getAllBooksAuthorLastNameStartsWith(String word) {
        return this.bookRepository.findAllByAuthorLastNameStartsWith(word);
    }

    @Override
    public int getBooksCountWithLongerTitlesThan(int size) {
        return this.bookRepository.countBooksByTitleSize(size);
    }

    @Override
    public Set<String> getBookInformationByTitle(String title) {
        return this.bookRepository.getBookInformationWithTitle(title);
    }
}
