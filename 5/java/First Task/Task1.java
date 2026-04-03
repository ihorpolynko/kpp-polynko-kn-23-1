import java.io.*;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Введiть шлях до першого файлу: ");
            String path1 = sc.nextLine();

            System.out.print("Введiть шлях до другого файлу: ");
            String path2 = sc.nextLine();

            try (
                BufferedReader br1 = new BufferedReader(new FileReader(path1));
                BufferedReader br2 = new BufferedReader(new FileReader(path2))
            ) {

                String line1, line2;
                int lineNum = 1;
                boolean same = true;

                while (true) {

                    line1 = br1.readLine();
                    line2 = br2.readLine();

                    if (line1 == null && line2 == null) break;

                    if (!Objects.equals(line1, line2)) {
                        same = false;
                        System.out.println("Рядок " + lineNum + " не збiгається:");
                        System.out.println("Файл1: " + line1);
                        System.out.println("Файл2: " + line2);
                        System.out.println();
                    }

                    lineNum++;
                }

                if (same) {
                    System.out.println("Файли однаковi");
                }

            } catch (IOException e) {
                System.out.println("Помилка: " + e.getMessage());
            }
        }
    }
}