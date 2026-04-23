package trip;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CargoType {
    GENERAL("звичайний", 1, 1.0),
    FRAGILE("крихкий", 2, 1.2),
    HEAVY("важкий", 3, 1.5),
    DANGEROUS("небезпечний", 4, 2.0);

    private final String ukrainianName;
    private final int requiredExperience;
    private final double paymentMultiplier;

    @Override
    public String toString() {
        return ukrainianName;
    }
}
