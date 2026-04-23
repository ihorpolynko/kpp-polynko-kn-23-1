package vehicle;

public interface Vehicle {
    String getName();
    Integer getMaxWeight();
    Integer getDrivingComplexity();
    VehicleStatus getStatus();
    void setStatus(VehicleStatus status);
}
