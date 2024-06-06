import java.util.Scanner;
import java.util.List;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Library Management System:");
            System.out.println("1. Add Book");
            System.out.println("2. List Books");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Delete Book");
            System.out.println("6. Update Book");
            System.out.println("7. Borrow Book");
            System.out.println("8. Return Book");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            try {
                switch (choice) {
                    case 1:
                        addBook(scanner, library);
                        break;

                    case 2:
                        listBooks(library);
                        break;

                    case 3:
                        searchBookByTitle(scanner, library);
                        break;

                    case 4:
                        searchBookByAuthor(scanner, library);
                        break;

                    case 5:
                        deleteBook(scanner, library);
                        break;

                    case 6:
                        updateBook(scanner, library);
                        break;

                    case 7:
                        borrowBook(scanner, library);
                        break;

                    case 8:
                        returnBook(scanner, library);
                        break;

                    case 9:
                        System.out.println("Exiting the system. Goodbye!");
                        scanner.close();
                        System.exit(0);
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void addBook(Scanner scanner, Library library) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book ISBN: ");
        String isbn = scanner.nextLine();

        try {
            library.addBook(new Book(title, author, isbn));
            System.out.println("Book added successfully!");
        } catch (Book.InvalidBookDataException | Library.BookAlreadyExistsException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    private static void listBooks(Library library) {
        System.out.println("Listing all books:");
        for (Book book : library.listBooks()) {
            System.out.println(book);
        }
    }

    private static void searchBookByTitle(Scanner scanner, Library library) {
        System.out.print("Enter book title to search: ");
        String searchTitle = scanner.nextLine();
        List<Book> booksByTitle = library.searchByTitle(searchTitle);
        if (!booksByTitle.isEmpty()) {
            System.out.println("Books found:");
            for (Book book : booksByTitle) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books found with title: " + searchTitle);
        }
    }

    private static void searchBookByAuthor(Scanner scanner, Library library) {
        System.out.print("Enter book author to search: ");
        String searchAuthor = scanner.nextLine();
        List<Book> booksByAuthor = library.searchByAuthor(searchAuthor);
        if (!booksByAuthor.isEmpty()) {
            System.out.println("Books found:");
            for (Book book : booksByAuthor) {
                System.out.println(book);
            }
        } else {
            System.out.println("No books found with author: " + searchAuthor);
        }
    }

    private static void deleteBook(Scanner scanner, Library library) {
        System.out.print("Enter ISBN of the book to delete: ");
        String deleteIsbn = scanner.nextLine();
        try {
            library.deleteBook(deleteIsbn);
            System.out.println("Book deleted successfully!");
        } catch (Library.BookNotFoundException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }

    private static void updateBook(Scanner scanner, Library library) {
        System.out.print("Enter ISBN of the book to update: ");
        String updateIsbn = scanner.nextLine();
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new author: ");
        String newAuthor = scanner.nextLine();

        try {
            library.updateBook(updateIsbn, newTitle, newAuthor);
            System.out.println("Book updated successfully!");
        } catch (Library.BookNotFoundException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    private static void borrowBook(Scanner scanner, Library library) {
        System.out.print("Enter ISBN of the book to borrow: ");
        String borrowIsbn = scanner.nextLine();
        try {
            library.borrowBook(borrowIsbn);
            System.out.println("Book borrowed successfully!");
        } catch (Library.BookNotFoundException | Library.BookNotAvailableException e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    private static void returnBook(Scanner scanner, Library library) {
        System.out.print("Enter ISBN of the book to return: ");
        String returnIsbn = scanner.nextLine();
        try {
            library.returnBook(returnIsbn);
            System.out.println("Book returned successfully!");
        } catch (Library.BookNotFoundException e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }
}
