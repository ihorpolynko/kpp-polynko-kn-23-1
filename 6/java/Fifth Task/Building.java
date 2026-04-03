abstract class Building {

    protected String address;

    public Building(String address) {
        this.address = address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract void print();
}