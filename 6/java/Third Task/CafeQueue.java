import java.util.Deque;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Comparator;

class CafeQueue {

    private Deque<Visitor> queue = new LinkedList<>();

    // звичайний відвідувач
    public void addVisitor(String name) {
        queue.addLast(new Visitor(name, -1)); // -1 = немає резерву
    }

    // резерв
    public void addReserved(String name, int time) {
        for (Visitor v : queue) {
            if (v.name.equals(name)) {
                v.reserveTime = time;
                System.out.println("Оновлено резерв для: " + name);
                return;
            }
        }
        queue.addLast(new Visitor(name, time));
    }

    // звільнився столик
    public void serve(int timeNow) {

        // шукаємо резерви, час яких вже настав (reserveTime != -1 && reserveTime <= timeNow)
        Optional<Visitor> reservedNow = queue.stream()
            .filter(v -> v.reserveTime >= 0 && v.reserveTime <= timeNow)
            .min(Comparator.comparingInt(v -> v.reserveTime));

        if (reservedNow.isPresent()) {
            Visitor v = reservedNow.get();
            queue.remove(v);
            System.out.println("Сiв (резерв): " + v.name);
            return;
        }

        if (!queue.isEmpty()) {
            System.out.println("Сiв: " + queue.pollFirst().name);
        } else {
            System.out.println("Черга пуста");
        }
    }
}