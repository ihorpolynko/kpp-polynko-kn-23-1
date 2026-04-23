package trip;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    // Перевіряємо, що розрахунок оплти за рейс повертає позитивне значение
    @Test
    void calculatePayment_shouldReturnPositiveValue() {
        Trip trip = new Trip(300.0, new Address("Львів"), 200, CargoType.FRAGILE);

        double payment = trip.calculatePayment();

        assertTrue(payment > 0);
    }

    // Перевіряємо, що необхідний стаж залежить від параметрів рейсу
    @Test
    void getNeededExperience_shouldDependOnTripData() {
        Trip trip = new Trip(500.0, new Address("Одеса"), 300, CargoType.DANGEROUS);

        int experience = trip.getNeededExperience();

        assertTrue(experience > 0);
    }
}
