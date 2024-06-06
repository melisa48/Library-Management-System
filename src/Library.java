import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) throws BookAlreadyExistsException {
        if (isIsbnExist(book.getIsbn())) {
            throw new BookAlreadyExistsException("Book with ISBN " + book.getIsbn() + " already exists.");
        }
        books.add(book);
    }

    public List<Book> listBooks() {
        return books;
    }

    public List<Book> searchByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Book> searchByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());
    }

    public boolean deleteBook(String isbn) throws BookNotFoundException {
        int index = findBookIndexByIsbn(isbn);
        if (index == -1) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        books.remove(index);
        return true;
    }

    public boolean updateBook(String isbn, String newTitle, String newAuthor) throws BookNotFoundException {
        int index = findBookIndexByIsbn(isbn);
        if (index == -1) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        Book book = books.get(index);
        book.setTitle(newTitle);
        book.setAuthor(newAuthor);
        return true;
    }

    public boolean borrowBook(String isbn) throws BookNotFoundException, BookNotAvailableException {
        int index = findBookIndexByIsbn(isbn);
        if (index == -1) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        Book book = books.get(index);
        if (!book.isAvailable()) {
            throw new BookNotAvailableException("Book with ISBN " + isbn + " is not available.");
        }
        book.markBorrowed();
        return true;
    }

    public boolean returnBook(String isbn) throws BookNotFoundException {
        int index = findBookIndexByIsbn(isbn);
        if (index == -1) {
            throw new BookNotFoundException("Book with ISBN " + isbn + " not found.");
        }
        Book book = books.get(index);
        book.markReturned();
        return true;
    }

    private boolean isIsbnExist(String isbn) {
        return books.stream().anyMatch(book -> book.getIsbn().equals(isbn));
    }

    private int findBookIndexByIsbn(String isbn) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                return i;
            }
        }
        return -1;
    }

    public static class BookAlreadyExistsException extends Exception {
        public BookAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class BookNotFoundException extends Exception {
        public BookNotFoundException(String message) {
            super(message);
        }
    }

    public static class BookNotAvailableException extends Exception {
        public BookNotAvailableException(String message) {
            super(message);
        }
    }
}
