import com.github.javafaker.Faker;
import driver.Driver;
import trip.Address;
import trip.CargoType;
import trip.Trip;
import vehicle.DefaultVehicle;
import vehicle.Vehicle;
import vehicle.VehicleStatus;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {

    // Імена водіїв українською
    private static final String[] DRIVER_NAMES = {
            "Олег", "Іван", "Андрій", "Сергій", "Дмитро",
            "Максим", "Тарас", "Віталій", "Петро", "Юрій"
    };

    // Прізвища водіїв українською
    private static final String[] DRIVER_SURNAMES = {
            "Коваленко", "Шевченко", "Бондаренко", "Ткаченко", "Мельник",
            "Кравченко", "Савченко", "Петренко", "Лисенко", "Мороз"
    };

    // Міста для маршрутів
    private static final String[] CITIES = {
            "Київ", "Львів", "Одеса", "Харків", "Дніпро",
            "Полтава", "Черкаси", "Вінниця", "Івано-Франківськ", "Чернігів"
    };

    // Вулиці для маршрутів
    private static final String[] STREETS = {
            "Хрещатик", "Шевченка", "Соборна", "Незалежності", "Грушевського",
            "Київська", "Лесі Українки", "Садова", "Шкільна", "Центральна"
    };

    // Простий набір зрозумілих автомобілів
    private static final String[] VEHICLES = {
            "Mercedes Sprinter",
            "Mercedes Actros",
            "MAN TGL",
            "MAN TGX",
            "Renault Master",
            "Renault Premium",
            "Volkswagen Crafter",
            "Iveco Daily",
            "DAF XF",
            "Volvo FH"
    };

    public static void main(String[] args) {
        Faker faker = new Faker();

        // Список вільних автомобілів
        List<Vehicle> vehicles = new ArrayList<>();

        // Список вільних водіїв
        List<Driver> drivers = new ArrayList<>();

        // Черга нових заявок на рейси
        Queue<Trip> trips = new ArrayDeque<>();

        // Генеруємо випадкові дані для демонстрації роботи системи
        for (int i = 0; i < 10; i++) {
            // Створюємо автомобіль з назвою, вантажопідйомністю та складністю керування
            vehicles.add(new DefaultVehicle(
                    VEHICLES[i],
                    faker.number().numberBetween(100, 1000),
                    faker.number().numberBetween(1, 5),
                    VehicleStatus.OK
            ));

            // Створюємо водія
            drivers.add(new Driver(
                    DRIVER_NAMES[i],
                    DRIVER_SURNAMES[i],
                    "+380" + faker.number().digits(9),
                    faker.number().numberBetween(1, 20)
            ));

            // Створюємо заявку на перевезення
            trips.add(new Trip(
                    faker.number().randomDouble(2, 100, 1000),
                    new Address(
                            CITIES[faker.number().numberBetween(0, CITIES.length - 1)] +
                                    ", вул. " + STREETS[faker.number().numberBetween(0, STREETS.length - 1)] +
                                    ", буд. " + faker.number().numberBetween(1, 100)
                    ),
                    faker.number().numberBetween(50, 500),
                    CargoType.values()[faker.number().numberBetween(0, CargoType.values().length - 1)]
            ));
        }

        // Створюємо диспетчера з готовими списками
        var dispatcher = DispatcherService.builder()
                .drivers(drivers)
                .vehicles(vehicles)
                .pendingTrips(trips)
                .build();

        // Запускаємо обробку рейсів
        doSomething(dispatcher);
    }

    public static void doSomething(DispatcherService dispatcherService) {
        // Поки можна призначати нові рейси — призначаємо їх
        while (dispatcherService.assignNextTrip()) {
        }

        // Показуємо всі активні рейси
        System.out.println("\n=== Активні рейси ===");
        dispatcherService.getTripsInProgress().forEach(System.out::println);

        // Показуємо статистику до завершення рейсів
        dispatcherService.printStatistics();

        // Завершуємо всі активні рейси
        var tripsInProgress = dispatcherService.getTripsInProgress();
        for (Trip trip : new ArrayList<>(tripsInProgress)) {
            dispatcherService.completeTrip(trip);
        }

        // Показуємо статистику після завершення рейсів
        dispatcherService.printStatistics();
    }
}
