package org.gestionbibliotheque;

import org.gestionbibliotheque.dao.BookDAO;
import org.gestionbibliotheque.dto.BookDTO;
import org.gestionbibliotheque.model.Book;
import org.gestionbibliotheque.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class BookServiceTest {
    private BookService bookService;
    private final BookDAO bookDAO;

    public BookServiceTest(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookDAO);
    }

    @Test
    void testAddBook() {
        Book book = Book.builder()
                .id(1L)
                .title("Java Programming")
                .author("John Doe")
                .isAvailable(true)
                .build();
        bookService.addBook(book);
        Assertions.assertEquals(1, bookDAO.findAll().size());
        Assertions.assertEquals("Java Programming", bookDAO.getBookById(1L).getTitle());
    }

    @Test
    void testUpdateBook() {
        Book book = Book.builder()
                .id(1L)
                .title("Java Programming")
                .author("John Doe")
                .isAvailable(true)
                .build();
        bookService.addBook(book);
        bookService.updateBook( new BookDTO("Advanced Java", "Jane Doe", "ou ud",2001,"hehe"),1L);
        Assertions.assertEquals("Advanced Java", bookDAO.getBookById(1L).getTitle());
        assertFalse(bookDAO.getBookById(1L).getIsAvailable());
    }

    @Test
    void testDeleteBook() {
        Book book = Book.builder()
                .id(1L)
                .title("Java Programming")
                .author("John Doe")
                .isAvailable(true)
                .build();
        bookService.addBook(book);
        bookService.deleteBook(book);
        Assertions.assertNull(bookDAO.getBookById(1L));
    }
}
