package trip;
import lombok.Data;
import lombok.NonNull;
import driver.Driver;
import vehicle.Vehicle;

import java.time.LocalDate;

@Data
public class Trip {
    // Призначений автомобіль для рейсу
    private Vehicle vehicle;

    // Призначений водій для рейсу
    private Driver driver;

    // Дата початку рейсу
    private LocalDate startDate;

    // Дата завершення рейсу
    private LocalDate endDate;

    // Відстань маршруту
    @NonNull
    private Double distance;

    // Пункт призначення
    @NonNull
    private Address address;

    // Вага вантажу
    @NonNull
    private Integer neededWeight;

    // Тип вантажу
    @NonNull
    private CargoType cargoType;

    // Оплата за рейс
    private Double payment;

    // Розрахунок необхідного стажу водія
    public Integer getNeededExperience() {
        int byCargo = cargoType.getRequiredExperience();
        int byDistance = Math.max(1, (int) Math.ceil(distance / 250.0));
        int byWeight = Math.max(1, neededWeight / 250);
        return byCargo + byDistance + byWeight;
    }

    // Розрахунок вартості рейсу
    public double calculatePayment() {
        double base = neededWeight * distance * 0.5;
        return base * cargoType.getPaymentMultiplier();
    }

    @Override
    public String toString() {
        return """
                Рейс:
                  Автомобіль:
                %s
                  Водій:
                %s
                  Дата початку: %s
                  Дата завершення: %s
                  Відстань: %.2f км
                  %s
                  Вага вантажу: %d кг
                  Тип вантажу: %s
                  Оплата: %.2f грн
                """.formatted(
                vehicle == null ? "    не призначено" : indent(vehicle.toString(), 4),
                driver == null ? "    не призначено" : indent(driver.toString(), 4),
                startDate,
                endDate,
                distance,
                address,
                neededWeight,
                cargoType,
                payment == null ? 0.0 : payment
        ).trim();
    }

    // Допоміжний метод для красивого відступу в багаторядковому тексті
    private String indent(String text, int spaces) {
        String prefix = " ".repeat(spaces);
        return text.replaceAll("(?m)^", prefix);
    }
}