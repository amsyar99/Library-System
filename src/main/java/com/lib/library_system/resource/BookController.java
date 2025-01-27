package com.lib.library_system.resource;

import com.lib.library_system.entity.Book;
import com.lib.library_system.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("")
    public ResponseEntity<Book> registerBook(@RequestBody Book book) {
        log.info("Received request to register a new book: {}", book);
        bookService.validateBookForRegistration(book);
        Book savedBook = bookService.register(book);
        log.info("Book successfully registered with ID: {}", savedBook.getId());
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Received request to get list of book");
        List<Book> books = bookService.getBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PutMapping("/{bookId}/borrow")
    public ResponseEntity<Book> borrowBook(@PathVariable Long bookId, @RequestParam Long borrowerId) {
        log.info("Received request to borrow book with ID: {} by borrower ID: {}", bookId, borrowerId);
        Book book = bookService.validateBorrowBook(bookId);
        Book result = bookService.borrowBook(book, borrowerId);
        log.info("Book with ID: {} has been borrowed by borrower ID: {}", bookId, borrowerId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{bookId}/return")
    public ResponseEntity<Book> returnBook(@PathVariable Long bookId) {
        log.info("Received request to return book with ID: {}", bookId);
        Book book = bookService.validateReturnBook(bookId);
        Book result = bookService.returnBook(book);
        log.info("Book with ID: {} has been successfully returned", bookId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

