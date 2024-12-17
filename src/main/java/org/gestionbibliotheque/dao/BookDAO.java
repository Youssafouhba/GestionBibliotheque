package org.gestionbibliotheque.dao;

import org.gestionbibliotheque.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookDAO extends JpaRepository<Book,Long> {
    Book getBookByIsbn(String isbn);
    Book getBookById(Long bookId);
    List<Book> findBooksByIsAvailable(Boolean isAvailable);
}
