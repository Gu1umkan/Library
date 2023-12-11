package service.impl;

import models.Book;
import models.Library;
import models.db.Database;
import service.BookService;

import java.util.ArrayList;

public class BookServiceImpl implements BookService {
    @Override
    public String saveBook(Long libraryId, Book book) {
        Database.books.add(book);
        for (Library library : Database.libraries) {
            if (library.getId() == libraryId) {
                library.getBooks().add(book);
                return "Succesful";
            }
        }
        return "Not found  Library id";
    }

    @Override
    public ArrayList<Book> getAllBooks(Long libraryId) {

        return Database.books;
    }

    @Override
    public Book getBookById(Long libraryId, Long bookId) {
        for (Library library : Database.libraries) {
            if (library.getId() == libraryId) {
                for (Book book : library.getBooks()) {
                    if (book.getId() == bookId) {
                        return book;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public String deleteBook(Long libraryId, Long bookId) {
        for (Library library : Database.libraries) {
            if (library.getId() == libraryId) {
                for (Book book : library.getBooks()) {
                    if (book.getId() == bookId) {
                        library.getBooks().remove(book);
                        return "Succesfull";
                    }
                }
            }
        }
        return "Noot found id";
    }

    @Override
    public void clearBooksByLibraryId(Long libraryId) {
        for (Library library : Database.libraries) {
            if (library.getId() == libraryId) {
                library.getBooks().clear();
                System.out.println("Succesfull");
            }
        }
    }
}
