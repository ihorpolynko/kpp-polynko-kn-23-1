class Catalog {

    private LibraryItem[] items = new LibraryItem[20];
    private int size = 0;

    // додати об'єкт
    public void add(LibraryItem item) {
        items[size++] = item;
    }

    public void addRandom() {

        int type = (int) (Math.random() * 3);

        switch (type) {
            case 0:
                add(Utils.randomBook());
                System.out.println("Додано випадкову книгу");
                break;

            case 1:
                add(Utils.randomNewspaper());
                System.out.println("Додано випадкову газету");
                break;

            case 2:
                add(new Almanac(
                        Utils.randomTitle(),
                        new Book[] {
                                Utils.randomBook(),
                                Utils.randomBook()
                        }));
                System.out.println("Додано випадковий альманах");
                break;
        }
    }

    // видалення за назвою
    public void remove(String title) {
        for (int i = 0; i < size; i++) {

            if (items[i].getTitle().equalsIgnoreCase(title)) {

                // зсув масиву
                for (int j = i; j < size - 1; j++) {
                    items[j] = items[j + 1];
                }

                size--;
                System.out.println("\nВидалено: " + title);
                return;
            }
        }
    }

    // вивід всього
    public void printAll() {
        for (int i = 0; i < size; i++) {
            items[i].print();
            System.out.println("\n");
        }
    }

    // пошук за назвою
    public void searchByTitle(String title) {

        for (int i = 0; i < size; i++) {

            // перевіряємо сам об'єкт
            if (items[i].getTitle().equalsIgnoreCase(title)) {
                items[i].print();
            }

            // якщо це альманах — шукаємо всередині
            if (items[i] instanceof Almanac) {

                Almanac a = (Almanac) items[i];

                for (Book b : a.getBooks()) {

                    if (b.getTitle().equalsIgnoreCase(title)) {
                        b.print();
                    }
                }
            }
        }
    }

    // пошук за автором (книги + альманахи)
    public void searchByAuthor(String author) {

        for (int i = 0; i < size; i++) {

            // 1. Якщо це звичайна книга
            if (items[i] instanceof Book) {

                Book b = (Book) items[i];

                if (b.getAuthor().equalsIgnoreCase(author)) {
                    b.print();
                }
            }

            // 2. Якщо це альманах
            if (items[i] instanceof Almanac) {

                Almanac a = (Almanac) items[i];

                for (Book b : a.getBooks()) {

                    if (b.getAuthor().equalsIgnoreCase(author)) {
                        b.print();
                    }
                }
            }
        }
    }
}