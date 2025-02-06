import java.util.HashMap;

public class Library {
    private HashMap<String, Member> members; // Where key is the member's email (unique identifier)
    private HashMap<String, Book> books;       // Where key is the ISBN of the book
    
    public Library() {
        members = new HashMap<>();
        books = new HashMap<>();
    }
    // Book Management Methods
    // Add Book
    public void addBook(Book book) {
        if (books.containsKey(book.getISBN())) { // Check if the book already exists
            System.out.println("The book already exists in library!");
        } else {
            books.put(book.getISBN(), book); // Add book to the library
            System.out.println("The book " + book.getTitle() + " was successfully added to the library!");
        }
    }
    // Remove Book
    public void removeBook(Book book) {
        if (books.containsKey(book.getISBN())) { // Check if the book actually exists
            books.remove(book.getISBN()); // Remove book from the library
            System.out.println("The book " + book.getTitle() + " was successfully removed from the library!");
        } else {
            System.out.println("The book doesn't exist in the library!");
        }
    }
    // Display All Available Books
    public void displayAllAvailableBooks() {
        if (books.isEmpty()) { // Checks if there are any books in the library
            System.out.println("No books in the library.");
        } else {
            for (Book book : books.values()) { // Iterates over the Book objects
                if (book.getIsAvailable()) { // Only display available books
                    System.out.println(book); // Automatically calls toString()
                }
            }
        }
    }
    
    // Get Book by ISBN
    public Book getBookByISBN(String ISBN) {
        return books.get(ISBN);
    }
    
    // Get Member by Email
    public Member getMemberbyEmail(String email) {
        return members.get(email);
    }
    
    // Membership Management Methods
    // Create Membership
    public void createMembership(String email, String name, String password) {
        if (members.containsKey(email)) { // Verifies if membership already exists
            System.out.println("This email is already been used.");
        } else {
            Member newMember = new Member(email, name, password); // Create new Member
            members.put(email, newMember); // Add new member to the dictionary
            System.out.println("Your membership has successfully been created.");
        }
    }
    // Login
    public Member getMembership(String email, String password) {
        if (members.containsKey(email)) {
            Member member = members.get(email); // Get the Member object 
            String StoredPassword = member.getPassword(); // Get the password of that member
            if (password.equals(StoredPassword)) { // Compare the stored password with the input
                System.out.println("You have successfully logged in");
                return member;
            } else {
                System.out.println("Invalid Credentials. Please try again.");
                return null;
            }
        } else {
            System.out.println("Membership not found. Please try to create an account!");
            return null;
        }
    }
    // Remove Membership
    public void removeMembership(String email, String password) {
        if (members.containsKey(email)) { // Check if the membership exists
            Member member = members.get(email); 
            String StoredPassword = member.getPassword();
            if (password.equals(StoredPassword)) { // Check if the provided password is correct
                members.remove(email, member); // Remove membership
                System.out.println("The membership was successfully removed!");
            } else {
                System.out.println("Invalid password! Please try again.");
            }
        } else {
            System.out.println("The membership does not exist!");
        }
    }
    // Display All Current Memberships
    public void displayAllMemberships() {
        if (members.isEmpty()) { // Checks if there are any active memberships in the library
            System.out.println("There is no active memberships in the library");
        } else {
            for (Member member : members.values()) { // Iterates over Member objects
                System.out.println(member); // Calls toString()
            }
        }
    }
    // Connection \"bridge\" between Class Book and Class Member
    // Sets book as borrowed and gives it to the member
    public void borrowBook(Member member, String bookISBN) {
        if (!books.containsKey(bookISBN)) { // Checks if the book exists in the library
            System.out.println("The book doesn't exist in the library.");
        } else {
            Book book = books.get(bookISBN);
            if (book.getIsAvailable()) { // Checks if book is available
                book.markAsBorrowed(); // Mark book as borrowed
                member.borrowBook(book); // Add book to member's list
                System.out.println("The book was successfully borrowed!");
            } else {
                System.out.println("The book is not available");
            }
        }
    }
    // Sets book as available and returns it to the library
    public void returnBook(Member member, String bookISBN) {
        if (!books.containsKey(bookISBN)) { // Check if the book exists
            System.out.println("That book does not exist. Please check the ISBN!");
        } else {
            Book book = books.get(bookISBN);
            if (book.getIsAvailable()) { // Checks if the book is already available                
                System.out.println("The book is available! Please check the ISBN!");          
            } else {                
                book.markAsAvailable(); // Mark book as available                
                member.returnBook(book); // Remove book from member's list               
                System.out.println("The book was successfully returned.");            
            }       
        }   
    }
}

