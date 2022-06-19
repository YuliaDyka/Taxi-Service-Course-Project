package ua.lviv.iot.Server.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car extends TaxiEntity {
    private String vinCode;

    private String stateNumber;

    private Integer driverId;

    private String description;
}
