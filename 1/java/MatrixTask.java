import java.util.Random;

public class MatrixTask {

    public static void main(String[] args) {

        // Розмірність масиву
        int rows = 4;
        int cols = 5;

        // Створення двовимірного масиву
        int[][] matrix = new int[rows][cols];

        Random rand = new Random();

        // Заповнення масиву випадковими числами
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = rand.nextInt(100); // числа від 0 до 99
            }
        }

        System.out.println("Початковий масив:");
        printMatrix(matrix);

        // Пошук максимального елемента
        int max = matrix[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Максимальний елемент: " + max);

        // Перестановка рядків (міняємо рядок з максимальним елементом з нульовим)
        int[] tempRow = matrix[0];
        matrix[0] = matrix[maxRow];
        matrix[maxRow] = tempRow;

        // Перестановка стовпців
        for (int i = 0; i < rows; i++) {

            int temp = matrix[i][0];
            matrix[i][0] = matrix[i][maxCol];
            matrix[i][maxCol] = temp;
        }

        System.out.println("Масив пiсля перестановки:");
        printMatrix(matrix);
    }

    // Метод виводу масиву
    public static void printMatrix(int[][] m) {

        for (int i = 0; i < m.length; i++) {

            for (int j = 0; j < m[i].length; j++) {
                System.out.print(m[i][j] + "\t");
            }

            System.out.println();
        }
    }
}