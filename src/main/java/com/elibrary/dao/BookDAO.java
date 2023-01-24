package com.elibrary.dao;

import com.elibrary.models.Book;
import com.elibrary.models.Person;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BookDAO {

//    private final JdbcTemplate jdbcTemplate;
//
//    public BookDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Book> index() {
//        return jdbcTemplate.query("SELECT * FROM Book", new BeanPropertyRowMapper<>(Book.class));
//    }
//
//    public Optional<Book> show(String title, String author) {
//        return jdbcTemplate.query("SELECT * FROM Book WHERE title=? AND author=?", new Object[]{title, author},
//                new BeanPropertyRowMapper<>(Book.class)).stream().findAny();
//    }
//
//    public Book show(int id) {
//        return jdbcTemplate.query("SELECT book.id, title, author, year, p.full_name FROM book LEFT JOIN person p ON book.person_id = p.id WHERE book.id=?",
//                        new Object[]{id}, new BeanPropertyRowMapper<>(Book.class))
//                .stream().findAny().orElse(null);
//    }
//
//    public void save(Book book) {
//        jdbcTemplate.update("INSERT INTO Book (title, author, year) VALUES(?, ?, ?)",
//                book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book updateBook) {
//        jdbcTemplate.update("UPDATE Book SET title=?, author=?, year=? WHERE id=?",
//                updateBook.getTitle(), updateBook.getAuthor(), updateBook.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("DELETE FROM Book WHERE id=?", id);
//    }
//
//
//
//    public void release(int id) {
//        jdbcTemplate.update("UPDATE book SET person_id=null WHERE id=?", id);
//    }
//
//    public void set(int personId, int bookId) {
//        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", personId, bookId);
//    }
}
