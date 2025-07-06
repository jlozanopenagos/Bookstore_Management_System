package com.app.bookstoremanagementsystem.repositories;

import com.app.bookstoremanagementsystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByActiveTrue();

    @Query("SELECT b FROM books_table b WHERE b.active = true AND b.stockQuantity > 0 AND LOWER(b.title) LIKE LOWER(CONCAT('%', :keyword, '%') )")
    List<Book> searchBooks(@Param("keyword") String keyword);
}
