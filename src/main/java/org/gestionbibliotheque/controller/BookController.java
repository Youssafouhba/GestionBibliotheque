package org.gestionbibliotheque.controller;


import lombok.RequiredArgsConstructor;
import org.gestionbibliotheque.dto.BookDTO;
import org.gestionbibliotheque.model.Book;
import org.gestionbibliotheque.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public String listBooks(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "books/list";
    }

    @GetMapping("/available")
    public String listAvailableBooks(Model model) {
        model.addAttribute("books", bookService.getAvailableBooks());
        return "books/available";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("bookDTO") BookDTO bookDTO,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "books/add";
        }

        Book book = Book.builder()
                .title(bookDTO.getTitle())
                .author(bookDTO.getAuthor())
                .isbn(bookDTO.getIsbn())
                .year(bookDTO.getYear())
                .publisher(bookDTO.getPublisher())
                .build();

        bookService.addBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Livre ajouté avec succès");
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setYear(book.getYear());
        bookDTO.setPublisher(book.getPublisher());

        model.addAttribute("bookDTO", bookDTO);
        model.addAttribute("bookId", id);
        return "books/edit";
    }

    @PostMapping("/edit/{id}")
    public String editBook(@PathVariable Long id,
                           @ModelAttribute("bookDTO") BookDTO bookDTO,
                           BindingResult result,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "books/edit";
        }

        bookService.updateBook(bookDTO, id);
        redirectAttributes.addFlashAttribute("successMessage", "Livre modifié avec succès");
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id);
        bookService.deleteBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Livre supprimé avec succès");
        return "redirect:/books";
    }
}