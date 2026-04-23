package vehicle;

import lombok.*;

@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DefaultVehicle implements Vehicle  {
    private String name = "";
    private Integer maxWeight = 0;
    private Integer drivingComplexity = 1;
    private VehicleStatus status = VehicleStatus.OK;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getMaxWeight() {
        return maxWeight;
    }

    @Override
    public Integer getDrivingComplexity() {
        return drivingComplexity;
    }

    @Override
    public VehicleStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(VehicleStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return """
                  Назва: %s
                  Вантажопідйомність: %d
                  Складність керування: %d
                  Статус: %s
                """.formatted(name, maxWeight, drivingComplexity, status.getUkrainianName()).trim();
    }
}
