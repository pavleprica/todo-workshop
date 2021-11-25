package rs.fon.hzs.todo.app;

import org.junit.jupiter.api.Test;

public class TestJava {

    @Test
    void test() {
        System.out.println("Printing");
        Car car = new Car(5, "BG1111CE", "Ford");
        Car carBuilder = Car.builder()
                .mark("Dacia")
                .build();
        System.out.println("Car seats: " + car.getCarSeats());
        car.setCarSeats(10);
        System.out.println("Car: " + car);
        System.out.println("Car: " + carBuilder);

        CarRecord carRecord = new CarRecord(2, "BG1112CE", "Mercedes");
        carRecord.mark();
        carRecord.registrationNumber();
    }

}
