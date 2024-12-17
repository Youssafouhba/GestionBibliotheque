package org.gestionbibliotheque.controller;

import lombok.RequiredArgsConstructor;
import org.gestionbibliotheque.service.BookService;
import org.gestionbibliotheque.service.BorrowService;
import org.gestionbibliotheque.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;
    private final BookService bookService;
    private final StudentService studentService;

    @GetMapping
    public String listBorrows(Model model) {
        model.addAttribute("borrows", borrowService.getAllBorrows());
        return "borrows/list";
    }

    @GetMapping("/active")
    public String listActiveBorrows(Model model) {
        model.addAttribute("borrows", borrowService.getActiveBorrows());
        return "borrows/active";
    }

    @GetMapping("/borrow")
    public String showBorrowForm(Model model) {
        model.addAttribute("books", bookService.getAvailableBooks());
        model.addAttribute("students", studentService.displayStudents());
        return "borrows/borrow";
    }

    @PostMapping("/borrow")
    public String borrowBook(@RequestParam("studentId") Long studentId,
                             @RequestParam("bookId") Long bookId,
                             RedirectAttributes redirectAttributes) {
        try {
            borrowService.borrowBook(studentId, bookId);
            redirectAttributes.addFlashAttribute("successMessage", "Livre emprunté avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/borrows";
    }

    @GetMapping("/return")
    public String showReturnForm(Model model) {
        model.addAttribute("activeBorrows", borrowService.getActiveBorrows());
        return "borrows/return";
    }

    @PostMapping("/return")
    public String returnBook(@RequestParam("studentId") Long studentId,
                             @RequestParam("bookId") Long bookId,
                             RedirectAttributes redirectAttributes) {
        try {
            borrowService.returnBook(studentId, bookId);
            redirectAttributes.addFlashAttribute("successMessage", "Livre retourné avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/borrows";
    }
}