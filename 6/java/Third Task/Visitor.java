class Visitor {
    String name;
    int reserveTime; // время резерва (например, в "шагах" или минутах)

    public Visitor(String name, int reserveTime) {
        this.name = name;
        this.reserveTime = reserveTime;
    }
}