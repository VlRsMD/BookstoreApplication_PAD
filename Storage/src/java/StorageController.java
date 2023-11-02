package com.example.Storage.storage;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("storage")
public class StorageController {
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping
    @Transactional(timeout = 500)
    public List<Book> getStorage() {
        return storageService.getStorage();
    }

    @PostMapping()
    @Transactional(timeout = 500)
    public void addBook(@RequestBody Book book) {
        storageService.addBook(book);
    }

    @DeleteMapping("{bookId}")
    @Transactional(timeout = 500)
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        storageService.deleteBook(bookId);
    }

    @PatchMapping("{bookId}")
    @Transactional(timeout = 500)
    public void changePrice(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) int price) {
        storageService.changePrice(bookId, price);
    }
}