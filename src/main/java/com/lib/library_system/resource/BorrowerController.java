package com.lib.library_system.resource;

import com.lib.library_system.entity.Borrower;
import com.lib.library_system.service.BorrowerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {

    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService1) {
        this.borrowerService = borrowerService1;
    }

    @PostMapping("")
    public ResponseEntity<Borrower> registerBorrower(@RequestBody Borrower borrower) {
        log.info("Received request to register a new borrower: {}", borrower);
        borrowerService.validate(borrower);
        Borrower savedBorrower = borrowerService.save(borrower);
        log.info("Borrower successfully registered with ID: {}", savedBorrower.getId());
        return new ResponseEntity<>(savedBorrower, HttpStatus.CREATED);
    }
}
