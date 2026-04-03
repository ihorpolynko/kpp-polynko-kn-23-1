import java.util.Random;

class Utils {

    static String[] brands = {"BMW", "Audi", "Toyota", "Ford", "Tesla"};

    static Random rand = new Random();

    public static String randomBrand() {
        return brands[rand.nextInt(brands.length)];
    }

    public static int randomYear() {
        return 2000 + rand.nextInt(25);
    }

    public static boolean randomRent() {
        return rand.nextBoolean();
    }

    public static int randomMonth() {
        return 1 + rand.nextInt(12);
    }

    public static int randomDuration() {
        return 1 + rand.nextInt(6);
    }
}