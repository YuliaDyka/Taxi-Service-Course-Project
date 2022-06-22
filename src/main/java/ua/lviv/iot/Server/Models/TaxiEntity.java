package ua.lviv.iot.Server.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class TaxiEntity {
    /**
     * stores id.
     */
    protected Integer id;
    /**
     * stores name.
     */
    protected String name;
}
