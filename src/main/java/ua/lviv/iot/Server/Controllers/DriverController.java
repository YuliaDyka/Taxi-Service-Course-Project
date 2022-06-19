package ua.lviv.iot.Server.Controllers;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.Server.Models.Driver;
import ua.lviv.iot.Server.Models.TaxiEntity;
import ua.lviv.iot.Server.Services.DriverService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/driver")
public class DriverController extends TaxiController {
    @Autowired
    public DriverController(DriverService driverService) throws IOException {
        this.service = driverService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Driver> drivers) throws IOException {
        for (var driver: drivers) {
            service.add(driver);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") final String id,
                                 @RequestBody final Driver driver) throws IOException {
        var parseId = Integer.parseInt(id);
        if (service.getById(Integer.parseInt(id)) != null) {
            if (service.edit(parseId, driver)) {
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
