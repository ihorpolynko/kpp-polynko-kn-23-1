import java.util.Random;
import java.util.Scanner;

public class Game2048 {

    static int[][] field = new int[4][4];
    static int score = 0;
    static int bestScore = 0;
    static Random random = new Random();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean play = true;

        while (play) {

            initGame();

            while (true) {

                printField();

                if (!canMove()) {
                    System.out.println("Гра завершена! Нема можливих ходiв.");
                    break;
                }

                System.out.println("Введiть хiд (W-вгору, S-вниз, A-влiво, D-вправо, Q-вихiд):");
                char move = sc.next().toUpperCase().charAt(0);

                boolean moved = false;

                if (move == 'A')
                    moved = moveLeft();
                if (move == 'D')
                    moved = moveRight();
                if (move == 'W')
                    moved = moveUp();
                if (move == 'S')
                    moved = moveDown();
                if (move == 'Q')
                    return;

                if (moved) {
                    addRandomNumber();
                }
            }

            if (score > bestScore) {
                bestScore = score;
            }

            System.out.println("Ваш рахунок: " + score);
            System.out.println("Найкращий рахунок: " + bestScore);
            System.out.println("Почати нову гру? (Y/N)");

            if (!sc.next().equalsIgnoreCase("Y")) {
                play = false;
            }
        }
    }

    static void initGame() {
        field = new int[4][4];
        score = 0;
        addRandomNumber();
        addRandomNumber();
    }

    static void printField() {

        System.out.println("\nРахунок: " + score + "   Найкращий: " + bestScore);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (field[i][j] == 0)
                    System.out.print(".\t");
                else
                    System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
    }

    static void addRandomNumber() {

        int emptyCount = 0;

        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (field[i][j] == 0)
                    emptyCount++;

        if (emptyCount == 0)
            return;

        int position = random.nextInt(emptyCount);
        int count = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (field[i][j] == 0) {

                    if (count == position) {
                        field[i][j] = (random.nextInt(10) < 9) ? 2 : 4;
                        return;
                    }
                    count++;
                }
            }
        }
    }

    static boolean moveLeft() {

        boolean moved = false;

        for (int i = 0; i < 4; i++) {

            for (int j = 1; j < 4; j++) {

                if (field[i][j] != 0) {

                    int col = j;

                    while (col > 0 && field[i][col - 1] == 0) {
                        field[i][col - 1] = field[i][col];
                        field[i][col] = 0;
                        col--;
                        moved = true;
                    }

                    if (col > 0 && field[i][col - 1] == field[i][col]) {
                        field[i][col - 1] *= 2;
                        score += field[i][col - 1];
                        field[i][col] = 0;
                        moved = true;
                    }
                }
            }
        }

        return moved;
    }

    static boolean moveRight() {

        rotateHorizontal();
        boolean moved = moveLeft();
        rotateHorizontal();

        return moved;
    }

    static boolean moveUp() {

        transpose();
        boolean moved = moveLeft();
        transpose();

        return moved;
    }

    static boolean moveDown() {

        transpose();
        rotateHorizontal();
        boolean moved = moveLeft();
        rotateHorizontal();
        transpose();

        return moved;
    }

    static void rotateHorizontal() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {

                int temp = field[i][j];
                field[i][j] = field[i][3 - j];
                field[i][3 - j] = temp;
            }
        }
    }

    static void transpose() {

        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 4; j++) {

                int temp = field[i][j];
                field[i][j] = field[j][i];
                field[j][i] = temp;
            }
        }
    }

    static boolean canMove() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if (field[i][j] == 0)
                    return true;

                if (j < 3 && field[i][j] == field[i][j + 1])
                    return true;

                if (i < 3 && field[i][j] == field[i + 1][j])
                    return true;
            }
        }

        return false;
    }
}