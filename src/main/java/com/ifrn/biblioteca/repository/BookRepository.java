package com.ifrn.biblioteca.repository;

import com.ifrn.biblioteca.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
