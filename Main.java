import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();
        
        while (true) { // Deciding if you want to enter the Library or just leave 
            System.out.println("\nChoose an option.");
            System.out.println("1 - Enter Library.");
            System.out.println("2 - Exit.");
            
            try { // Outter try-catch starts here
                int option = scanner.nextInt();
                scanner.nextLine(); //Clears the buffer
                
                switch (option) { //Initializes the cases for the first set of choices
                    case 1:
                        System.out.println("You enter the library.");
                        boolean SessionActive = true; // Initialize a boolean variable for eventually leave the loop
                        
                        while (SessionActive) { //Innitializes the library menu
                            System.out.println("\nChoose what would you like to do.");
                            System.out.println("1 - Add book to the library.");
                            System.out.println("2 - Remove book from library.");
                            System.out.println("3 - Display all available books in the library.");
                            System.out.println("4 - Create new membership.");
                            System.out.println("5 - Access Library.");
                            System.out.println("6 - Remove Membership.");
                            System.out.println("7 - Display all current memberships.");
                            System.out.println("8 - Exit library management.");
                            
                            try { // Initializes 2nd try-catch here
                                int choice = scanner.nextInt();
                                scanner.nextLine();//Clears the buffer
                                
                                switch (choice) { //Initilizes the cases for each choice of library menu 
                                    case 1:
                                        System.out.println("Enter Book Title, Author, Publisher, ISBN");
                                        String title = scanner.nextLine();
                                        String author = scanner.nextLine();
                                        String publisher = scanner.nextLine();
                                        String ISBN = scanner.nextLine();
                                        Book book = new Book(title, author, publisher, ISBN);// Creates a new Book object using the provided title, author, publisher, and ISBN.
                                        library.addBook(book);//Call the method for adding the book to the library
                                        break;
                                    case 2:
                                        System.out.println("Enter Book ISBN");
                                        ISBN = scanner.nextLine();
                                        Book bookToRemove = library.getBookByISBN(ISBN); // Retrieves the Book object corresponding to the provided ISBN from the library.
                                        if (bookToRemove != null) {
                                            library.removeBook(bookToRemove); //Calls the method removeBook() to remove a book from the library
                                        } else {
                                            System.out.println("Book not found!");
                                        }
                                        break;
                                    case 3:
                                        library.displayAllAvailableBooks();//Calls the method displayAllAvailableBook() to display all available books in the library
                                        break;
                                    case 4:
                                        System.out.println("Enter Member Email, Name and Password");
                                        String email = scanner.nextLine();
                                        String name = scanner.nextLine();
                                        String password = scanner.nextLine();
                                        Member member = new Member(email, name, password); // Creates a new Member object using the provided email, name and password
                                        library.createMembership(email, name, password); // calls for the method createMembership() to create a new membership
                                        break;
                                    case 5:
                                        System.out.println("Enter Member Email and password");
                                        email = scanner.nextLine();
                                        password = scanner.nextLine();
                                        Member loggedInMember = library.getMembership(email, password); //Retrieves the Member object corresponding to the provided email and password
                                        if (loggedInMember == null) { //Checkes if the provided email and/or password are valid
                                            System.out.println("Invalid email or password. Please try again.");
                                        } else { // Only proceed if login is successful
                                            boolean LoginActive = true; // Creates a boolean variable to eventually logout
                                            while (LoginActive) { //Initializes the login menu
                                                System.out.println("Hello " + loggedInMember.getName() + "!");
                                                System.out.println("\n1-Borrow Book\n2-Return Book\n3-Display All books\n4-Logout");
                                                
                                                try { //Inner try-catch starts here
                                                    int opt = scanner.nextInt();
                                                    scanner.nextLine();//Clears the buffer
                                                    switch (opt) { //Initializes the cases for each choice of the login menu
                                                        case 1:
                                                            System.out.println("Enter Member email and Book ISBN to borrow: ");
                                                            email = scanner.nextLine();
                                                            ISBN = scanner.nextLine();
                                                            Member bmember = library.getMemberbyEmail(email); //Retrives the Member object corresponding to the provided email
                                                            Book bookToBorrow = library.getBookByISBN(ISBN); //Retrieves the Book object correspnding to the provided ISBN
                                                            if (bookToBorrow != null) { //Checks if book exists
                                                                if (bmember != null) { //Checks if member exists
                                                                    library.borrowBook(bmember, ISBN); //Calls for the method borrowBook() to borrow the book to the member
                                                                } else {
                                                                    System.out.println("Member not found!");
                                                                }
                                                            } else {
                                                                System.out.println("Book not found!");
                                                            }
                                                            break;
                                                        case 2:
                                                            System.out.println("Enter Member Email and Book ISBN to Return:");
                                                            email = scanner.nextLine();
                                                            ISBN = scanner.nextLine();
                                                            Member rmember = library.getMemberbyEmail(email);
                                                            Book bookToReturn = library.getBookByISBN(ISBN);
                                                            if (bookToReturn != null) {
                                                                if (rmember != null) {
                                                                    library.returnBook(rmember, ISBN); // Calls for the method returnBook(), so the memeber can return the book
                                                                } else {
                                                                    System.out.println("Member not found!");
                                                                }
                                                            } else {
                                                                System.out.println("Book not found!");
                                                            }
                                                            break;
                                                        case 3:
                                                            library.displayAllAvailableBooks();
                                                            break;
                                                        case 4:
                                                            System.out.println("Leaving for the main menu...");
                                                            LoginActive = false;//Breaks from Login menu and goes back to library menu
                                                            break;
                                                        default:
                                                            System.out.println("Invalid option. Please try again.");
                                                            break;
                                                    }
                                                } catch (Exception e) { //inner try-catch ends here. Makes sure that only Integers are valid inputs
                                                    System.out.println("Invalid input. Please enter a valid number");
                                                    scanner.nextLine();
                                                }
                                            }
                                        }
                                        break;
                                    case 6:
                                        System.out.println("Enter Member email and password.");
                                        String emailRemove = scanner.nextLine();
                                        String passRemove = scanner.nextLine();
                                        library.removeMembership(emailRemove, passRemove); // Calls for the method removeMembership() to delete a membership
                                        break;
                                    case 7:
                                        library.displayAllMemberships(); //Calls for the method displayAllMemberships() to display all the current memberships
                                        break;
                                    case 8:
                                        System.out.println("Leaving the library...");
                                        SessionActive = false; //Breaks from the library
                                        break;
                                    default:
                                        System.out.println("Invalid choice. Please try again!");
                                        break;
                                }
                            } catch (Exception e) { //Mid try catch ends here. Makes sure that only Integers are valid inputs
                                System.out.println("Invalid input. Please enter a valid number");
                                scanner.nextLine();
                            }
                        }
                        break; // End of case 1 in outer switch
                    case 2:
                        System.out.println("Exiting Program....");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again");
                        break;
                }
            } catch(Exception e) { // Outer try-catch ends here. Makes sure that only Integers are valid inputs              
                System.out.println("Invalid input. Please enter a valid number.");                
                scanner.nextLine();            
            }        
        }    
    }
}

