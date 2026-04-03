class Almanac implements LibraryItem {

    private String title;
    private Book[] books;

    public Almanac(String title, Book[] books) {
        this.title = title;
        this.books = books;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void print() {
        System.out.println("Альманах: " + title);
        System.out.println("Твори:");
        for (Book b : books) {
            b.print();
        }
    }

    public Book[] getBooks() {
        return books;
    }
}