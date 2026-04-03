import java.util.*;

class Factory {

    static Random r = new Random();

    static House randomHouse() {
        return new House("A" + r.nextInt(100), r.nextInt(50) + 1);
    }

    static Shop randomShop() {
        List<String> possibleDeps = Arrays.asList("Food", "Clothes", "Tech");

        int count = r.nextBoolean() ? 1 : r.nextInt(5) + 1;
        List<String> deps = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            deps.add(possibleDeps.get(r.nextInt(possibleDeps.size())));
        }
        return new Shop("B" + r.nextInt(100), deps);
    }

    static School randomSchool() {
        SchoolType type = SchoolType.values()[r.nextInt(SchoolType.values().length)];
        int students;
        switch (type) {
            case GENERAL:
                students = 200 + r.nextInt(100);
                break;
            case GYMNASIUM:
                students = 150 + r.nextInt(50);
                break;
            case LYCEUM:
                students = 100 + r.nextInt(50);
                break;
            default:
                students = 100;
        }
        return new School("S" + r.nextInt(100), type, students);
    }

    static Hospital randomHospital() {
        return new Hospital("H" + r.nextInt(100), 50 + r.nextInt(50));
    }
}