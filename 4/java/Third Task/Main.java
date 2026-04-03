public class Main {

    public static void main(String[] args) {

        Catalog catalog = new Catalog();

        // тестова ініціалізація
        catalog.add(new Book("Шевченко", "Кобзар", "Поезiя", 200));

        catalog.add(new Newspaper(
                "Новини",
                "01.04.2026",
                new String[] { "Полiтика", "Економiка" }));

        catalog.add(new Almanac(
                "Збiрка",
                new Book[] {
                        new Book("Франко", "Захар Беркут", "Iсторiя", 150),
                }));

        // вивід
        System.out.println("ВСЕ:");
        catalog.printAll();

        // додавання випадкового об'єкта
        System.out.println("\nДОДАЄМО ВИПАДКОВИЙ ОБ'ЄКТ:");
        catalog.addRandom();

        System.out.println("\nПIСЛЯ ДОДАВАННЯ:");
        catalog.printAll();

        // пошук
        System.out.println("\nПОШУК Захар Беркут:");
        catalog.searchByTitle("Захар Беркут");

        System.out.println("\nПОШУК ШЕВЧЕНКО:");
        catalog.searchByAuthor("Шевченко");

        // видалення
        catalog.remove("Кобзар");

        System.out.println("\nПIСЛЯ ВИДАЛЕННЯ:");
        catalog.printAll();
    }
}