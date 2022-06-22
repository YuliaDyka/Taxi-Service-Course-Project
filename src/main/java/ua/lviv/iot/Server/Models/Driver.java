package ua.lviv.iot.Server.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Driver extends TaxiEntity {
    /**
     * stores phone.
     */
    private String phone;
    /**
     * stores experience.
     */
    private String experience;
}
