package com.elibrary.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Название книги не может быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов")
    @Column(name = "title")
    private String title;
    @NotEmpty(message = "Автор не может быть пустым")
    @Size(min = 2, max = 100, message = "Название книги должно быть от 2 до 100 символов")
    @Column(name = "author")
    private String author;
    @Min(value = 1600)
    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    @Column(name = "booked_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookedAt;

    @Transient
    private boolean outOfDate;

    public Book() {
    }

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Date getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(Date bookedAt) {
        this.bookedAt = bookedAt;
    }

    public boolean isOutOfDate() {
        return outOfDate;
    }

    public void setOutOfDate(boolean outOfDate) {
        this.outOfDate = outOfDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
