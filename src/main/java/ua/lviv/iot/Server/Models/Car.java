package ua.lviv.iot.Server.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Car extends TaxiEntity {
    /**
     * stores vinCode.
     */
    private String vinCode;
    /**
     * stores stateNumber.
     */
    private String stateNumber;
    /**
     * stores driverId.
     */
    private Integer driverId;
    /**
     * stores description.
     */
    private String description;
}
