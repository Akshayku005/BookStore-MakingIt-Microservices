package com.bridgelabz.book.service;

import com.bridgelabz.book.dto.BookDTO;
import com.bridgelabz.book.exception.BookException;
import com.bridgelabz.book.model.Book;
import com.bridgelabz.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository repo;

    @Override
    public Book insertBook(BookDTO dto) {
        Book book = new Book(dto);
        repo.save(book);
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> list = repo.findAll();
        if (list.isEmpty()) {
            throw new NullPointerException("there are no books inserted yet");
        }
        return list;
    }


    @Override
    public Book getbookByID(Integer bookId) {
        Optional<Book> book = repo.findByBookId(bookId);
        if (book.isEmpty()) {
            throw new BookException("There are no Books with given id");
        }
        return book.get();
    }

    @Override
    public Book getbookByBookName(String bookName) {
        Optional<Book> book = repo.findByBookName(bookName);
        if (book.isEmpty()) {
            throw new BookException("There are no Books with given name");
        }
        return book.get();
    }


    @Override
    public List<Book> retrieveBooks() {
        List<Book> list = repo.findAll();
        if (list.isEmpty()) {
            throw new BookException("There are no Books added");
        } else {
            return list;
        }
    }


    @Override
    public Book updateById(Integer bookId, BookDTO dto) {
        Optional<Book> book = repo.findById(bookId);
        if (book.isPresent()) {
            Book newBook = new Book(bookId, dto);
            repo.save(newBook);
            return newBook;
        } else {
            throw new BookException("Book not found");
        }
    }


    @Override
    public Book deleteById(Integer bookId) {
        Optional<Book> book = repo.findById(bookId);
        if (book.isEmpty()) {
            throw new BookException("Invalid BookId..please input valid Id");
        }
        repo.deleteById(bookId);
        return book.get();
    }

    @Override
    public List<Book> sortBooksAsc() {
        return repo.sortBooksAsc();
    }


    @Override
    public List<Book> sortBooksDesc() {
        return repo.sortBooksDesc();
    }

    @Override
    public Book updateBookQuantity(int bookId, int bookQuantity) {
        Book book = this.getbookByID(bookId);
        book.setQuantity(bookQuantity);
        return repo.save(book);
    }

    @Override
    public Book getbookByIdAPI(Integer bookId) {
        Optional<Book> book = repo.findById(bookId);
        if (book.isEmpty()) {
            throw new BookException("There are no Books with given id");
        }
        return book.get();
    }
}
