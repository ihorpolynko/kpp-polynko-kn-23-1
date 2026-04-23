package driver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    // Перевіряємо, що при додаванні грошей збільшується заробіток водія
    @Test
    void addEarnings_shouldIncreaseEarnings() {
        Driver driver = new Driver("Ігор", "Полинько", "0987654321", 5);

        driver.addEarnings(150.5);

        assertEquals(150.5, driver.getEarnings());
    }

    // Перевіряємо, що після завершения рейсу збільшується рахунок виконаних рейсів
    @Test
    void incrementCompletedTrips_shouldIncreaseCounter() {
        Driver driver = new Driver("Ігор", "Полинько", "0987654321", 5);

        driver.incrementCompletedTrips();

        assertEquals(1, driver.getCompletedTrips());
    }
}
