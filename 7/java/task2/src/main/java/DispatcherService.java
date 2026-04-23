import lombok.*;
import driver.Driver;
import trip.Trip;
import vehicle.Vehicle;
import vehicle.VehicleStatus;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class DispatcherService {
    // Черга заявок, які ще не оброблені
    private Queue<Trip> pendingTrips = new ArrayDeque<>();

    // Список доступних автомобілів
    private List<Vehicle> vehicles = new ArrayList<>();

    // Список доступних водіїв
    private List<Driver> drivers = new ArrayList<>();

    // Рейси, які зараз у процесі виконання
    private Set<Trip> tripsInProgress = new HashSet<>();

    // Завершені рейси
    private List<Trip> completedTrips = new ArrayList<>();

    // Логування завершених рейсів у файл
    private StreamLogger<Trip> tripLogger = new StreamLogger<>(Path.of("trips.log"));

    @Builder
    public DispatcherService(Queue<Trip> pendingTrips,
                             List<Vehicle> vehicles,
                             List<Driver> drivers,
                             Set<Trip> tripsInProgress,
                             List<Trip> completedTrips,
                             StreamLogger<Trip> tripLogger) {
        this.pendingTrips = pendingTrips != null ? pendingTrips : new ArrayDeque<>();
        this.vehicles = vehicles != null ? vehicles : new ArrayList<>();
        this.drivers = drivers != null ? drivers : new ArrayList<>();
        this.tripsInProgress = tripsInProgress != null ? tripsInProgress : new HashSet<>();
        this.completedTrips = completedTrips != null ? completedTrips : new ArrayList<>();
        this.tripLogger = tripLogger != null ? tripLogger : new StreamLogger<>(Path.of("trips.log"));
    }

    // Запис рейсу в лог-файл
    public void logTrip(Trip trip) {
        tripLogger.append(trip);
    }

    // Видалити авто зі списку доступних
    public void removeVehicle(Vehicle vehicle) {
        vehicles.remove(vehicle);
    }

    // Видалити водія зі списку доступних
    public void removeDriver(Driver driver) {
        drivers.remove(driver);
    }

    // Додати авто
    public void addVehicle(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    // Додати водія
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }

    // Додати нову заявку
    public void addTrip(Trip trip) {
        pendingTrips.add(trip);
    }

    // Водій просить ремонт
    public void driverRequestRepair(Driver driver) {
        tripsInProgress.stream()
                .filter(trip -> trip.getDriver() != null && trip.getDriver().equals(driver))
                .forEach(trip -> trip.getVehicle().setStatus(VehicleStatus.REPAIR));
    }

    // Водій підтверджує, що авто в нормі
    public void driverRequestOk(Driver driver) {
        tripsInProgress.stream()
                .filter(trip -> trip.getDriver() != null && trip.getDriver().equals(driver))
                .forEach(trip -> trip.getVehicle().setStatus(VehicleStatus.OK));
    }

    // Водій повідомляє, що авто зламалось
    public void driverReportBrokenVehicle(Driver driver) {
        tripsInProgress.stream()
                .filter(trip -> trip.getDriver() != null && trip.getDriver().equals(driver))
                .forEach(trip -> trip.getVehicle().setStatus(VehicleStatus.BROKEN));
    }

    // Призначення наступного рейсу
    public boolean assignNextTrip() {
        if (pendingTrips.isEmpty() || vehicles.isEmpty() || drivers.isEmpty()) {
            return false;
        }

        // Дивимось наступну заявку, але не забираємо її, поки не знайдемо ресурс
        Trip trip = pendingTrips.peek();

        Optional<Vehicle> vehicleOptional = findOptimalVehicle(trip);
        Optional<Driver> driverOptional = findOptimalDriver(trip, vehicleOptional.orElse(null));

        // Якщо немає відповідного авто або водія — рейс не створюємо
        if (vehicleOptional.isEmpty() || driverOptional.isEmpty()) {
            return false;
        }

        // Тепер можна забрати заявку з черги
        trip = pendingTrips.poll();

        Vehicle vehicle = vehicleOptional.get();
        Driver driver = driverOptional.get();

        // Призначаємо авто і водія на рейс
        trip.setVehicle(vehicle);
        trip.setDriver(driver);
        trip.setStartDate(LocalDate.now());
        trip.setPayment(trip.calculatePayment());

        // Прибираємо їх зі списків вільних
        removeVehicle(vehicle);
        removeDriver(driver);

        // Додаємо рейс у список активних
        tripsInProgress.add(trip);

        return true;
    }

    // Пошук найкращого авто під вагу вантажу
    private Optional<Vehicle> findOptimalVehicle(Trip trip) {
        int neededWeight = trip.getNeededWeight();

        return vehicles.stream()
                .filter(v -> v.getStatus() == VehicleStatus.OK)
                .filter(v -> v.getMaxWeight() >= neededWeight)
                .min(Comparator.comparingInt(v -> v.getMaxWeight() - neededWeight));
    }

    // Пошук найкращого водія під умови рейсу
    private Optional<Driver> findOptimalDriver(Trip trip, Vehicle vehicle) {
        int neededExperience = trip.getNeededExperience();
        int vehicleComplexity = vehicle == null ? 0 : vehicle.getDrivingComplexity();
        int extraNeed = neededExperience + vehicleComplexity;

        return drivers.stream()
                .filter(d -> d.getExperience() >= extraNeed)
                .min(Comparator.comparingInt(Driver::getExperience));
    }

    // Завершення рейсу
    public boolean completeTrip(Trip trip) {
        if (tripsInProgress.remove(trip)) {
            trip.setEndDate(LocalDate.now());

            if (trip.getPayment() == null) {
                trip.setPayment(trip.calculatePayment());
            }

            // Додаємо заробіток водію
            trip.getDriver().addEarnings(trip.getPayment());
            trip.getDriver().incrementCompletedTrips();

            // Повертаємо авто і водія в список доступних
            vehicles.add(trip.getVehicle());
            drivers.add(trip.getDriver());

            // Зберігаємо рейс у завершені
            completedTrips.add(trip);

            // Пишемо в лог-файл
            logTrip(trip);
            return true;
        }
        return false;
    }

    // Статистика рейсів по водіях
    public Map<String, Long> getCompletedTripsByDriver() {
        return completedTrips.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getDriver().getSurname() + " " + t.getDriver().getName(),
                        Collectors.counting()
                ));
    }

    // Статистика рейсів по пунктах призначення
    public Map<String, Long> getCompletedTripsByDestination() {
        return completedTrips.stream()
                .collect(Collectors.groupingBy(
                        t -> t.getAddress().address(),
                        Collectors.counting()
                ));
    }

    // Топ водіїв за заробітком
    public List<Driver> getTopEarnersFromHistory() {
        return completedTrips.stream()
                .map(Trip::getDriver)
                .distinct()
                .sorted(Comparator.comparingDouble(Driver::getEarnings).reversed())
                .toList();
    }

    // Вивід статистики у консоль
    public void printStatistics() {
        System.out.println("=== Статистика автобази ===");
        System.out.println("Кількість завершених рейсів: " + completedTrips.size());

        System.out.println("Рейси за водіями:");
        getCompletedTripsByDriver().forEach((driver, count) ->
                System.out.println(driver + " -> " + count));

        System.out.println("Рейси за пунктами призначення:");
        getCompletedTripsByDestination().forEach((destination, count) ->
                System.out.println(destination + " -> " + count));

        System.out.println("Найбільший заробіток:");
        getTopEarnersFromHistory().forEach(driver ->
                System.out.println(driver.getSurname() + " " + driver.getName() + " -> " + driver.getEarnings()));
    }
}
