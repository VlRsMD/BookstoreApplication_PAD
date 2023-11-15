package com.example.Storage.storage;

import com.example.Storage.LoadBalancerConfiguration;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "storage")
@LoadBalancerClient(name = "storage",
        configuration= LoadBalancerConfiguration.class)
@RestController
@RequestMapping("storage")
public class StorageController {
    private static final Logger LOG = LogManager.getLogger(StorageController.class);
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker1", fallbackMethod = "fallback")
    public List<Book> getStorage() {
        LOG.log(Level.INFO, "Request to fetch books in the system.");
        return storageService.getStorage();
    }

    @PostMapping()
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker2", fallbackMethod = "fallback")
    public void addBook(@RequestBody Book book) {
        LOG.log(Level.INFO, "Request to add a book to the system.");
        storageService.addBook(book);
    }

    @DeleteMapping("{bookId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker3", fallbackMethod = "fallback")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        LOG.log(Level.INFO, "Request to delete a particular book from the system.");
        storageService.deleteBook(bookId);
    }

    @PatchMapping("{bookId}")
    @Transactional(timeout = 500)
    @CircuitBreaker(name = "circuitBreaker4", fallbackMethod = "fallback")
    public void changePrice(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) int price) {
        LOG.log(Level.INFO, "Request to change the price of a particular book.");
        storageService.changePrice(bookId, price);
    }

    private String fallback(Throwable e) {

        System.out.println("Exception happened : " + e.getMessage());
        return "Handled the exception through fallback method";
    }
}
