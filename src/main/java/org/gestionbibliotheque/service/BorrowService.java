package org.gestionbibliotheque.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gestionbibliotheque.dao.BookDAO;
import org.gestionbibliotheque.dao.BorrowDAO;
import org.gestionbibliotheque.dao.StudentDAO;
import org.gestionbibliotheque.model.Book;
import org.gestionbibliotheque.model.Borrow;
import org.gestionbibliotheque.model.Student;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowDAO borrowDAO;
    private final BookDAO bookDAO;
    private final StudentDAO studentDAO;

    @Transactional
    public Borrow borrowBook(Long studentId, Long bookId) {
        // Vérifier si le livre existe et est disponible
        Optional<Book> bookOptional = bookDAO.findById(bookId);
        Optional<Student> studentOptional = studentDAO.findById(studentId);

        if (bookOptional.isEmpty() || studentOptional.isEmpty()) {
            throw new RuntimeException("Étudiant ou livre non trouvé.");
        }

        Book book = bookOptional.get();
        Student student = studentOptional.get();

        if (!book.getIsAvailable()) {
            throw new RuntimeException("Le livre n'est pas disponible.");
        }

        // Créer l'emprunt
        Borrow borrow = new Borrow();
        borrow.setBook(book);
        borrow.setStudent(student);
        borrow.setBorrowDate(new Date());

        // Mettre à jour la disponibilité du livre
        book.setIsAvailable(false);
        bookDAO.save(book);

        return borrowDAO.save(borrow);
    }


    @Transactional
    public Borrow returnBook(Long studentId, Long bookId) {
        // Trouver l'emprunt actif
        Optional<Borrow> borrowOptional = borrowDAO.findByStudentIdAndBookIdAndReturnDateIsNull(studentId, bookId);

        if (borrowOptional.isEmpty()) {
            throw new RuntimeException("Aucun emprunt trouvé pour ce livre et cet étudiant.");
        }

        Borrow borrow = borrowOptional.get();
        borrow.setReturnDate(new Date());

        // Mettre à jour la disponibilité du livre
        Book book = borrow.getBook();
        book.setIsAvailable(true);
        bookDAO.save(book);

        return borrowDAO.save(borrow);
    }

    public List<Borrow> getAllBorrows() {
        return borrowDAO.findAll();
    }

    public List<Borrow> getActiveBorrows() {
        return borrowDAO.findByReturnDateIsNull();
    }
}
