public class Book {

    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) throws InvalidBookDataException {
        validateTitle(title);
        validateAuthor(author);
        validateIsbn(isbn);
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    private void validateTitle(String title) throws InvalidBookDataException {
        if (title == null || title.isEmpty()) {
            throw new InvalidBookDataException("Title cannot be empty");
        }
    }

    private void validateAuthor(String author) throws InvalidBookDataException {
        if (author == null || author.isEmpty()) {
            throw new InvalidBookDataException("Author cannot be empty");
        }
    }

    private void validateIsbn(String isbn) throws InvalidBookDataException {
        // Implement ISBN validation logic here (e.g., length, format)
        if (isbn == null || isbn.isEmpty()) {
            throw new InvalidBookDataException("ISBN cannot be empty");
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void markBorrowed() {
        available = false;
    }

    public void markReturned() {
        available = true;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }

    public static class InvalidBookDataException extends Exception {
        public InvalidBookDataException(String message) {
            super(message);
        }
    }
}
