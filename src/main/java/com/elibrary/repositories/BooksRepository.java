package com.elibrary.repositories;

import com.elibrary.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class BooksRepository implements JpaRepository<Book, Integer> {
}
