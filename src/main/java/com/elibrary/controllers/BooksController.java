package com.elibrary.controllers;

import com.elibrary.dao.BookDAO;
import com.elibrary.dao.PersonDAO;
import com.elibrary.models.Book;
import com.elibrary.models.Person;
import com.elibrary.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

//    @GetMapping("")
//    public String index(Model model) {
//        model.addAttribute("books", bookDAO.index());
//        return "books/index";
//    }
//
//    @GetMapping("/new")
//    public String newPerson(@ModelAttribute("book") Book book) {
//        return "books/new";
//    }
//
//    @PostMapping
//
//    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
//
//        bookValidator.validate(book, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "books/new";
//        }
//
//        bookDAO.save(book);
//        return "redirect:/books";
//    }
//
//    @GetMapping("/{id}")
//    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int id, Model model) {
//        model.addAttribute("book", bookDAO.show(id));
//        model.addAttribute("people", personDAO.index());
//        return "books/show";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") int id) {
//        model.addAttribute("book", bookDAO.show(id));
//        return "books/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
//
//        bookValidator.validate(book, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "books/edit";
//        }
//
//        bookDAO.update(id, book);
//        return "redirect:/books";
//    }
//
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable("id") int id) {
//        bookDAO.delete(id);
//        return "redirect:/books";
//    }
//
//    @PatchMapping("/{id}/release")
//    public String release(@PathVariable("id") int id) {
//        bookDAO.release(id);
//        return "redirect:/books/{id}";
//    }
//
//    @PatchMapping("/{id}/set")
//    public String set(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
//        bookDAO.set(person.getId(), id);
//        return "redirect:/books/{id}";
//    }

}
