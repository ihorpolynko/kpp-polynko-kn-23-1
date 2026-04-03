import java.util.Random;

class Utils {

    static Random r = new Random();

    static String randomTitle() {
        String[] t = { "Свiт", "Наука", "Техно", "Життя" };
        return t[r.nextInt(t.length)];
    }

    static String randomAuthor() {
        String[] a = { "Шевченко", "Франко", "Костенко" };
        return a[r.nextInt(a.length)];
    }

    static String randomGenre() {
        String[] a = { "Шевченко", "Франко", "Костенко" };
        return a[r.nextInt(a.length)];
    }

    static Book randomBook() {
        return new Book(
                randomAuthor(),
                randomTitle(),
                "Жанр: Нонфiкшн",
                100 + r.nextInt(300));
    }

    static Newspaper randomNewspaper() {
        return new Newspaper(
                randomTitle(),
                "02.04.2026",
                new String[] { "Вiйна", "Катаклiзми" });
    }
}