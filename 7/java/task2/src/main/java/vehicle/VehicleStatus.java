package vehicle;

public enum VehicleStatus {
    OK("справний"),
    REPAIR("на ремонті"),
    BROKEN("зламаний");

    private final String ukrainianName;

    VehicleStatus(String ukrainianName) {
        this.ukrainianName = ukrainianName;
    }

    public String getUkrainianName() {
        return ukrainianName;
    }
}
