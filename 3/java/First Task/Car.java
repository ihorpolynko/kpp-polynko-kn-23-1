class Car {

    // приватні поля (інкапсуляція)
    private String brand;
    private String plate;
    private int year;
    private boolean isRented;
    private int rentMonth; // місяць оренди
    private int rentDuration; // термін оренди (в місяцях)

    // конструктор
    public Car(String brand, String plate, int year, boolean isRented, int rentMonth, int rentDuration) {
        this.brand = brand;
        this.plate = plate;
        this.year = year;
        this.isRented = isRented;
        this.rentMonth = rentMonth;
        this.rentDuration = rentDuration;
    }

    // перевантажений конструктор (без оренди)
    public Car(String brand, String plate, int year) {
        this(brand, plate, year, false, 0, 0);
    }

    // гетери
    public boolean isRented() {
        return isRented;
    }

    public int getFreeMonth() {
        return rentMonth + rentDuration;
    }

    // перевантаження методу
    public void print() {
        System.out.println(brand + " | " + plate + " | " + year + " | " + (isRented ? "Орендовано" : "Вiльний"));
    }

    public void print(boolean detailed) {
        if (detailed) {
            System.out.println(brand + " | " + plate + " | " + year +
                    " | оренда: " + isRented +
                    " | мiсяць: " + rentMonth +
                    " | термiн: " + rentDuration);
        } else {
            print();
        }
    }
}