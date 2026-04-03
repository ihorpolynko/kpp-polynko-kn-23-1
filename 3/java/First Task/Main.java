public class Main {

    public static void main(String[] args) {

        Car[] cars = new Car[5];

        // генерація масиву
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(
                    Utils.randomBrand(),
                    "AA" + i,
                    Utils.randomYear(),
                    Utils.randomRent(),
                    Utils.randomMonth(),
                    Utils.randomDuration()
            );
        }

        // а) всі авто
        System.out.println("ВСI АВТО:");
        for (Car c : cars) c.print();

        // б) тільки вільні
        System.out.println("\nВIЛЬНI:");
        for (Car c : cars)
            if (!c.isRented())
                c.print();

        // б) орендовані
        System.out.println("\nОРЕНДОВАНI:");
        for (Car c : cars)
            if (c.isRented())
                c.print();

        // в) пошук по місяцю
        int searchMonth = 5;

        System.out.println("\nЗВIЛЬНЯТЬСЯ У МIСЯЦI " + searchMonth);
        for (Car c : cars)
            if (c.getFreeMonth() == searchMonth)
                c.print(true);
    }
}