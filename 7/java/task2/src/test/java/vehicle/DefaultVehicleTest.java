package vehicle;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefaultVehicleTest {

    // Перевіряємо, що зміна статуса автомобіля працює коректно
    @Test
    void setStatus_shouldChangeStatus() {
        DefaultVehicle vehicle = new DefaultVehicle("Грузовик", 500, 2, VehicleStatus.OK);

        vehicle.setStatus(VehicleStatus.BROKEN);

        assertEquals(VehicleStatus.BROKEN, vehicle.getStatus());
    }

    // Перевіряємо, що геттери повертають коректні значення автомобіля
    @Test
    void getters_shouldReturnValues() {
        DefaultVehicle vehicle = new DefaultVehicle("Грузовик", 500, 2, VehicleStatus.OK);

        assertEquals("Грузовик", vehicle.getName());
        assertEquals(500, vehicle.getMaxWeight());
        assertEquals(2, vehicle.getDrivingComplexity());
        assertEquals(VehicleStatus.OK, vehicle.getStatus());
    }
}
