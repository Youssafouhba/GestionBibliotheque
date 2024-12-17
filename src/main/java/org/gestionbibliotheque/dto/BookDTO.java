package org.gestionbibliotheque.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String isbn;
}
