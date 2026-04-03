import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Street street = new Street();
        Scanner sc = new Scanner(System.in);

        // тестова генерація
        for (int i = 0; i < 3; i++) {
            street.add(Factory.randomHouse());
            street.add(Factory.randomShop());
            street.add(Factory.randomSchool());
            street.add(Factory.randomHospital());
        }

        boolean running = true;
        while (running) {
            System.out.println("\n1 - Показати всю вулицю");
            System.out.println("2 - Знайти магазини за вiддiлом");
            System.out.println("3 - Додати будинок");
            System.out.println("4 - Видалити будинок за адресою");
            System.out.println("5 - Знайти магазини поруч з будинком");
            System.out.println("0 - Вихiд");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    street.printAll();
                    break;
                case 2:
                    System.out.print("Введiть вiддiл: ");
                    String dep = sc.nextLine();
                    street.findShops(dep);
                    break;
                case 3:
                    System.out.println("1-House 2-Shop 3-School 4-Hospital");
                    int type = sc.nextInt();
                    sc.nextLine();
                    switch (type) {
                        case 1:
                            street.add(Factory.randomHouse());
                            break;
                        case 2:
                            street.add(Factory.randomShop());
                            break;
                        case 3:
                            street.add(Factory.randomSchool());
                            break;
                        case 4:
                            street.add(Factory.randomHospital());
                            break;
                    }
                    break;
                case 4:
                    System.out.print("Введiть адресу: ");
                    street.remove(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Введiть адресу будинку: ");
                    String addr = sc.nextLine();

                    System.out.print("Введiть вiддiл: ");
                    String depNearby = sc.nextLine();

                    House selectedHouse = null;

                    for (Building b : street.getBuildings()) {
                        if (b instanceof House && b.address.equalsIgnoreCase(addr)) {
                            selectedHouse = (House) b;
                            break;
                        }
                    }

                    if (selectedHouse == null) {
                        System.out.println("Будинок не знайдено");
                        break;
                    }

                    System.out.println("Обраний будинок:");
                    selectedHouse.print();

                    System.out.println("Магазини поруч:");
                    street.findNearbyShops(selectedHouse, 2, depNearby);
                    break;
                case 0:
                    running = false;
                    break;
            }
        }
        sc.close();
    }
}