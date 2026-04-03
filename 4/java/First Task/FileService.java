import java.io.*;

interface FileService {

    // запис масиву у файл
    static void saveToFile(Car[] cars, String filename) throws Exception {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));

        out.writeObject(cars); // записуємо весь масив

        out.close();
    }

    // зчитування масиву з файлу
    static Car[] loadFromFile(String filename) throws Exception {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));

        Car[] cars = (Car[]) in.readObject(); // читаємо масив

        in.close();

        return cars;
    }
}