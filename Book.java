public class Book {
    private String title;
    private String author;
    private String publisher;
    private boolean isAvailable;
    private String ISBN; // Unique Identifier like an ID

    public Book(String title, String author, String publisher, String ISBN) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isAvailable = true;
        this.ISBN = ISBN;
    }
    // Getters
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public String getPublisher() {
        return publisher;
    }
    public boolean getIsAvailable() {
        return isAvailable;
    }
    public String getISBN() {
        return ISBN;
    }
    public void markAsAvailable() {
        isAvailable = true;
    }
    public void markAsBorrowed() {
        isAvailable = false;
    }
    @Override
    public String toString() { //Printing Book 
        return "The book title is " + title + ", the author who wrote it is " + author +
               ". Additional Information: Status - " + (isAvailable ? "Available" : "Borrowed") +
               " ISBN - " + ISBN;
    }
}
