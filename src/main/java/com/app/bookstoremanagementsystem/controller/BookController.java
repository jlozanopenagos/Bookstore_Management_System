package com.app.bookstoremanagementsystem.controller;

import com.app.bookstoremanagementsystem.dto.BookRequest;
import com.app.bookstoremanagementsystem.dto.BookResponse;
import com.app.bookstoremanagementsystem.service.BookService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookstore")
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponse>> getBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(@RequestBody BookRequest bookRequest) {
        return new ResponseEntity<BookResponse>(bookService.createBook(bookRequest),
                HttpStatus.CREATED);
    }
}
