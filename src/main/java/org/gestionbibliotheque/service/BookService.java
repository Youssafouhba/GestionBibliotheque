package org.gestionbibliotheque.service;

import lombok.RequiredArgsConstructor;
import org.gestionbibliotheque.dao.BookDAO;
import org.gestionbibliotheque.dto.BookDTO;
import org.gestionbibliotheque.model.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookDAO bookDAO;

    public void addBook(Book book){
        book.setIsAvailable(true);
        bookDAO.save(book);
    }

    public List<Book> getAllBooks() {
        return bookDAO.findAll();
    }

    public Book getBookById(Long bookId){
        return bookDAO.getBookById(bookId);
    }

    public void deleteBook(Book book){
        bookDAO.delete(book);
    }

    public void updateBook(@NotNull BookDTO bookDTO, Long bookId){
        Book book = bookDAO.getBookById(bookId);
        Book.builder()
                .title(bookDTO.getTitle())
                .isbn(bookDTO.getIsbn())
                .year(bookDTO.getYear())
                .publisher(bookDTO.getPublisher())
                .build();
        bookDAO.save(book);
    }

    public List<Book> getAvailableBooks() {
        return bookDAO.findBooksByIsAvailable(true);
    }
}
