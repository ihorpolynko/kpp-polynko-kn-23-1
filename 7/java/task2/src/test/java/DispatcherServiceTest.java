import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import driver.Driver;
import trip.Address;
import trip.CargoType;
import trip.Trip;
import vehicle.DefaultVehicle;
import vehicle.Vehicle;
import vehicle.VehicleStatus;


import static org.junit.jupiter.api.Assertions.*;

class DispatcherServiceTest {

    private DispatcherService dispatcher;

    private Vehicle vehicle;
    private Driver driver;
    private Trip trip;

    @BeforeEach
    void setUp() {
        // Створюємо новий диспетчер перед кожним тестом
        dispatcher = new DispatcherService();

        // Готуємо тестові автомобіль, водія і заявку
        vehicle = new DefaultVehicle("Автомобіль1", 1000, 3, VehicleStatus.OK);
        driver = new Driver("Ігор", "Полинько", "0987654321", 10);
        trip = new Trip(500.0, new Address("Київ"), 500, CargoType.GENERAL);

        // Додаємо їх в систему
        dispatcher.addVehicle(vehicle);
        dispatcher.addDriver(driver);
        dispatcher.addTrip(trip);
    }

    // Перевіряємо, що рейс успішно назначається, якщо усі ресурси є
    @Test
    void assignNextTrip_success() {
        boolean result = dispatcher.assignNextTrip();

        assertTrue(result);
        assertEquals(1, dispatcher.getTripsInProgress().size());
        assertTrue(dispatcher.getPendingTrips().isEmpty());

        Trip assignedTrip = dispatcher.getTripsInProgress().iterator().next();
        assertNotNull(assignedTrip.getDriver());
        assertNotNull(assignedTrip.getVehicle());
        assertNotNull(assignedTrip.getStartDate());
        assertNotNull(assignedTrip.getPayment());
    }

    // Перевіряємо, що якщо нема заявок, рейс не призначається
    @Test
    void assignNextTrip_noTrips() {
        dispatcher = new DispatcherService();
        dispatcher.addVehicle(vehicle);
        dispatcher.addDriver(driver);

        boolean result = dispatcher.assignNextTrip();

        assertFalse(result);
    }

    // Перевіряємо, що якщо нема потрібного автомобілю, рейс не призначається
    @Test
    void assignNextTrip_noVehicleForWeight() {
        dispatcher = new DispatcherService();

        Vehicle smallVehicle = new DefaultVehicle("Маленький автомобіль", 100, 1, VehicleStatus.OK);

        dispatcher.addVehicle(smallVehicle);
        dispatcher.addDriver(driver);
        dispatcher.addTrip(trip);

        boolean result = dispatcher.assignNextTrip();

        assertFalse(result);
    }

    // Перевіряємо, що якщо водію не вистачає досвіду, рейс не призначається
    @Test
    void assignNextTrip_noDriverForExperience() {
        dispatcher = new DispatcherService();

        Driver inexperiencedDriver = new Driver("Ігор", "Полинько", "0987654321", 1);

        dispatcher.addVehicle(vehicle);
        dispatcher.addDriver(inexperiencedDriver);
        dispatcher.addTrip(trip);

        boolean result = dispatcher.assignNextTrip();

        assertFalse(result);
    }

    // Перевіряємо, що після завершення рейсу усі ресурси повертаються назад
    @Test
    void completeTrip_success() {
        dispatcher.assignNextTrip();

        Trip activeTrip = dispatcher.getTripsInProgress().iterator().next();

        boolean result = dispatcher.completeTrip(activeTrip);

        assertTrue(result);
        assertEquals(1, dispatcher.getCompletedTrips().size());
        assertTrue(dispatcher.getTripsInProgress().isEmpty());
        assertEquals(1, dispatcher.getDrivers().size());
        assertEquals(1, dispatcher.getVehicles().size());
    }

    // Перевіряємо, що завершить рейс, якого нема серед активних, не можна
    @Test
    void completeTrip_notFound() {
        boolean result = dispatcher.completeTrip(trip);

        assertFalse(result);
    }

    // Перевіряємо, що заявка на ремонт змінює статус автомобіля на REPAIR
    @Test
    void driverRequestRepair_changesStatus() {
        dispatcher.assignNextTrip();
        Driver activeDriver = dispatcher.getTripsInProgress().iterator().next().getDriver();

        dispatcher.driverRequestRepair(activeDriver);

        assertEquals(VehicleStatus.REPAIR, dispatcher.getTripsInProgress().iterator().next().getVehicle().getStatus());
    }

    // Перевіряємо, що повідомлення о поламці переводить авто у статус BROKEN
    @Test
    void driverReportBrokenVehicle_changesStatus() {
        dispatcher.assignNextTrip();
        Driver activeDriver = dispatcher.getTripsInProgress().iterator().next().getDriver();

        dispatcher.driverReportBrokenVehicle(activeDriver);

        assertEquals(VehicleStatus.BROKEN, dispatcher.getTripsInProgress().iterator().next().getVehicle().getStatus());
    }
}