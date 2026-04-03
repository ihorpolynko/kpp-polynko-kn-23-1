class Hospital extends Building {

    private int beds;

    public Hospital(String address, int beds) {
        super(address);
        this.beds = beds;
    }

    @Override
    public void print() {
        System.out.println("Лiкарня " + address + " лiжок: " + beds);
    }
}