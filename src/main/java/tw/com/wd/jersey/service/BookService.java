package tw.com.wd.jersey.service;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class BookService {
    private static final ConcurrentMap<String, Book> BOOK_MAP               = new ConcurrentHashMap<>();
    private static final ConcurrentMap<BookType, Set<Book>> BOOK_TYPE_MAP   = new ConcurrentHashMap<>();


    Collection<Book> getBooks(BookType bookType) {
        if (BookType.Undefine != bookType) {
            return BOOK_TYPE_MAP.get(bookType);
        }
        return BOOK_MAP.values();
    }

    Book getBook(String bookID) {
        return BOOK_MAP.get(bookID);
    }

    boolean createBook(String body) {
        return null;
    }

    boolean updateBook(String bookId, String body) {
        return null;
    }

    boolean deleteBook(String bookID) {
        return null;
    }
}
