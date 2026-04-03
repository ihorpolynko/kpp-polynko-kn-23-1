import java.io.*;
import java.util.*;

class Corporation {

    private ArrayList<Employee> list = new ArrayList<>();

    // завантаження з файлу
    public void load(String file) {

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), "UTF-8"));) {

            String line;
            while ((line = br.readLine()) != null) {
                list.add(Employee.fromString(line));
            }

        } catch (IOException e) {
            System.out.println("Помилка читання");
        }
    }

    // збереження
    public void save(String file) {

        try (PrintWriter pw = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));) {

            for (Employee e : list) {
                pw.println(e.toFileString());
            }

        } catch (IOException e) {
            System.out.println("Помилка запису");
        }
    }

    // додати
    public void add(Employee e) {
        list.add(e);
    }

    // видалити
    public void remove(String surname) {
        list.removeIf(e -> e.getSurname().equalsIgnoreCase(surname));
    }

    // пошук
    public void search(String surname) {
        for (Employee e : list) {
            if (e.getSurname().equalsIgnoreCase(surname)) {
                e.print();
            }
        }
    }

    // вивід всіх
    public void printAll() {
        for (Employee e : list)
            e.print();
    }

    // сортування за віком
    public void sortByAge() {
        list.sort(Comparator.comparingInt(Employee::getAge));
    }

    // фільтр по літері
    public void filterByLetter(char c) {
        for (Employee e : list) {
            if (e.getSurname().startsWith(String.valueOf(c))) {
                e.print();
            }
        }
    }
}