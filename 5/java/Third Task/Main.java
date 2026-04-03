import java.util.*;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
                Corporation corp = new Corporation();

                System.out.print("Введiть файл: ");
                String file = sc.nextLine();

                corp.load(file);

                while (true) {

                    System.out.println("\n1-Додати 2-Видалити 3-Пошук 4-Вивiд 5-Сортування 6-Фiльтр 0-Вихiд");
                    int choice = sc.nextInt();
                    sc.nextLine();

                    switch (choice) {

                        case 1:
                            System.out.print("Прiзвище: ");
                            String s = sc.nextLine();
                            System.out.print("Вiк: ");
                            int age = sc.nextInt();
                            corp.add(new Employee(s, age));
                            break;

                        case 2:
                            System.out.print("Кого видалити: ");
                            corp.remove(sc.nextLine());
                            break;

                        case 3:
                            System.out.print("Пошук: ");
                            corp.search(sc.nextLine());
                            break;

                        case 4:
                            corp.printAll();
                            break;

                        case 5:
                            corp.sortByAge();
                            break;

                        case 6:
                            System.out.print("Лiтера: ");
                            corp.filterByLetter(sc.nextLine().charAt(0));
                            break;

                        case 0:
                            corp.save(file);
                            return;
                    }
                }
        }
    }
}