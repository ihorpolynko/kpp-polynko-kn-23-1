import java.util.Scanner;

public class StringTasks {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            // ===== ЗАВДАННЯ 1 =====
            System.out.println("Введiть текст:");
            String text = sc.nextLine();

            countWordsAndSentences(text);
            duplicateLongestSentence(text);
            filterForbiddenWords(text);

            // ===== ЗАВДАННЯ 3 =====
            System.out.println("\nВведiть пароль:");
            String password = sc.nextLine();

            checkPassword(password);

            // ===== ЗАВДАННЯ 5 =====
            System.out.println("\nВведiть рядок з email:");
            String emails = sc.nextLine();

            removeRuEmails(emails);
        }
    }

    // ===============================
    // Завдання 1: підрахунок слів і речень
    static void countWordsAndSentences(String text) {

        // Розбиваємо текст на слова (по пробілах)
        String[] words = text.trim().split("\\s+");

        // Рахуємо речення (по ., !, ?)
        String[] sentences = text.split("[.!?]");

        System.out.println("Кiлькiсть слiв: " + words.length);
        System.out.println("Кiлькiсть речень: " + sentences.length);
    }

    // ===============================
    // Завдання 1: найдовше речення
    static void duplicateLongestSentence(String text) {

        String[] sentences = text.split("[.!?]");

        String longest = "";

        for (String s : sentences) {

            if (s.length() > longest.length()) {
                longest = s;
            }
        }

        System.out.println("Дубль найдовшого речення:");
        System.out.println(longest.trim());
    }

    // ===============================
    // Завдання 1: фільтр заборонених слів
    static void filterForbiddenWords(String text) {

        String[] forbidden = {"bad", "word", "java"}; // приклад

        for (String f : forbidden) {

            String stars = "";

            // створюємо рядок зірочок такої ж довжини
            for (int i = 0; i < f.length(); i++) {
                stars += "*";
            }

            // заміна слова
            text = text.replaceAll("(?i)" + f, stars);
        }

        System.out.println("Текст пiсля фiльтрацii:");
        System.out.println(text);
    }

    // ===============================
    // Завдання 3: перевірка пароля
    static void checkPassword(String password) {

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSymbol = false;

        // перевірка довжини
        if (password.length() < 8) {
            System.out.println("Пароль ненадiйний (менше 8 символiв)");
            return;
        }

        // перевірка кожного символу
        for (int i = 0; i < password.length(); i++) {

            char c = password.charAt(i);

            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (c == '!' || c == '*' || c == '_') hasSymbol = true;
        }

        // фінальна перевірка
        if (hasUpper && hasLower && hasDigit && hasSymbol) {
            System.out.println("Пароль надiйний");
        } else {
            System.out.println("Пароль ненадiйний");
        }
    }

    // ===============================
    // Завдання 5: видалення .ru email
    static void removeRuEmails(String text) {

        String[] parts = text.split("\\s+");

        String result = "";

        for (String email : parts) {

            // якщо не містить .ru — додаємо
            if (!email.endsWith(".ru")) {
                result += email + " ";
            }
        }

        System.out.println("Пiсля видалення .ru:");
        System.out.println(result.trim());
    }
}