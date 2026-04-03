import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        CafeQueue cafe = new CafeQueue();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n1-Додати вiдвiдувача 2-Додати резерв 3-Вiльний столик 4-Вихiд");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Iм'я вiдвiдувача: ");
                        cafe.addVisitor(sc.nextLine());
                        break;
                    case 2:
                        System.out.print("Iм'я (резерв): ");
                        String name = sc.nextLine();
                        System.out.print("Час резерву (число або HH:MM): ");
                        String timeStr = sc.nextLine().trim();
                        int time = parseTimeToMinutes(timeStr);
                        if (time < 0) {
                            System.out.println("Невірний формат часу резерву. Використано -1 (без резерву).");
                        }
                        cafe.addReserved(name, time);
                        break;
                    case 3:
                        System.out.print("Поточний час (HH:MM або година): ");
                        String nowStr = sc.nextLine().trim();
                        int now = parseTimeToMinutes(nowStr);
                        if (now < 0) {
                            System.out.println("Невірний формат поточного часу. Використано 0.");
                            now = 0;
                        }
                        cafe.serve(now);
                        break;
                    case 4:
                        return;
                }
            }
        }
    }

    private static int parseTimeToMinutes(String s) {
        if (s == null || s.isEmpty()) return -1;
        s = s.trim();
        if (s.contains(":")) {
            String[] parts = s.split(":");
            if (parts.length >= 2) {
                try {
                    int hh = Integer.parseInt(parts[0].trim());
                    int mm = Integer.parseInt(parts[1].trim());
                    if (hh >= 0 && hh < 24 && mm >= 0 && mm < 60) return hh * 60 + mm;
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
            return -1;
        }

        String digits = s.replaceAll("\\D+", "");
        if (digits.isEmpty()) return -1;

        try {
            if (digits.length() <= 2) {
                int hh = Integer.parseInt(digits);
                if (hh >= 0 && hh < 24) return hh * 60;
                return -1;
            } else if (digits.length() == 3 || digits.length() == 4) {
                int hh = Integer.parseInt(digits.substring(0, digits.length() - 2));
                int mm = Integer.parseInt(digits.substring(digits.length() - 2));
                if (hh >= 0 && hh < 24 && mm >= 0 && mm < 60) return hh * 60 + mm;
                return -1;
            } else {
                return Integer.parseInt(digits);
            }
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
}