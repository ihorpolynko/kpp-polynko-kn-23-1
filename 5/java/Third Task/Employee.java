import java.io.Serializable;

class Employee implements Serializable {

    private String surname;
    private int age;

    public Employee(String surname, int age) {
        this.surname = surname;
        this.age = age;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public void print() {
        System.out.println(surname + " | " + age);
    }

    // запис в файл (строкой)
    public String toFileString() {
        return surname + "," + age;
    }

    // создание из строки
    public static Employee fromString(String line) {
        String[] parts = line.split(",");
        return new Employee(parts[0], Integer.parseInt(parts[1]));
    }
}