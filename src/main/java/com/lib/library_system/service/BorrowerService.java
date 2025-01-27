package com.lib.library_system.service;

import com.lib.library_system.entity.Borrower;
import com.lib.library_system.repository.BorrowerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BorrowerService {

    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public Borrower save(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }

    public void validate (Borrower borrower) {
        if (borrowerRepository.existsByEmail(borrower.getEmail())) {
            throw new IllegalArgumentException("Borrower with this email already exists.");
        }

    }
}

