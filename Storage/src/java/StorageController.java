package com.example.Storage.storage;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("storage")
public class StorageController implements StorageControllerInterface {
    private static final Logger LOG = LogManager.getLogger(StorageController.class);
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @Override
    @GetMapping
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker1", fallbackMethod = "fallback1")
    public List<Book> getStorage() {
        LOG.log(Level.INFO, "Request to fetch books in the system.");
        return storageService.getStorage();
    }

    @Override
    @PostMapping()
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker2", fallbackMethod = "fallback2")
    public void addBook(@RequestBody Book book) {
        LOG.log(Level.INFO, "Request to add a book to the system.");
        storageService.addBook(book);
    }

    @Override
    @DeleteMapping("{bookId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker3", fallbackMethod = "fallback3")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        LOG.log(Level.INFO, "Request to delete a particular book from the system.");
        storageService.deleteBook(bookId);
    }

    @Override
    @PatchMapping("{bookId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker4", fallbackMethod = "fallback4")
    public void changePrice(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) int price) {
        LOG.log(Level.INFO, "Request to change the price of a particular book.");
        storageService.changePrice(bookId, price);
    }

    private String fallback1(Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback2(@RequestBody Book book, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback3(@PathVariable("bookId") Long bookId, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }

    private String fallback4(@PathVariable("bookId") Long bookId,
                             @RequestParam(required = false) int price, Throwable e) {
        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method.";
    }
}
