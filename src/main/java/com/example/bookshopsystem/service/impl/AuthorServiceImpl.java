package com.example.bookshopsystem.service.impl;

import com.example.bookshopsystem.entity.Author;
import com.example.bookshopsystem.repository.AuthorRepository;
import com.example.bookshopsystem.service.AuthorService;
import com.example.bookshopsystem.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final FileUtil fileUtil;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, FileUtil fileUtil) {
        this.authorRepository = authorRepository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void renderAuthors() throws IOException {
        String[] strings = this.fileUtil.readFile("src/main/resources/files/authors.txt");

        Arrays.stream(strings).forEach(row -> {
            String[] line = row.split("\\s+");
            Author author = new Author();
            author.setFirstName(line[0]);
            author.setLastName(line[1]);
            authorRepository.saveAndFlush(author);
        });

    }

    @Override
    public Author getAuthor() {
        Random random = new Random();
        long i = (long) random.nextInt((int) this.authorRepository.count()) + 1;
        return this.authorRepository.getReferenceById(i);
    }

    @Override
    public Set<Author> getAuthorsByBooksCount() {
        return this.authorRepository.findAllByBooksCount();
    }

    @Override
    public Set<Author> getAllAuthorsWithBooksCount() {
        return this.authorRepository.getAllAuthorsByBooksCountDesc();
    }

    @Override
    public Set<Author> getAllAuthorsNamesEndingWith(String endsWith) {
        return this.authorRepository.findAllByFirstNameEndingWith(endsWith);
    }

    @Override
    public Set<String> getAllAuthorsWithAllBookCopies() {
        return this.authorRepository.getAllAuthorsWithBookCopiesCount();
    }
}
