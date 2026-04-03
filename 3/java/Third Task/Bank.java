class Bank {

    private ATM[] atms;

    public Bank(int count) {
        atms = new ATM[count];

        for (int i = 0; i < count; i++) {
            atms[i] = new ATM();
        }
    }

    public void init() {
        for (ATM atm : atms) {
            int[] money = {10,10,10,10,10,10,10,10,10};
            atm.loadMoney(money);
        }
    }

    public int getTotalMoney() {
        int sum = 0;
        for (ATM atm : atms) {
            sum += atm.getTotal();
        }
        return sum;
    }
}