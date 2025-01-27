package com.lib.library_system.repository;


import com.lib.library_system.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn AND " +
            "(b.title != :title OR b.author != :author)")
    List<Book> findConflictingBooks(@Param("isbn") String isbn,
                                    @Param("title") String title,
                                    @Param("author") String author);
}

