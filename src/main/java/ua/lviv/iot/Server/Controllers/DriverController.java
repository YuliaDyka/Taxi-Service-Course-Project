package ua.lviv.iot.Server.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import ua.lviv.iot.Server.Models.Driver;
import ua.lviv.iot.Server.Services.DriverService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/driver")
public final class DriverController extends TaxiController {
    @Autowired
    public DriverController(final DriverService driverService) throws IOException {
        service = driverService;
    }

    /**
     * Add drivers (POST request).
     * @param drivers
     * @return ResponseEntity
     * @throws IOException
     */
    @PostMapping("/add")
    public ResponseEntity<?> addPost(@RequestBody final List<Driver> drivers) throws IOException {
        for (var driver: drivers) {
            service.add(driver);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * Edit driver by id.
     * @param id
     * @param driver
     * @return ResponseEntity
     * @throws IOException
     */
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
