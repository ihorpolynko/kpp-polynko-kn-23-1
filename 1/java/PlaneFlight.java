// Підключення класів для роботи з файлами
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// Головний клас програми
public class PlaneFlight {

    public static void main(String[] args) {

        try {

            try (// Створення об'єкта Scanner для зчитування даних з файлу
            Scanner input = new Scanner(new FileReader("data.txt"))) {
                // Зчитування ємності бака літака
                double tank = input.nextDouble();

                // Зчитування відстані між пунктами A і B
                double distanceAB = input.nextDouble();

                // Зчитування відстані між пунктами B і C
                double distanceBC = input.nextDouble();

                // Зчитування ваги вантажу
                double weight = input.nextDouble();

                // Змінна для витрати палива на 1 км
                double fuelPerKm = 0;

                // Визначення витрати палива залежно від ваги
                if (weight <= 500) {
                    fuelPerKm = 1;
                } else if (weight <= 1000) {
                    fuelPerKm = 4;
                } else if (weight <= 1500) {
                    fuelPerKm = 7;
                } else if (weight <= 2000) {
                    fuelPerKm = 9;
                } else {
                    // Якщо вантаж більше 2000 кг — літак не може злетіти
                    System.out.println("Лiтак не може пiдняти такий вантаж.");
                    return;
                }

                // Розрахунок необхідного палива для ділянки A-B
                double fuelAB = distanceAB * fuelPerKm;

                // Розрахунок необхідного палива для ділянки B-C
                double fuelBC = distanceBC * fuelPerKm;

                // Перевірка чи може літак долетіти до пункту B
                if (fuelAB > tank) {
                    System.out.println("Неможливо долетiти з A до B.");
                    return;
                }

                // Перевірка чи можливо подолати відстань B-C
                if (fuelBC > tank) {
                    System.out.println("Неможливо долетiти з B до C навiть пiсля заправки.");
                    return;
                }

                // Кількість палива, що залишиться після польоту A-B
                double fuelLeft = tank - fuelAB;

                // Мінімальна кількість палива для дозаправки
                double refuel = fuelBC - fuelLeft;

                // Якщо палива вистачає без дозаправки
                if (refuel < 0) {
                    refuel = 0;
                }

                // Виведення результату
                System.out.println("Мiнiмальна кiлькiсть палива для дозаправки: " + refuel + " лiтрiв");
            }

        } catch (IOException ex) {

            // Повідомлення про помилку читання файлу
            System.out.println("Помилка читання файлу: " + ex.getMessage());
        }
    }
}