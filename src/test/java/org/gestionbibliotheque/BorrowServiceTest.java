package org.gestionbibliotheque;

import org.gestionbibliotheque.dao.BookDAO;
import org.gestionbibliotheque.dao.BorrowDAO;
import org.gestionbibliotheque.dao.StudentDAO;
import org.gestionbibliotheque.model.Book;
import org.gestionbibliotheque.model.Borrow;
import org.gestionbibliotheque.model.Student;
import org.gestionbibliotheque.service.BorrowService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BorrowServiceTest {
    @Mock
    private BorrowDAO borrowDAO;

    @Mock
    private BookDAO bookDAO;

    @Mock
    private StudentDAO studentDAO;

    @InjectMocks
    private BorrowService borrowService;

    private Book book;
    private Student student;

    @BeforeEach
    void setUp() {
        // Prepare a book and a student for tests
        book = Book.builder()
                .id(1L)
                .title("Test Book")
                .isAvailable(true)
                .build();

        student = Student.builder()
                .id(1L)
                .name("Test Student")
                .build();
    }

    @Test
    void testReturnBook() {
        // Arrange
        Borrow borrow = Borrow.builder()
                .id(1L)
                .book(book)
                .student(student)
                .borrowDate(new Date())
                .build();

        // Configure mocks
        when(borrowDAO.findByStudentIdAndBookIdAndReturnDateIsNull(1L, 1L))
                .thenReturn(Optional.of(borrow));
        when(borrowDAO.save(ArgumentMatchers.any(Borrow.class))).thenReturn(borrow);

        // Act
        Borrow returnedBorrow = borrowService.returnBook(1L, 1L);

        // Assert
        Assertions.assertNotNull(returnedBorrow);
        Assertions.assertNotNull(returnedBorrow.getReturnDate());
        assertTrue(returnedBorrow.getBook().getIsAvailable());

        // Verify interactions
        verify(borrowDAO).findByStudentIdAndBookIdAndReturnDateIsNull(1L, 1L);
        verify(borrowDAO).save(ArgumentMatchers.any(Borrow.class));
    }

    @Test
    void testBorrowBookNotAvailable() {
        // Arrange
        book.setIsAvailable(false);

        when(bookDAO.findById(1L)).thenReturn(Optional.of(book));
        when(studentDAO.findById(1L)).thenReturn(Optional.of(student));

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> borrowService.borrowBook(1L, 1L),
                "Le livre n'est pas disponible.");
    }

    @Test
    void testBorrowBookStudentNotFound() {
        // Arrange
        when(bookDAO.findById(1L)).thenReturn(Optional.empty());
        when(studentDAO.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class,
                () -> borrowService.borrowBook(1L, 1L),
                "Étudiant ou livre non trouvé.");
    }

    @Test
    void testBorrowBookSuccessful() {
        // Arrange
        when(bookDAO.findById(1L)).thenReturn(Optional.of(book));
        when(studentDAO.findById(1L)).thenReturn(Optional.of(student));
        when(borrowDAO.save(ArgumentMatchers.any(Borrow.class))).thenAnswer(invocation -> {
            Borrow savedBorrow = invocation.getArgument(0);
            savedBorrow.setId(1L);
            return savedBorrow;
        });

        // Act
        Borrow borrow = borrowService.borrowBook(1L, 1L);

        // Assert
        Assertions.assertNotNull(borrow);
        Assertions.assertFalse(book.getIsAvailable());
        verify(bookDAO).save(book);
    }
}
