package com.elibrary.services;

import com.elibrary.models.Book;
import com.elibrary.models.Person;
import com.elibrary.repositories.BooksRepository;
import com.elibrary.repositories.PeopleRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public List<Book> findAll(int page, int size) {
        return booksRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public List<Book> findAll(int page, int size, boolean sort) {
        return booksRepository.findAll(PageRequest.of(page, size, Sort.by("year"))).getContent();
    }

    public List<Book> findAll(boolean sort) {
        return booksRepository.findAll(Sort.by("year"));
    }

    public Optional<Book> findByTitleAndAuthor(String title, String author) {
        return booksRepository.findByTitleAndAuthor(title, author).stream().findAny();
    }

    public List<Book> findByTitleStartWith(String startWithTitle) {
        return booksRepository.findByTitleStartingWith(startWithTitle);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    public Book findById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void setOwner(Person owner, int bookId) {
        booksRepository.findById(bookId).ifPresent(
                book -> {
                    book.setOwner(owner);
                    book.setBookedAt(new Date());
                }
        );
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setBookedAt(null);
                }
        );
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToUpdate = booksRepository.findById(id).get();

        updatedBook.setId(id);
        updatedBook.setOwner(bookToUpdate.getOwner());

        booksRepository.save(updatedBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }
}
