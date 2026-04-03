public class Main {
    public static void main(String[] args) {

        ATM atm = new ATM();

        // 1. Ініціалізація
        atm.loadMoney(new int[]{2,2,2,2,2,2,2,2,2});
        atm.printState();

        // 2. Поповнення
        System.out.println("\nПоповнення...");
        atm.deposit(new int[]{1,0,0,0,0,0,0,0,0});
        atm.printState();

        // 3. Успішне зняття
        try {
            System.out.println("\nЗняття 880:");
            atm.withdraw(880);
            atm.printState();
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }

        // 4. Перевищення ліміту
        try {
            System.out.println("\nСпроба зняти 10001:");
            atm.withdraw(10001);
        } catch (ATMException e) {
            System.out.println("Очiкувана помилка: " + e.getMessage());
        }

        // 5. Недостатньо коштів
        try {
            System.out.println("\nСпроба зняти 9999:");
            atm.withdraw(9999);
        } catch (ATMException e) {
            System.out.println("Очiкувана помилка: " + e.getMessage());
        }

        // 6. Неможливо видати суму
        try {
            System.out.println("\nСпроба зняти 3:");
            atm.withdraw(3);
        } catch (ATMException e) {
            System.out.println("Очiкувана помилка: " + e.getMessage());
        }
    }
}