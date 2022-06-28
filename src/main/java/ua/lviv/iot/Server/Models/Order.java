package ua.lviv.iot.Server.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Order extends TaxiEntity {
    /**
     * stores carID.
     */
    private Integer carID;
    /**
     * stores clientID.
     */
    private Integer clientID;
    /**
     * stores distance.
     */
    private Double distance;
    /**
     * stores price.
     */
    private Double price;
}
