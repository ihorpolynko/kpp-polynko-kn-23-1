package driver;

import lombok.*;

@Data
public class Driver {
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String phone;
    @NonNull
    private Integer experience;

    // Заробіток водія
    private double earnings = 0.0;

    // Кількість виконаних рейсів
    private int completedTrips = 0;

    // Додати гроші до заробітку
    public void addEarnings(double amount) {
        earnings += amount;
    }

    // Збільшити кількість виконаних рейсів
    public void incrementCompletedTrips() {
        completedTrips++;
    }

    @Override
    public String toString() {
        return """
                  Ім'я: %s
                  Прізвище: %s
                  Телефон: %s
                  Стаж: %d
                  Заробіток: %.2f грн
                  Виконано рейсів: %d
                """.formatted(name, surname, phone, experience, earnings, completedTrips).trim();
    }
}
