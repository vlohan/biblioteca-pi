package com.ifrn.biblioteca.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Student student;
    @NotNull
    @ManyToOne
    private Book book;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate loanDate;

    public Loan() {
    }

    public Loan(Long id, Student student, Book book, LocalDate loanDate) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.loanDate = loanDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }
}
