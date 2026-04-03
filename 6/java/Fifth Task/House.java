class House extends Building {

    private int residents;

    public House(String address, int residents) {
        super(address);
        this.residents = residents;
    }

    public int getResidents() {
        return residents;
    }

        // Встановлення через рядок
    public void setFromString(String str) {
        // наприклад, "20" — кількість мешканців
        this.residents = Integer.parseInt(str);
    }


    @Override
    public void print() {
        System.out.println("Будинок " + address + " мешканцi: " + residents);
    }
}