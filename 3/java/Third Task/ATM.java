class ATM {

    private int[] nominal = { 500, 200, 100, 50, 20, 10, 5, 2, 1 };
    private int[] count = new int[nominal.length];

    private int maxWithdraw = 10000;
    private int maxNotes = 20;

    // ініціалізація
    public void loadMoney(int[] values) {
        for (int i = 0; i < count.length; i++) {
            count[i] = values[i];
        }
    }

    // КУПЮРОПРИЙМАЧ
    public void deposit(int[] values) {
        for (int i = 0; i < count.length; i++) {
            count[i] += values[i];
        }
    }

    // загальна сума
    public int getTotal() {
        int sum = 0;
        for (int i = 0; i < nominal.length; i++) {
            sum += nominal[i] * count[i];
        }
        return sum;
    }

    // зняття грошей
    public void withdraw(int amount) throws ATMException {

        if (amount > maxWithdraw)
            throw new LimitExceededException("Перевищено лiмiт");

        if (amount > getTotal())
            throw new NotEnoughMoneyException("Недостатньо коштiв");

        // копія масиву
        int[] tempCount = count.clone();

        int notesUsed = 0;

        for (int i = 0; i < nominal.length; i++) {

            while (amount >= nominal[i] && tempCount[i] > 0) {

                amount -= nominal[i];
                tempCount[i]--;
                notesUsed++;

                if (notesUsed > maxNotes)
                    throw new LimitExceededException("Забагато купюр");
            }
        }

        if (amount > 0)
            throw new CannotDispenseException("Неможливо видати суму");

        count = tempCount;

        System.out.println("Грошi видано");
        printState();
    }

    // ручне введення суми через купюроприймач
    public void depositAmount(int amount) throws ATMException {

        if (amount <= 0)
            throw new ATMException("Невiрна сума");

        // тимчасовий масив
        int[] temp = new int[count.length];

        // розбиваємо суму на купюри (жадібний алгоритм)
        for (int i = 0; i < nominal.length; i++) {

            while (amount >= nominal[i]) {
                amount -= nominal[i];
                temp[i]++;
            }
        }

        // якщо залишок є — значить не можемо прийняти
        if (amount > 0)
            throw new CannotDispenseException("Неможливо прийняти таку суму");

        // додаємо в банкомат
        for (int i = 0; i < count.length; i++) {
            count[i] += temp[i];
        }

        System.out.println("Суму успiшно внесено");
    }

    public void printState() {
        System.out.println("Стан банкомату:");
        for (int i = 0; i < nominal.length; i++) {
            System.out.println(nominal[i] + " грн: " + count[i] + " шт");
        }
        System.out.println("Загальна сума: " + getTotal());
    }
}