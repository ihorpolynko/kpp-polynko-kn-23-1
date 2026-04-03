import java.util.*;
import java.text.Normalizer;
import java.util.Locale;

class Street {

    private List<Building> buildings = new ArrayList<>();

    public void add(Building b) {
        buildings.add(b);
    }

    public void remove(String address) {
        buildings.removeIf(b -> b.address.equals(address));
    }

    public void printAll() {
        buildings.forEach(Building::print);
    }

    public List<Building> getBuildings() {
        return buildings;
    }

    public void findShops(String department) {
        String normDept = normalize(department);

        buildings.stream()
            .filter(b -> b instanceof Shop)
            .map(b -> (Shop) b)
            .filter(s -> s.getDepartments().stream()
                .anyMatch(d -> normalize(d).equals(normDept)))
            .forEach(Shop::print);
    }

    // знайти магазини поруч з будинком
    public void findNearbyShops(House house, int range, String department) {
        int index = buildings.indexOf(house);
        if (index == -1)
            return;

        int from = Math.max(0, index - range);
        int to = Math.min(buildings.size(), index + range + 1);

        String normDept = normalize(department);

        buildings.subList(from, to).stream()
            .filter(b -> b instanceof Shop)
            .map(b -> (Shop) b)
            .filter(s -> s.getDepartments().stream()
                .anyMatch(d -> normalize(d).equals(normDept)))
            .forEach(Shop::print);
    }

        private String normalize(String s) {
        if (s == null) return "";
        String n = Normalizer.normalize(s, Normalizer.Form.NFKC);
        n = n.replace('\u00A0', ' ').trim();
        return n.toLowerCase(Locale.ROOT);
        }
}