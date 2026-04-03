import java.util.Arrays;
import java.util.List;

class Shop extends Building {

    private List<String> departments;
    private boolean isSupermarket;

    public Shop(String address, List<String> dep) {
        super(address);
        this.departments = dep;
        this.isSupermarket = dep.size() > 1;
    }

    public List<String> getDepartments() { return departments; }
    public boolean isSupermarket() { return isSupermarket; }

    @Override
    public void print() {
        String type = isSupermarket ? "Супермаркет" : "Приватний магазин";
        System.out.println(type + " " + address + " вiддiли: " + departments);
    }

    // Віртуальний метод для встановлення відділів через рядок
    public void setFromString(String str) {
        // наприклад, рядок "Їжа,Одяг"
        departments = Arrays.asList(str.split(","));
        isSupermarket = departments.size() > 1;
    }
}