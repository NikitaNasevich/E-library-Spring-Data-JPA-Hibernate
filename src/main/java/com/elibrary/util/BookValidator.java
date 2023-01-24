package com.elibrary.util;

import com.elibrary.dao.BookDAO;
import com.elibrary.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    private final BookDAO bookDAO;

    @Autowired
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDAO.show(book.getTitle(), book.getAuthor()).isPresent()) {
            errors.rejectValue("title", "", "Книга с таким именем и автором уже зарегистрирована");
            errors.rejectValue("author", "", "Книга с таким именем и автором уже зарегистрирована");
        }
    }
}
