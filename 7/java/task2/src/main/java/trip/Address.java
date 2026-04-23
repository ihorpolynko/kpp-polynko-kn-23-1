package trip;

public record Address(String address) {
    @Override
    public String toString() {
        return "Пункт призначення: " + address;
    }
}
