import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserManager um = new UserManager();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {

                System.out.println("\n1-Додати 2-Видалити 3-Перевiрка 4-Змiнити логiн 5-Змiнити пароль 6-Вивiд 0-Вихiд");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {

                    case 1:
                        System.out.print("Логiн: ");
                        String login = sc.nextLine();
                        System.out.print("Пароль: ");
                        String pass = sc.nextLine();
                        um.addUser(login, pass);
                        break;

                    case 2:
                        System.out.print("Логiн: ");
                        um.removeUser(sc.nextLine());
                        break;

                    case 3:
                        System.out.print("Логiн: ");
                        System.out.println(um.exists(sc.nextLine()) ? "Iснує" : "Немає");
                        break;

                    case 4:
                        System.out.print("Старий логiн: ");
                        String oldL = sc.nextLine();
                        System.out.print("Новий логiн: ");
                        String newL = sc.nextLine();
                        um.changeLogin(oldL, newL);
                        break;

                    case 5:
                        System.out.print("Логiн: ");
                        String l = sc.nextLine();
                        System.out.print("Новий пароль: ");
                        um.changePassword(l, sc.nextLine());
                        break;

                    case 6:
                        um.printAll();
                        break;

                    case 0:
                        return;
                }
            }
        }
    }
}