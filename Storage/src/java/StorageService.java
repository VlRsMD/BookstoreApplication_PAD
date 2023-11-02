package com.example.Storage.storage;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StorageService {
    private final StorageRepository storageRepository;

    public StorageService(StorageRepository storageRepository) {
        this.storageRepository = storageRepository;
    }

    public List<Book> getStorage() {
        return storageRepository.findAll();
    }

    public void addBook(Book book) {
        storageRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        boolean exists = storageRepository.existsById(bookId);
        if (!exists) {
            throw new IllegalStateException("Book with ID " + bookId + " does not exist. Unable to delete a nonexistent book.");
        }
        storageRepository.deleteById(bookId);
    }

    @Transactional
    @Cacheable(value = "book", key = "#id")
    public void changePrice(Long bookId, int price) {
        Book book = storageRepository.findById(bookId)
                .orElseThrow(() -> {
                    throw new IllegalStateException("Book with ID " + bookId + " does not exist. Unable to change the price of a nonexistent book.");
                });
        if(book.getPrice() != price) {
            book.setPrice(price);
        }
    }
}
