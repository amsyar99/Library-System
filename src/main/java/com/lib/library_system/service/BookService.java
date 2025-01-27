package com.lib.library_system.service;

import com.lib.library_system.entity.Book;
import com.lib.library_system.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book register(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Transactional
    public Book borrowBook(Book book, Long borrowerId) {
        book.setBorrowed(true);
        book.setBorrowedBy(borrowerId);
        return bookRepository.save(book);
    }

    @Transactional
    public Book returnBook(Book book) {
        book.setBorrowed(false);
        book.setBorrowedBy(null);
        return bookRepository.save(book);
    }

    public void validateBookForRegistration(Book book) {
        List<Book> conflictingBooks = bookRepository.findConflictingBooks(
                book.getIsbn(), book.getTitle(), book.getAuthor()
        );

        if (!conflictingBooks.isEmpty()) {
            throw new IllegalArgumentException("ISBN conflict: Title/Author mismatch.");
        }
    }

    public Book validateBorrowBook(Long bookId) {
        return bookRepository.findById(bookId)
                .filter(book -> !book.isBorrowed())
                .orElseThrow(() -> new IllegalArgumentException("The book is either not found or already borrowed."));
    }

    public Book validateReturnBook(Long bookId) {
        return bookRepository.findById(bookId)
                .filter(Book::isBorrowed)
                .orElseThrow(() -> new IllegalArgumentException("The book is either not found or not currently borrowed."));
    }

}

