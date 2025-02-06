import java.util.ArrayList;

public class Member {
    private String email;
    private String name;
    private String password;
    private ArrayList<Book> borrowedbooks;

    // Constructor
    public Member(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.borrowedbooks = new ArrayList<Book>();
    }
    // Getters
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    // Methods
    // Borrow Book
    public void borrowBook(Book book) {
        if (!book.getIsAvailable()) { // Checks if book was already borrowed
            System.out.println("This book is already borrowed!");
        } else {
            book.markAsBorrowed(); // Marks book as Borrowed
            borrowedbooks.add(book); // Add book to the borrowed book list
            System.out.println("Book borrowed successfully.");
        }
    }
    // Return a book
    public void returnBook(Book book) {
        if (book.getIsAvailable()) { // Checks if book was already returned
            System.out.println("This book is available! You might want to check the ISBN again");
        } else {
            book.markAsAvailable(); // Mark book as Available
            borrowedbooks.remove(book); // Remove book from list of borrowed books
            System.out.println("Book returned successfully.");
        }
    }
    // Display borrowed books
    public void displayBorrowedBooks() {
        for (Book book : borrowedbooks) { // For each Book in the borrowedBooks
            System.out.println(book);
        }
    }
    @Override
    public String toString() { //Printing Member
        return "Email: " + email + ", Name: " + name; 
    }
}
