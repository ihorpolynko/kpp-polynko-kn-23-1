import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Car[] cars = new Car[5];

        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(
                    Utils.randomBrand(),
                    "AA" + i,
                    Utils.randomYear(),
                    Utils.randomRent(),
                    Utils.randomMonth(),
                    Utils.randomDuration()
            );
        }

        // ДО СОРТУВАННЯ
        System.out.println("ДО СОРТУВАННЯ:");
        for (Car c : cars) c.print();

        // СОРТУВАННЯ
        Arrays.sort(cars);

        System.out.println("\nПIСЛЯ СОРТУВАННЯ:");
        for (Car c : cars) c.print();

        try {
            // ЗАПИС У ФАЙЛ
            FileService.saveToFile(cars, "cars.dat");

            // ЧИТАННЯ З ФАЙЛУ
            Car[] loaded = FileService.loadFromFile("cars.dat");

            System.out.println("\nЗ ФАЙЛУ:");
            for (Car c : loaded) c.print();

        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}