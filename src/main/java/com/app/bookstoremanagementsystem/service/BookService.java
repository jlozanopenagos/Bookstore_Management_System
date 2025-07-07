package com.app.bookstoremanagementsystem.service;

import com.app.bookstoremanagementsystem.dto.BookRequest;
import com.app.bookstoremanagementsystem.dto.BookResponse;
import com.app.bookstoremanagementsystem.model.Book;
import com.app.bookstoremanagementsystem.repositories.BookRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return  bookRepository.findByActiveTrue().stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public List<BookResponse> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword).stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse createBook(BookRequest bookRequest) {
        Book book = new Book();
        updateBookFromRequest(book, bookRequest);
        Book savedBook = bookRepository.save(book);
        return mapToBookResponse(savedBook);
    }

    public void updateBookFromRequest(Book book, BookRequest bookRequest) {
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setPublisher(bookRequest.getPublisher());
        book.setDescription(bookRequest.getDescription());
        book.setCategory(bookRequest.getCategory());
        book.setIsbn(bookRequest.getIsbn());
        book.setImageUrl(bookRequest.getImageUrl());
        book.setPrice(bookRequest.getPrice());
        book.setStockQuantity(bookRequest.getStockQuantity());
    }

    public BookResponse mapToBookResponse(Book savedBook) {
        BookResponse bookResponse = new BookResponse();

        bookResponse.setId(savedBook.getId());
        bookResponse.setTitle(savedBook.getTitle());
        bookResponse.setAuthor(savedBook.getAuthor());
        bookResponse.setPublisher(savedBook.getPublisher());
        bookResponse.setDescription(savedBook.getDescription());
        bookResponse.setCategory(savedBook.getCategory());
        bookResponse.setIsbn(savedBook.getIsbn());
        bookResponse.setImageUrl(savedBook.getImageUrl());
        bookResponse.setPrice(savedBook.getPrice());
        bookResponse.setStockQuantity(savedBook.getStockQuantity());

        return bookResponse;
    }

    public Optional<BookResponse> updateBook(Long id, BookRequest bookRequest) {
        return bookRepository.findById(id)
                .map(existingBook -> {
                    updateBookFromRequest(existingBook, bookRequest);
                    Book savedBook = bookRepository.save(existingBook);
                    return mapToBookResponse(savedBook);
                });
    }

    public boolean deleteBook(Long id) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setActive(false);
                    bookRepository.save(book);
                    return true;
                }).orElse(false);
    }
}
