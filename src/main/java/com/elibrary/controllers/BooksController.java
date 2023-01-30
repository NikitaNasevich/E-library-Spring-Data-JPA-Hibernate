package com.elibrary.controllers;

import com.elibrary.models.Book;
import com.elibrary.models.Person;
import com.elibrary.services.BooksService;
import com.elibrary.services.PeopleService;
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

    private final BooksService booksService;
    private final PeopleService peopleService;
    private final BookValidator bookValidator;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService, BookValidator bookValidator) {
        this.booksService = booksService;
        this.peopleService = peopleService;
        this.bookValidator = bookValidator;
    }


    @GetMapping("")
    public String index(@RequestParam(value = "page", required = false) Integer page,
                        @RequestParam(value = "books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(value = "sort_by_year", required = false) boolean sortByYear,
                        Model model) {

        boolean sort = false;

        if (sortByYear) {
            sort = sortByYear;
        }

        if (page != null && booksPerPage != null && sort) {
            model.addAttribute("books", booksService.findAll(page, booksPerPage,true));
        } else if (page != null && booksPerPage != null) {
            model.addAttribute("books", booksService.findAll(page, booksPerPage));
        } else if (sort) {
            model.addAttribute("books", booksService.findAll(true));
        } else {
            model.addAttribute("books", booksService.findAll());
        }

        return "books/index";
    }

    @GetMapping("/search")
    public String search() {
        return "books/search";
    }

    @PostMapping("/search")
    public String makeSearch(Model model, @RequestParam("keyword") String keyword) {
        model.addAttribute("books", booksService.findByTitleStartWith(keyword));
        return "books/search";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping

    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        booksService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String show(@ModelAttribute("person") Person person, @PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findById(id));
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", booksService.findById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {

        bookValidator.validate(book, bindingResult);

        if (bindingResult.hasErrors()) {
            return "books/edit";
        }

        booksService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        booksService.release(id);
        return "redirect:/books/{id}";
    }

    @PatchMapping("/{id}/set")
    public String set(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        booksService.setOwner(person, id);
        return "redirect:/books/{id}";
    }


}
