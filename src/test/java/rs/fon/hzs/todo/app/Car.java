package rs.fon.hzs.todo.app;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Car {

    private int carSeats;
    private String registrationNumber;
    private String mark;

}
