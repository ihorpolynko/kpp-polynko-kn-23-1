import java.io.Serializable;

class Car implements Serializable, Comparable<Car> {

    private String brand;
    private String plate;
    private int year;
    private boolean isRented;
    private int rentMonth;
    private int rentDuration;

    public Car(String brand, String plate, int year, boolean isRented, int rentMonth, int rentDuration) {
        this.brand = brand;
        this.plate = plate;
        this.year = year;
        this.isRented = isRented;
        this.rentMonth = rentMonth;
        this.rentDuration = rentDuration;
    }

    public Car(String brand, String plate, int year) {
        this(brand, plate, year, false, 0, 0);
    }

    public boolean isRented() {
        return isRented;
    }

    public int getFreeMonth() {
        return rentMonth + rentDuration;
    }

    @Override
    public int compareTo(Car other) {

        // правило 1: спочатку по року (новіші вище)
        if (this.year != other.year) {
            return other.year - this.year;
        }

        // правило 2: якщо однаковий рік — по марці
        return this.brand.compareTo(other.brand);
    }

    public void print() {
        System.out.println(brand + " | " + plate + " | " + year + " | " + (isRented ? "Орендовано" : "Вiльний"));
    }
}